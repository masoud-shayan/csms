package com.chargepoint.csms.common.type.message.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest {

    private String stationUuid;
    private String driverIdentifier;
}
