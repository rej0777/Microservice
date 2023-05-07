package userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import userservice.dto.UserDto;
import userservice.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("all")
	public Flux<UserDto> all(){
		return this.userService.all();
	}
	
	@GetMapping("{id}")
	public Mono<ResponseEntity<UserDto>> getUserById(@PathVariable int id){
		return this.userService.getUserById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());							
	}
	
	@PostMapping
	public Mono<UserDto> createUser(@RequestBody Mono<UserDto> userDTOMono){
		return this.userService.cresteUser(userDTOMono);
		
	}
	
	@PutMapping("{id}")
	public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable int id, @RequestBody Mono<UserDto>userDtoMono){
		return this.userService.updateUser(id, userDtoMono)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public Mono<Void> deleteUser(@PathVariable int id){
		return this.userService.deleteUser(id);
	}
	
	
}
