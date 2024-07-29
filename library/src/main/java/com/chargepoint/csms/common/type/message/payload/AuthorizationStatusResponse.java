package com.chargepoint.csms.common.type.message.payload;

import lombok.Getter;

@Getter
public enum AuthorizationStatusResponse {
    ACCEPTED,
    INVALID,
    UNKNOWN,
    REJECTED
}
