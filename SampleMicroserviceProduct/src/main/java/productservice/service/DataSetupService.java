package productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import productservice.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DataSetupService implements CommandLineRunner{

	@Autowired
	private ProductService productService;
	
	
	@Override
	public void run(String... args) throws Exception {
			
		ProductDto p1 = new ProductDto("prod1", 2000);
		ProductDto p2 = new ProductDto("prod2", 3000);
		ProductDto p3 = new ProductDto("prod3", 4000);
		ProductDto p4 = new ProductDto("prod4", 5000);
		ProductDto p5 = new ProductDto("prod5", 6000);
		
		Flux.just(p1, p2, p3, p4, p5).flatMap(p -> this.productService.insertMono(Mono.just(p)))
		.subscribe(System.out::println);
	}

}
