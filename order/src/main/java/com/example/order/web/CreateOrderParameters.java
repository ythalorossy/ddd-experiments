package com.example.order.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.order.web.validators.ValidCreateOrderParameters;

import lombok.Data;

@Data
@ValidCreateOrderParameters
public class CreateOrderParameters {

    @NotNull
    @NotEmpty
    String[] plates;
    
    @NotNull
    @NotEmpty
    String[] drinks;

    @NotNull
    String orderType;
}