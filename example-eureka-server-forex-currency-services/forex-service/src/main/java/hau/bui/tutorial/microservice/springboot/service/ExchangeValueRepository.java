package hau.bui.tutorial.microservice.springboot.service;

import hau.bui.tutorial.microservice.springboot.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HauKute on 11/21/2020.
 */
public interface ExchangeValueRepository
				extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from, String to);
}
