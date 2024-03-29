package com.hand.hap.generator.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hand.hap.generator.dto.DBColumn;
import com.hand.hap.generator.dto.DBTable;
import com.hand.hap.generator.dto.GeneratorInfo;
import com.hand.hap.generator.service.IHapGeneratorService;

import freemarker.template.TemplateException;

/**
 * Created by jialong.zuo@hand-china.com on 2016/10/24.
 */
@Service
public class HapGeneratorServiceImpl implements IHapGeneratorService {

    @Autowired
    @Qualifier("sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    /*
     * @Autowired FileUtil fileUtil;
     */

    @Override
    public List<String> showTables() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<String> tables;

            Connection conn = DBUtil.getConnectionBySqlSession(sqlSession);
            tables = DBUtil.showAllTables(conn);
            conn.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    @Override
    public int generatorFile(GeneratorInfo info) {
        int rs = 0;
        String tableName = info.getTargetName();
        DBTable dbTable = getTableInfo(tableName);
        FileUtil.generatorInfo = info;
        try {
            rs = createFile(dbTable, info);
        } catch (IOException e) {
            rs = -1;
            e.printStackTrace();
        } catch (TemplateException e) {
            rs = -1;
            e.printStackTrace();
        }

        return rs;
    }

    // 获取table信息
    public DBTable getTableInfo(String tableName) {
        Connection conn = null;
        DBTable dbTable = new DBTable();
        List<DBColumn> columns = dbTable.getColumns();
        List<String> multiColumns = null;
        List<String> NotNullColumns = null;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 设置tablename
            dbTable.setName(tableName);
            conn = DBUtil.getConnectionBySqlSession(sqlSession);
            DatabaseMetaData dbmd = conn.getMetaData();
            // 是否为多语言表
            boolean multiLanguage = DBUtil.isMultiLanguageTable(tableName);
            if (multiLanguage) {
                dbTable.setMultiLanguage(multiLanguage);
                multiColumns = DBUtil.isMultiLanguageColumn(tableName, dbmd);
                // 判断多语言字段
            }
            // 获取主键字段
            String column_PK = DBUtil.getPrimaryKey(tableName, dbmd);
            // 获取不为空的字段
            NotNullColumns = DBUtil.getNotNullColumn(tableName, dbmd);
            // 获取表列信息
            ResultSet rs1 = DBUtil.getTableColumnInfo(tableName, dbmd);
            while (rs1.next()) {
                DBColumn column = new DBColumn();
                String columnName = rs1.getString("COLUMN_NAME");
                if (columnName.equals("OBJECT_VERSION_NUMBER")) {
                    break;
                }
                column.setName(columnName);
                String typeName = rs1.getString("TYPE_NAME");
                column.setJdbcType(typeName);
                // 判断是否为主键
                if (columnName.equals(column_PK)) {
                    column.setId(true);
                }
                // 判断是否为null字段
                for (String n : NotNullColumns) {
                    if (columnName.equals(n) && !columnName.equals(column_PK)) {
                        if (typeName.equals("BIGINT")) {
                            column.setNotNull(true);
                        } else if (typeName.equals("VARCHAR")) {
                            column.setNotEmpty(true);
                        }
                    }
                }
                // 判断多语言表中的多语言字段
                if (multiLanguage) {
                    for (String m : multiColumns) {
                        if (m.equals(columnName)) {
                            column.setMultiLanguage(true);
                            break;
                        }
                    }
                }
                columns.add(column);
            }
            // 是否是多语言表
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbTable;
    }

    public int createFile(DBTable table, GeneratorInfo info) throws IOException, TemplateException {

        int rs = FileUtil.isFileExist();
        if (rs == 0) {
            if (!info.getDtoStatus().equals("NotOperation")) {
                FileUtil.createDto(table);
            }
            if (!info.getControllerStatus().equals("NotOperation")) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Controller, table);
            }
            if (!info.getMapperStatus().equals("NotOperation")) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Mapper, table);
            }
            if (!info.getImplStatus().equals("NotOperation")) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Impl, table);
            }
            if (!info.getServiceStatus().equals("NotOperation")) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Service, table);
            }
            if (!info.getMapperXmlStatus().equals("NotOperation")) {
                FileUtil.createFtlInfoByType(FileUtil.pType.MapperXml, table);
            }
            if (!info.getHtmlStatus().equals("NotOperation")) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Html, table);
            }
        }
        return rs;
    }

}
