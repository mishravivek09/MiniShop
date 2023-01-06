package com.masai.main.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorDetails {
	@JsonFormat(pattern="E, dd MMM yyyy HH:mm:ss")
	private LocalDateTime dateTime;
	private String message;
	private String details;
}
