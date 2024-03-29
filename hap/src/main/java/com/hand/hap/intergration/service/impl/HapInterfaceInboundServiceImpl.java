package com.hand.hap.intergration.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.components.UserLoginInfoCollection;
import com.hand.hap.intergration.beans.HapInvokeInfo;
import com.hand.hap.intergration.controllers.HapInvokeRequestBodyAdvice;
import com.hand.hap.intergration.dto.HapInterfaceInbound;
import com.hand.hap.intergration.mapper.HapInterfaceInboundMapper;
import com.hand.hap.intergration.service.IHapInterfaceInboundService;
import com.hand.hap.intergration.util.HapInvokeExceptionUtils;
import com.hand.hap.system.service.impl.BaseServiceImpl;

@Service
@Transactional
public class HapInterfaceInboundServiceImpl extends BaseServiceImpl<HapInterfaceInbound>
        implements IHapInterfaceInboundService {

    @Autowired
    private HapInterfaceInboundMapper inboundMapper;

    @Autowired
    UserLoginInfoCollection loginInfo;

    @Override
    public int inboundInvoke(HttpServletRequest request, HapInterfaceInbound inbound, Throwable throwable) {

        // 处理一些请求信息
        String ipAddress = loginInfo.getIpAddress(request);
        inbound.setIp(ipAddress);
        String referer = request.getHeader("Referer");
        referer = StringUtils.abbreviate(referer, 240);
        inbound.setReferer(referer);
        inbound.setUserAgent(request.getHeader("User-Agent"));
        if (inbound.getRequestMethod() == null)
            inbound.setRequestMethod(request.getMethod());
        if (inbound.getInterfaceUrl() == null)
            inbound.setInterfaceUrl(request.getServletPath());
        if (inbound.getRequestHeaderParameter() == null)
            inbound.setRequestHeaderParameter(request.getQueryString());
        if (inbound.getRequestBodyParameter() == null)
            inbound.setRequestBodyParameter(HapInvokeRequestBodyAdvice.getAndRemoveBody());
        if (throwable != null) {
            // 获取异常堆栈
            inbound.setStackTrace(HapInvokeExceptionUtils.getRootCauseStackTrace(throwable,10));
            inbound.setRequestStatus(HapInvokeInfo.REQUEST_FAILURE);
        }

        return inboundMapper.insertSelective(inbound);

    }

    @Override
    public List<HapInterfaceInbound> select(IRequest request, HapInterfaceInbound condition, int pageNum,
            int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return inboundMapper.select(condition);
    }
}