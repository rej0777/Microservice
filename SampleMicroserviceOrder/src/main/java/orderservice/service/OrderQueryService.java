package orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orderservice.dto.OrderResponseDto;
import orderservice.entity.PurchaseOrder;
import orderservice.repository.PurchaseOrderRepository;
import orderservice.util.EntityDtoUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderQueryService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	public Flux<OrderResponseDto> getProductByUserId(int userId) {
		return Flux.fromStream(()  -> this.purchaseOrderRepository.findByUserId(userId).stream() )
				.map(EntityDtoUtil::getPurchaseOrderResponseDTO)
				.subscribeOn(Schedulers.boundedElastic());
	}
	
}
