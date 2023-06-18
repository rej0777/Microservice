package productservice.controller;

import java.awt.PageAttributes.MediaType;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import productservice.dto.ProductDto;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("product")
public class ProductStreamController {
	
	@Autowired
	private Flux<ProductDto> flux;
	
	@GetMapping(value = "stream/{maxPrice}", produces = org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ProductDto> getProductUpdates(@PathVariable int maxPrice){
		return this.flux
				.filter(dto -> dto.getPrice() <= maxPrice);
	}

}
