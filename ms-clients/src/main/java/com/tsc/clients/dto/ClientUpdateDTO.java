package com.tsc.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientUpdateDTO {
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String identificationNumber;
    private Boolean status;
    private String password;
}
