package productservice.util;

import org.springframework.beans.BeanUtils;

import productservice.dto.ProductDto;
import productservice.entity.Product;

public class EntityDtoUtil {

	public static ProductDto toDto(Product product ) {
		
		ProductDto dto = new ProductDto();		
		BeanUtils.copyProperties(product, dto);
		return dto;
	}
	
	public static Product toEntity(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto ,product );
		return product;		
	}
}
