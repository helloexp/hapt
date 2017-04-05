package com.hand.hap.sale.mapper;

import java.util.List;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.sale.dto.Header;


public interface HeaderMapper extends Mapper<Header>{

	List<Header> selectOrderHeaders(Header header);
//	void insertOrderHeader(Header orderHeader);
//	void updateOrderHeader(Header orderHeader);
//	 void deleteOrderHeader(Header orderHeader);
	List<Header> ExcelHeader(Header header);
	
}
