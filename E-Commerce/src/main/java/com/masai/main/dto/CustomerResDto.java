package com.masai.main.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResDto {
	private int id;
	private String name;
	private String email;
	private String message;
	@JsonFormat(pattern="E, dd MMM yyyy HH:mm:ss")
	private LocalDateTime dateTime;
}
