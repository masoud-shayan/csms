package com.chargepoint.csms.common.type.message.base;

import com.chargepoint.csms.common.type.message.payload.AuthorizationRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthorizationRequestMessage extends EventMessage<AuthorizationRequest> {

    public AuthorizationRequestMessage(AuthorizationRequest payload) {
        super(payload);
    }
}
