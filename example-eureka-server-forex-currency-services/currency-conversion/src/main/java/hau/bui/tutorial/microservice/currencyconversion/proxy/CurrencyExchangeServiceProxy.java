package hau.bui.tutorial.microservice.currencyconversion.proxy;

import hau.bui.tutorial.microservice.currencyconversion.bean.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by HauKute on 11/22/2020.
 */
@FeignClient(name="forex-service")
@RibbonClient(name = "forex-service")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	CurrencyConversionBean retrieveExchangeValue(
					@PathVariable("from") String from,
					@PathVariable("to") String to);

}
