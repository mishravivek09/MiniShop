package com.masai.main.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.masai.main.myenum.Bank;
import com.masai.main.myenum.Ifsc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResDto {
	private Bank bankName;
	private Ifsc ifsc;
	private int accountNo;
	private double amount;
	private double totalBalance;
	@JsonFormat(pattern="E, dd MMM yyyy HH:mm:ss")
	private LocalDateTime dateTime;
}
