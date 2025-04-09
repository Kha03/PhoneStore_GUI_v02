package iuh.fit.se.techgalaxy.frontend.customerV02.entities;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.DetailStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    private String id;

    private DetailStatus detailStatus;

    private Order order;

    private ProductVariantDetail productVariantDetail;

    private Integer quantity;

    private Double price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
