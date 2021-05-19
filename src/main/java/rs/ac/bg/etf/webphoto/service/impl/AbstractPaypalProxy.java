package rs.ac.bg.etf.webphoto.service.impl;

import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import com.paypal.payments.RefundRequest;
import lombok.RequiredArgsConstructor;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.InvoiceItem;
import rs.ac.bg.etf.webphoto.service.PaypalIntegrationService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
abstract class AbstractPaypalProxy implements PaypalIntegrationService {
    private static final Double TAX_VALUE = 0.2;
    private static final DecimalFormat df2 = new DecimalFormat("#.##");

    final PayPalHttpClient client;

    OrderRequest buildOrderRequest(Invoice invoice) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext().brandName("PHOTO SALER").landingPage("BILLING");//.shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<Item> items = new ArrayList<>();

        for (InvoiceItem item : invoice.getInvoiceItems()) {
            double tax = item.getPrice() * TAX_VALUE;
            items.add(new Item()
                    .name(item.getPhotoDetails().getPhoto().getTitle())
                    .quantity("1")
                    .unitAmount(new Money().currencyCode("USD").value(df2.format(item.getPrice() - tax)))
                    .tax(new Money().currencyCode("USD").value(df2.format(tax))));
        }

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId(invoice.getId().toString())
                .description("Photo")
                .customId(invoice.getUser().getId().toString())
                .amountWithBreakdown(
                        new AmountWithBreakdown().currencyCode("USD").value(df2.format(invoice.getAmount() + invoice.getTax()))
                                .amountBreakdown(new AmountBreakdown()
                                        .itemTotal(new Money().currencyCode("USD").value(df2.format(invoice.getAmount())))
                                        .taxTotal(new Money().currencyCode("USD").value(df2.format(invoice.getTax())))
                                )
                )
                .items(items);

        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);

        return orderRequest;
    }

    RefundRequest buildRefundRequest(Invoice invoice) {
        RefundRequest refundRequest = new RefundRequest();
        com.paypal.payments.Money money = new com.paypal.payments.Money();
        money.currencyCode("USD");
        money.value(String.valueOf(invoice.getAmount() + invoice.getTax()));
        refundRequest.amount(money);
        return refundRequest;
    }
}
