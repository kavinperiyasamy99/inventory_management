package com.inventory.handler;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomException extends Exception{

    private static final long serialVersionUID = 1L;

    public CustomException(String s) {
        super(s);
    }
}
