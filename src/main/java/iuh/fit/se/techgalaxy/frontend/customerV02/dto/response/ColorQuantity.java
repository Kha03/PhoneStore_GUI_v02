package iuh.fit.se.techgalaxy.frontend.customerV02.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ColorQuantity {
    Integer viewsCount;
    Double price;
    Double sale;
    Integer quantity;
    String colorId;
}
