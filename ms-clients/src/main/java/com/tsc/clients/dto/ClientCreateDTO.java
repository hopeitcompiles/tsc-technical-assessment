package com.tsc.clients.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClientCreateDTO {
    @NotEmpty
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
    @NotNull(message = "Identification number cannot be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String identificationNumber;
    @NotEmpty
    private String password;
}
