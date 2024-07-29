package com.chargepoint.csms.transactionservice.charging.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverIdentifierDto {

    @NotBlank
    private String id;
}
