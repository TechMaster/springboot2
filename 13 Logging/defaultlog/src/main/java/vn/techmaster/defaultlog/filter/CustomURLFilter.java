package vn.techmaster.defaultlog.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomURLFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    log.info("Logging Request  {} : {}", request.getMethod() , request.getRequestURI());

    // call next filter in the filter chain
    filterChain.doFilter(request, response);
    log.info("Logging Response :{}", response.getContentType());
  }
}
