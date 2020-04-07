package wxs.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages ="wxs")
@SpringBootApplication
public class WxsOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxsOauthApplication.class, args);
    }

}
