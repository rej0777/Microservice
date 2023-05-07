package productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import productservice.dto.ProductDto;
import productservice.repository.ProductRepository;
import productservice.util.EntityDtoUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Flux<ProductDto> getAll(){
		return this.productRepository.findAll()
		.map(EntityDtoUtil::toDto);
	}
	
	public Flux<ProductDto> getProductRange(int min, int max){
		return this.productRepository.findByPriceBetween(Range.closed(min, max))
		.map(EntityDtoUtil::toDto);
	}
	
	public Mono<ProductDto> getProductById(String id){
		return this.productRepository.findById(id).map(EntityDtoUtil::toDto);
	}
	
	public Mono<ProductDto> insertMono(Mono<ProductDto> productDtoMono){
	return	productDtoMono
			.map(EntityDtoUtil::toEntity)
			.flatMap(this.productRepository::insert)
			.map(EntityDtoUtil::toDto);
	}

	public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono){
		return this.productRepository
				.findById(id)
				.flatMap(p -> productDtoMono
						.map(EntityDtoUtil::toEntity)
						.doOnNext(e ->e.setId(id)))
				.flatMap(this.productRepository::save)
				.map(EntityDtoUtil::toDto);		
	}
	
	public Mono<Void> deleteProduct(String id) {
		return this.productRepository.deleteById(id);
	}
}
