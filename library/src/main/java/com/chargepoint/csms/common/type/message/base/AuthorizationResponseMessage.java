package com.chargepoint.csms.common.type.message.base;

import com.chargepoint.csms.common.type.message.payload.AuthorizationStatusResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthorizationResponseMessage extends EventMessage<AuthorizationStatusResponse> {

    public AuthorizationResponseMessage(AuthorizationStatusResponse payload) {
        super(payload);
    }

}
