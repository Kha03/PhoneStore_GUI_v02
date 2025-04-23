package iuh.fit.se.techgalaxy.frontend.customerV02.dto.response;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.OrderStatus;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    CustomerResponseV2 customer;
    SystemUserResponse systemUser;
    String address;
    PaymentStatus paymentStatus;
    OrderStatus orderStatus;
    List<OrderDetailResponse> orderDetails;
    LocalDateTime createdAt;
    String paymentLink;
}
