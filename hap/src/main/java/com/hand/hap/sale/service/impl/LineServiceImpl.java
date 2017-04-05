package com.hand.hap.sale.service.impl;

import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.extensible.components.ServiceListenerChainFactory;
import com.hand.hap.sale.dto.Line;
import com.hand.hap.sale.mapper.LineMapper;
import com.hand.hap.sale.service.ILineService;
import com.hand.hap.system.dto.BaseDTO;
import com.hand.hap.system.dto.DTOStatus;
import com.hand.hap.system.service.impl.BaseServiceImpl;

@Service
@Transactional
public class LineServiceImpl extends BaseServiceImpl<Line> implements ILineService{

	
	@Autowired
	private LineMapper lineMapper;
	@Autowired
        private ServiceListenerChainFactory chainFactory;
	@Override
	public List<Line> queryOrderLines(IRequest requestContext, Line orderLines, int page, int pagesize) {
		PageHelper.startPage(page, pagesize);
		return lineMapper.selectOrderLines(orderLines);
	}
	
	 @Override
	 @Transactional(rollbackFor = Exception.class)
	public void insertLine(Line line) {
		// TODO Auto-generated method stub
		 lineMapper.insertLine(line);
	}

	@Override
	public List<Line> batchUpdate1(IRequest request, List<Line> orderLines) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectMax() {
		// TODO Auto-generated method stub
		return lineMapper.selectMax();
	}

}
