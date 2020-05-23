package cn.thinking.signature.filter;

import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*", filterName = "requestFilter")
@Order(1)
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (httpServletRequest.getContentType() != null
                && httpServletRequest.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ServletRequest requestWrapper = new BodyReaderRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
    }
}