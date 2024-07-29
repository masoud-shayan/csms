package com.chargepoint.csms.transactionservice.charging.message.service;

import com.chargepoint.csms.common.type.message.base.EventMessage;

public interface RequestReplyService {

    EventMessage<?> send(EventMessage<?> eventMessage);
}
