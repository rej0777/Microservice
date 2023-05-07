package userservice.util;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import userservice.dto.TransactionRequestDto;
import userservice.dto.TransactionResponseDto;
import userservice.dto.TransactionStatus;
import userservice.dto.UserDto;
import userservice.entity.User;
import userservice.entity.UserTransaction;

public class EntityDtoUtil {
	
	public static UserDto toDto(User user) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}

	public static User toEntity(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}
	
	public static UserTransaction toEntity(TransactionRequestDto requestDto) {
		UserTransaction ut = new UserTransaction();
		ut.setUserId(requestDto.getUserId());
		ut.setAmount(requestDto.getAmount());
		ut.setTransactionDate(LocalDateTime.now());
		return ut;		
	}
	
	public static TransactionResponseDto toDto(TransactionRequestDto reqDto, TransactionStatus status) {
		TransactionResponseDto responseDto = new TransactionResponseDto();
		responseDto.setAmount(reqDto.getAmount());
		responseDto.setUserId(reqDto.getUserId());
		responseDto.setStatus(status);
		return responseDto;
	}
}
