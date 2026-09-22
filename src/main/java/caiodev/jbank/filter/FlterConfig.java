package caiodev.jbank.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlterConfig {

    private final IpFilter ipFilter;

    public FlterConfig(IpFilter ipFilter) {
        this.ipFilter = ipFilter;
    }

    @Bean
    public FilterRegistrationBean<IpFilter> filterRegistrationBean() {
        var registrationBean = new FilterRegistrationBean<IpFilter>();

        registrationBean.setFilter(ipFilter);

        return registrationBean;
    }
}
