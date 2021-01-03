package hau.kute.spring.tutorial.springbootmicroservice.appdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppdiscoveryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppdiscoveryserviceApplication.class, args);
	}

}
