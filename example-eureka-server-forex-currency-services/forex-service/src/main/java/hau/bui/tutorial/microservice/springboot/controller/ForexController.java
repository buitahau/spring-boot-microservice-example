package hau.bui.tutorial.microservice.springboot.controller;

import hau.bui.tutorial.microservice.springboot.entity.ExchangeValue;
import hau.bui.tutorial.microservice.springboot.service.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HauKute on 11/21/2020.
 */
@RestController
public class ForexController {

	@Autowired
	private Environment _environment;

	@Autowired
	private ExchangeValueRepository _repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(
					@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = _repository.findByFromAndTo(from, to);

		exchangeValue.setPort(Integer.parseInt(
						_environment.getProperty("local.server.port")));

		return exchangeValue;
	}
}
