package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.shared.AlbumDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="albums-ws", fallbackFactory =
				AlbumServiceClientFallBackFactory
				.class)
public interface AlbumServiceClient {

	@GetMapping("/users/{id}/albums")
	List<AlbumDTO> getAlbums(@PathVariable("id") String id);
}

class AlbumServiceClientFallBackFactory implements
				FallbackFactory<AlbumServiceClient> {

	@Override
	public AlbumServiceClient create(Throwable throwable) {

		return new AlbumServiceClientFallBack(throwable);
	}
}

class AlbumServiceClientFallBack implements AlbumServiceClient {

	private Throwable cause;
	private Logger logger = LoggerFactory.getLogger
					(AlbumServiceClientFallBack.class);

	public AlbumServiceClientFallBack(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public List<AlbumDTO> getAlbums(@PathVariable("id") String id) {

		logger.error(cause.getMessage());
		return new ArrayList<>();
	}
}
