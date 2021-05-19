package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentCommitRequestDto {

    @NotNull(message = "OrderId can't be null!")
    private String orderId;

}
