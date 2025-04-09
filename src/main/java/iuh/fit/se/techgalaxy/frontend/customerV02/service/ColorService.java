package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.Color;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;

import java.util.List;
import java.util.Set;

public interface ColorService {

    ApiResponse<List<Color>> getColorsById(Set<String> ids);
}
