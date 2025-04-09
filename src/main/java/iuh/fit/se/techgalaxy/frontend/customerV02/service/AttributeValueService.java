package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.ValueResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;

import java.util.List;

public interface AttributeValueService {
    ApiResponse<List<ValueResponse>> getValueByDriveSize();
    ApiResponse<List<ValueResponse>> getValueByScanFre();

    ApiResponse<List<ValueResponse>> getValueByVariantId(String variantId);

}
