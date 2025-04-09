package iuh.fit.se.techgalaxy.frontend.customerV02.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    private String id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
