package orderservice.dto;

import lombok.Data;

@Data
public class OrderResponseDto {

	private Integer orderId;
	private Integer userId;	
	private String productId;	
	private Integer amount;
	private OrderStatus status;
	
	
}
