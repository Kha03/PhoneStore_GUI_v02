package iuh.fit.se.techgalaxy.frontend.customerV02.dto.response;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantDetailResponse {
    String id;
    String name;
    String imgAva;
    ProductStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Map<String, List<ColorQuantity>> memories;

}
