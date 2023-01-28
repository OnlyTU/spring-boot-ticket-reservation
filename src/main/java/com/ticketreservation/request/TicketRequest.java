package com.ticketreservation.request;

import com.ticketreservation.enums.PaymentType;
import com.ticketreservation.model.Trip;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {
    private Integer no;
    private Trip trip;
    private Integer maleQuantity;
    private Integer femaleQuantity;
    private PaymentType paymentType;
}
