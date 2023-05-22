package orderservice.service;

import java.time.Duration;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import orderservice.client.ProductClient;
import orderservice.client.UserClient;
import orderservice.dto.OrderRequestDto;
import orderservice.dto.OrderResponseDto;
import orderservice.dto.RequestContext;
import orderservice.repository.PurchaseOrderRepository;
import orderservice.util.EntityDtoUtil;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

@Service
public class OrderFulfillmentService {

	@Autowired
	private ProductClient productClient;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	PurchaseOrderRepository orderRepository;
	
	public Mono<OrderResponseDto> procesOrder(Mono<OrderRequestDto> requestDtoMono){//requestDtoMono.map(dto -> new RequestContext(dto)) 
		return requestDtoMono.map(RequestContext::new)
		.flatMap(this::productRequestResponse)
		.doOnNext(EntityDtoUtil::setTransactionRequestDto)
		.flatMap(this::userRequestResponse)
		.map(EntityDtoUtil::getPurchaseOrder)
		.map(this.orderRepository::save) //po -> this.orderRepository.save(po)
		.map(EntityDtoUtil::getPurchaseOrderResponseDTO)
		.subscribeOn(Schedulers.boundedElastic());
	}
	
	private  Mono<RequestContext> productRequestResponse(RequestContext rc) {
	return	this.productClient.getProductById(rc.getOrderRequestDto().getProductId())
		.doOnNext(rc::setProductDto)
		//.retry(3)
		.retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(1)))
		.thenReturn(rc);	
	}
	
	private Mono<RequestContext> userRequestResponse(RequestContext rc){
		return this.userClient.authorizeTransaction(rc.getTransactionRequestDto())
				.doOnNext(rc::setTransactionResponseDto)
				.thenReturn(rc);
		
	}
}
