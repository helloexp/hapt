package com.hand.hap.sale.service;

import java.util.List;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.sale.dto.Line;
import com.hand.hap.system.service.IBaseService;

public interface ILineService extends IBaseService<Line>,ProxySelf<ILineService>{

	
	public List<Line> queryOrderLines(IRequest requestContext, Line orderLines, int page, int pagesize);
	public List<Line> batchUpdate1(IRequest request,List<Line> orderLines);
	public void insertLine(Line line);
	public int selectMax();
	
}
