package com.hand.hap.sale.service;

import java.util.List;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.sale.dto.Header;
import com.hand.hap.system.service.IBaseService;

public interface IHeaderService extends IBaseService<Header>,ProxySelf<IHeaderService>{

	
	public List<Header> queryOrderHeaders(IRequest requestContext, Header orderHeader, int page, int pagesize);
	public List<Header> ExcelHeader(Header header);
	
}
