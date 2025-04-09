package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.UsageCategoryResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;

import java.util.List;

public interface UsageCategoryService {
    ApiResponse<List<UsageCategoryResponse>> getAllUsageCategories();
}
