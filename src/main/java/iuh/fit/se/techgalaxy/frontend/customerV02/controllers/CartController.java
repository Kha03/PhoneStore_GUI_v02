package iuh.fit.se.techgalaxy.frontend.customerV02.controllers;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.CustomerService;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.impl.CartService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RequestMapping({"/cart"})
@Slf4j
public class CartController {
    CartService cartService;
    CustomerService customerService;

    @GetMapping
    public String getCart(Model model, HttpServletRequest request) {
        cartService.populateCartData(model, request.getSession());
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("accessToken");
        log.info("Access token: {}", token);
        model.addAttribute("token", token);
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model, HttpServletRequest request) {
        cartService.populateCartData(model, request.getSession());
        if (model.getAttribute("cartTotal") == null || (double) model.getAttribute("cartTotal") == 0.0) {
            return "redirect:/cart";
        }
        HttpSession session = request.getSession();
        ApiResponse<List<CustomerResponse>> customerResponse = customerService.getInfoByMail((String) session.getAttribute("email"), session);
        if (customerResponse.getData() != null && !customerResponse.getData().isEmpty()) {
            CustomerResponse customer = customerResponse.getData().get(0);
            model.addAttribute("customer", customer);
            session.setAttribute("customerId", customer.getId());
        }
        return "checkout";
    }

    @PostMapping()
    public String addToCart(@RequestParam String productVariantId,
                            @RequestParam(required = false) String memoryId,
                            @RequestParam(required = false) String colorId,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        cartService.addToCart(productVariantId, memoryId, colorId, session);
        log.info("Added product variant with ID {} to the cart", productVariantId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{productDetailId}")
    public String removeFromCart(@PathVariable String productDetailId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        cartService.removeFromCart(productDetailId, session);
        log.info("Removed product detail with ID {} from the cart", productDetailId);
        return "redirect:/cart";
    }

    @GetMapping("/removeSingle/{productDetailId}")
    public String removeSingleFromCart(@PathVariable String productDetailId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        cartService.removeSingleFromCart(productDetailId, session);
        log.info("Removed single product detail with ID {} from the cart", productDetailId);
        return "redirect:/cart";
    }

    @GetMapping("/add/{productDetailId}")
    public String addQuantity(@PathVariable String productDetailId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        cartService.addQuantity(productDetailId, session);
        log.info("Added quantity of product detail with ID {} to the cart", productDetailId);
        return "redirect:/cart";
    }
}
