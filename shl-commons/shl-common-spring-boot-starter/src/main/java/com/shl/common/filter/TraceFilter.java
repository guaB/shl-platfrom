package com.shl.common.filter;

import com.shl.common.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author songhonglei
 * @version 1.0
 * @description 链路追踪ID
 * @date 2019/12/15
 */
@ConditionalOnClass(Filter.class)
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceId = request.getHeader(CommonConstant.TRACE_ID_HEADER);
            if (StringUtils.isNotEmpty(traceId)){
                MDC.put(CommonConstant.LOG_TRACE_ID, traceId);
            }
            filterChain.doFilter(request, response);
        }finally {
            MDC.clear();
        }
    }
}
