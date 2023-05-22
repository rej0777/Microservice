package orderservice;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import orderservice.client.ProductClient;
import orderservice.client.UserClient;
import orderservice.dto.OrderRequestDto;
import orderservice.dto.OrderResponseDto;
import orderservice.dto.ProductDto;
import orderservice.dto.UserDto;
import orderservice.service.OrderFulfillmentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class SampleMicroserviceOrderApplicationTests {
	
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private ProductClient productClient;
	
	@Autowired
	private OrderFulfillmentService orderFulfillmentService;	
	
	
	@Test
	void contextLoads() {	
		
	Flux<OrderResponseDto> dtoFlux=	Flux.zip(userClient.getAllUsers(), productClient.getAllProduct())
		.map(t -> bulidDto(t.getT1(), t.getT2()))
		.flatMap(dto -> this.orderFulfillmentService.procesOrder(Mono.just(dto)))
		.doOnNext(System.out::println);	
	
	StepVerifier.create(dtoFlux)
	.expectNextCount(2)
	.verifyComplete();
	
	}
	
	private OrderRequestDto bulidDto(UserDto userDto, ProductDto productDto) {
		OrderRequestDto dto = new OrderRequestDto();
		dto.setUserId(userDto.getId());
		dto.setProductId(productDto.getId());
		return dto; 
	}

}
