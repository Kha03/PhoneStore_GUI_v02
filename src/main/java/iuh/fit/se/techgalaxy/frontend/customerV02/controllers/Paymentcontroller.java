package iuh.fit.se.techgalaxy.frontend.customerV02.controllers;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.PaymentStatus;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.CustomerService;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.EmailService;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.OrderService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping({"/payment"})
@Slf4j
public class Paymentcontroller {
    @GetMapping()
    public String getPayment(@RequestParam("vnp_ResponseCode") String vnp_ResponseCode,@RequestParam("vnp_TxnRef") String vnp_TxnRef ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (vnp_ResponseCode.equals("00")) {
            String address = (String) session.getAttribute("address");
            if (address != null) {
                session.removeAttribute("address");
                return "vnpay_return";
            }
        }
        return "vnpay_return";
    }
}
