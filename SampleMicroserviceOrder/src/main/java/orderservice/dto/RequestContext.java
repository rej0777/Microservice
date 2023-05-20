package orderservice.dto;

import lombok.Data;

@Data
public class RequestContext {

	private OrderRequestDto orderRequestDto;
	private OrderResponseDto orderResponseDto;
	
	private ProductDto productDto;
	private TransactionRequestDto transactionRequestDto;
	private TransactionResponseDto transactionResponseDto;
	
	public RequestContext(OrderRequestDto orderRequestDto) {
		this.orderRequestDto = orderRequestDto;
	}
	
	
}
