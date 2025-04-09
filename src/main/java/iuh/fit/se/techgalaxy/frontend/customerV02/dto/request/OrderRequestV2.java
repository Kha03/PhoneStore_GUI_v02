package iuh.fit.se.techgalaxy.frontend.customerV02.dto.request;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.OrderStatus;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestV2 {
    String customerId;
    String systemUserId;
    String address;
    PaymentStatus paymentStatus = PaymentStatus.PENDING;
    OrderStatus orderStatus = OrderStatus.NEW;
    List<ProductDetailOrder> productDetailOrders;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
     public static class ProductDetailOrder {
        String productVariantDetailId;
        int quantity;
    }
}
