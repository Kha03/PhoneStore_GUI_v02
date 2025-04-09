package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import java.util.List;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;

public interface OrderDetailService {
	
	ApiResponse<List<OrderDetailResponse>> orderDetailByOrderId(String orderId , HttpSession session);
}
