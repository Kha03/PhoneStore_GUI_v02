package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.OrderResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.PaymentMethod;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.PaymentStatus;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface OrderService {

    ApiResponse<List<OrderResponse>> createOrder(String address, HttpSession session, PaymentStatus paymentStatus, PaymentMethod paymentMethod);
	  ApiResponse<List<OrderResponse>> getOrderByCustomerId(String cusId, HttpSession session);
	  ApiResponse<List<OrderResponse>> getOrderById(String id, HttpSession session);

}
