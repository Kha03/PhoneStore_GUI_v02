package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpSession;

public interface PaymentService {

    PaymentResponse.VNPayResponseCreate createVnPayPayment(int amount, HttpSession session);
}
