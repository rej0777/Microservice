package orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import orderservice.dto.OrderRequestDto;
import orderservice.dto.OrderResponseDto;
import orderservice.service.OrderFulfillmentService;
import orderservice.service.OrderQueryService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {
	
	@Autowired
	private OrderFulfillmentService orderFulfillmentService;
	
	@Autowired
	private OrderQueryService queryService;
	
	@PostMapping
	public Mono<ResponseEntity<OrderResponseDto>> order(@RequestBody Mono<OrderRequestDto> requestDtoMono){
		return this.orderFulfillmentService.procesOrder(requestDtoMono)
				.map(ResponseEntity::ok)
				.onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
				.onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
	}
	
	@GetMapping("user/{userId}")
	public Flux<OrderResponseDto> getOrderByUserId(@PathVariable int userId ){
		return this.queryService.getProductByUserId(userId);	
	}

}
