package com.josetesan.ordershop.infrastructure.spring;

import java.lang.reflect.Field;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

/**
 * Hack to make swagger-ui3 work on spring-boot 2.6+
 *
 * @see [https://github.com/springfox/springfox/issues/3462]
 */
@Configuration
public class SpringFoxConfiguration {

  @Bean
  public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
    return new BeanPostProcessor() {

      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName)
          throws BeansException {
        if (bean instanceof WebMvcRequestHandlerProvider requestHandlerProvider) {
          customizeSpringfoxHandlerMappings(getHandlerMappings(requestHandlerProvider));
        }
        return bean;
      }

      private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(
          List<T> mappings) {
        List<T> copy =
            mappings.stream().filter(mapping -> mapping.getPatternParser() == null).toList();
        mappings.clear();
        mappings.addAll(copy);
      }

      @SuppressWarnings("unchecked")
      private List<RequestMappingInfoHandlerMapping> getHandlerMappings(
          WebMvcRequestHandlerProvider bean) {
        try {
          Field field =
              ReflectionUtils.findField(
                  WebMvcRequestHandlerProvider.class,
                  field1 -> field1.getName().equals("handlerMappings"));
          field.setAccessible(true);
          return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          throw new IllegalStateException(e);
        }
      }
    };
  }
}
