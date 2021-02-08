package hau.kute.spring.tutorial.springbootmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RefreshScope
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Environment environment;

	@Value("${test.property}")
	private String testProperty;

	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(
					@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping("/status-checking")
	public String statusChecking() {
		String result =  "Working on port "
						+ environment.getProperty("local.server.port");

		result += ", with test property = " + testProperty;

		return result;
	}
}
