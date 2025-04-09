package iuh.fit.se.techgalaxy.frontend.customerV02.dto.response;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantResponse {
    String id;
    String name;
    String description;
    String content;
    String avatar;
    Boolean featured;
    ProductStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
