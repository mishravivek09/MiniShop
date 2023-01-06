package com.masai.main.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentUserSession {
	@Id
	@Column(unique = true)
	private int userId;
	
	private String uuid;
	@JsonFormat(pattern="E, dd MMM yyyy HH:mm:ss z")
	private LocalDateTime dateTime;
}
