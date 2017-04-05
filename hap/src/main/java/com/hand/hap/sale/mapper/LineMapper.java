package com.hand.hap.sale.mapper;

import java.util.List;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.sale.dto.Line;

public interface LineMapper extends Mapper<Line>{

	List<Line> selectOrderLines(Line line);
	 void insertLine(Line line);
//	 void updateOrderLine(Header orderHeader);
//	 void deleteOrderLine(Header orderHeader);
	 int selectMax();
	
}
