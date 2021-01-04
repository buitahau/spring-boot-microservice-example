package hau.kute.spring.tutorial.springbootmicroservice.appzuulapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AppZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppZuulApiGatewayApplication.class, args);
	}

}
