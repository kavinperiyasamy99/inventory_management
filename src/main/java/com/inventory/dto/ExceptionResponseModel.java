package com.inventory.dto;

import com.inventory.io.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private StatusMessage statusMessage;

}
