package userservice.entity;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class UserTransaction {

	private Integer id;
	private Integer userId;
	private Integer amount;
	private LocalDateTime transactionDate;
	
	
}
