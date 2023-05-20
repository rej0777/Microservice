package orderservice.util;

import org.springframework.beans.BeanUtils;

import orderservice.dto.OrderRequestDto;
import orderservice.dto.OrderResponseDto;
import orderservice.dto.OrderStatus;
import orderservice.dto.RequestContext;
import orderservice.dto.TransactionRequestDto;
import orderservice.dto.TransactionStatus;
import orderservice.entity.PurchaseOrder;

public class EntityDtoUtil {
	
	
	public static OrderResponseDto getPurchaseOrderResponseDTO(PurchaseOrder purchaseOrder) {
		OrderResponseDto dto = new OrderResponseDto();
		BeanUtils.copyProperties(purchaseOrder, dto);
		dto.setOrderId(purchaseOrder.getId());
		return dto;
		
		
	}
	
	
	
	public static void setTransactionRequestDto(RequestContext rc) {	
		TransactionRequestDto dto = new TransactionRequestDto();
		dto.setUserId(rc.getOrderRequestDto().getUserId());
		dto.setAmount(rc.getProductDto().getPrice());
		rc.setTransactionRequestDto(dto);		
	}

	public static PurchaseOrder getPurchaseOrder(RequestContext rc) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setUserId(rc.getOrderRequestDto().getUserId());
		purchaseOrder.setProductId(rc.getOrderRequestDto().getProductId());
		purchaseOrder.setAmount(rc.getProductDto().getPrice());
		
		TransactionStatus status = rc.getTransactionResponseDto().getStatus();
		OrderStatus orderStatus = TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;		
		purchaseOrder.setStatus(orderStatus);
		
		return purchaseOrder;
		
	}
}
