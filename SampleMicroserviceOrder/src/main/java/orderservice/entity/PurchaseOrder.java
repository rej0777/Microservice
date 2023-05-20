package orderservice.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import orderservice.dto.OrderStatus;

@Data
@Entity
public class PurchaseOrder {

	@Id
	@GeneratedValue 
	private Integer id;
	
	private String productId;
	private Integer userId;
	private Integer amount;
	private OrderStatus status;
	
	
}
