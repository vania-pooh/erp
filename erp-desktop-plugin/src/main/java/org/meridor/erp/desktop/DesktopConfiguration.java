package org.meridor.erp.desktop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DesktopConfiguration {

    @Bean
    public SomeBean someBean() {
        return new SomeBean();
    }

    @Bean
    public AnotherBean anotherBean(SomeBean someBean) {
        return new AnotherBean(someBean);
    }

}
