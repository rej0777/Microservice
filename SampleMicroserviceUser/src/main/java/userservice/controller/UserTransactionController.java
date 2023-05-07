package userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import userservice.dto.TransactionRequestDto;
import userservice.dto.TransactionResponseDto;
import userservice.entity.UserTransaction;
import userservice.service.TransactionService;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono){		
		return requestDtoMono.flatMap(this.transactionService::createTransaction);		
	}
	
	@GetMapping
	public Flux<UserTransaction> getByUserId(@RequestParam("userId")int userId ){
		return this.transactionService.getByUserId(userId);
	}
	
}
