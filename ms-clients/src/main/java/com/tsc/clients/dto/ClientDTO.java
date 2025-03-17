package com.tsc.clients.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
    private String identificationNumber;
    private Boolean status;
}
