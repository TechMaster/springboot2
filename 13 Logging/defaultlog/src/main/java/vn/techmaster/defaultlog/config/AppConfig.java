package vn.techmaster.defaultlog.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import vn.techmaster.defaultlog.filter.CustomURLFilter;

@Configuration
public class AppConfig {
  @ConditionalOnExpression("false")
  @Bean
  public CommonsRequestLoggingFilter logFilter() {
    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(10000);
    filter.setIncludeHeaders(false);
    filter.setAfterMessagePrefix("REQUEST DATA : ");
    return filter;
  }

  @Bean
  public FilterRegistrationBean<CustomURLFilter> filterRegistrationBean() {
    FilterRegistrationBean<CustomURLFilter> registrationBean = new FilterRegistrationBean<>();
    CustomURLFilter customURLFilter = new CustomURLFilter();

    registrationBean.setFilter(customURLFilter);
    registrationBean.addUrlPatterns("/film/*");
    registrationBean.setOrder(2); // set precedence
    return registrationBean;
  }
}
