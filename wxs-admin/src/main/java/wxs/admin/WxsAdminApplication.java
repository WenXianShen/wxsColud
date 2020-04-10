package wxs.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages ="wxs")
@SpringBootApplication
/*
public class WxsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxsAdminApplication.class, args);
    }

}
*/
public class WxsAdminApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WxsAdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WxsAdminApplication.class);
    }
}
