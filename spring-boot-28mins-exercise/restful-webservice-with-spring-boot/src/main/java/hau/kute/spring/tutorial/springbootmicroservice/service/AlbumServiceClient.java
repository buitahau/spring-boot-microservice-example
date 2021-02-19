package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.shared.AlbumDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="albums-ws")
public interface AlbumServiceClient {

	@GetMapping("/users/{id}/albums")
	List<AlbumDTO> getAlbums(@PathVariable("id") String id);
}
