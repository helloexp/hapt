package com.hand.hap.sale.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.TokenException;
import com.hand.hap.excel.dto.ColumnInfo;
import com.hand.hap.excel.dto.ExportConfig;
import com.hand.hap.excel.service.IExportService;
import com.hand.hap.excel.service.IHapExcelImportService;
import com.hand.hap.function.dto.Function;
import com.hand.hap.sale.dto.Header;
import com.hand.hap.sale.dto.Line;
import com.hand.hap.sale.service.ILineService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

@Controller
public class LineController extends BaseController {

	@Autowired
	private ILineService lineService;

	// 查询

	@RequestMapping(value = "/sale/orderLines/query")
	@ResponseBody
	public ResponseData getOrderHeader(HttpServletRequest request, Line orderLines,
			@RequestParam(defaultValue = DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
		IRequest iRequest = createRequestContext(request);
		return new ResponseData(lineService.queryOrderLines(iRequest, orderLines, page, pagesize));
		//return new ResponseData(lineService.selectAll());

	}

	// submit
	@RequestMapping(value = "/sale/orderLines/submit", method = RequestMethod.POST)
	@ResponseBody
	public void submitLine(@RequestBody final List<Line> lines,final BindingResult result,
			HttpServletRequest request) throws TokenException {
		System.out.println("lines="+lines);
		Line line = lines.get(0);
		int maxlineid = lineService.selectMax();
		maxlineid++;
		line.setLineId(maxlineid);
		System.out.println("line="+line);
		lineService.insertLine(line);
	}
	
    @Autowired
    IExportService excelService;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/sale/orderLines/export")
    public void createXLS(HttpServletRequest request, @RequestParam String config,
            HttpServletResponse httpServletResponse) {
        IRequest requestContext = createRequestContext(request);
        try {
            JavaType type = objectMapper.getTypeFactory().constructParametrizedType(ExportConfig.class,
                    ExportConfig.class, Header.class, ColumnInfo.class);
            ExportConfig<Header, ColumnInfo> exportConfig = objectMapper.readValue(config, type);
            excelService.exportAndDownloadExcel("com.hand.hap.sale.mapper.HeaderMapper.ExcelHeader",
                    exportConfig, request, httpServletResponse, requestContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
