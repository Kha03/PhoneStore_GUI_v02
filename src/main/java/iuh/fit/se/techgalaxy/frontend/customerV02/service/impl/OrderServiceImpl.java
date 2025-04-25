package iuh.fit.se.techgalaxy.frontend.customerV02.service.impl;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.request.OrderRequestV2;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.OrderResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.ProductDetailResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.OrderStatus;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.PaymentMethod;
import iuh.fit.se.techgalaxy.frontend.customerV02.entities.enumeration.PaymentStatus;
import iuh.fit.se.techgalaxy.frontend.customerV02.exception.AppException;
import iuh.fit.se.techgalaxy.frontend.customerV02.exception.ErrorCode;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.OrderService;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.ProductService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    WebClient webClient;
    ProductService productService;

    @Override
    public ApiResponse<List<OrderResponse>> createOrder(String address, HttpSession session, PaymentStatus paymentStatus, PaymentMethod paymentMethod) {
        String customerId = (String) session.getAttribute("customerId");
        String accessToken = (String) session.getAttribute("accessToken");
        if (customerId == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_ACTIVE);
        }

        OrderRequestV2.OrderRequestV2Builder orderRequestBuilder = OrderRequestV2.builder()
                .customerId(customerId)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentStatus)
                .orderStatus(OrderStatus.NEW)
                .address(address);

        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            throw new AppException(ErrorCode.CART_EMPTY);
        }

        List<OrderRequestV2.ProductDetailOrder> orderItems = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String productVariantDetailId=entry.getKey();
            Integer quantity= entry.getValue();
            List<String> productIds = List.copyOf(Collections.singleton(productVariantDetailId));
            ApiResponse<List<ProductDetailResponse>> productDetails = productService.getProductDetailsByIds(productIds);
            List<ProductDetailResponse> productDetailResponses = productDetails.getData();
            Double price= productDetailResponses.get(0).getPrice();
            System.out.println(productDetailResponses);
            orderItems.add(new OrderRequestV2.ProductDetailOrder(productVariantDetailId, quantity,price));
        }

        orderRequestBuilder.productDetailOrders(orderItems);
        OrderRequestV2 orderRequest = orderRequestBuilder.build();

        try {
            ApiResponse<List<OrderResponse>> response = webClient.post()
                    .uri("/orders/v2")
                    .header("Authorization", "Bearer " + accessToken)
                    .bodyValue(orderRequest)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, res -> {
                        // Nếu có lỗi về số lượng
                        return res.bodyToMono(ApiResponse.class)
                                .flatMap(body -> {
                                    if (body.getMessage().contains("Insufficient product quantity")) {
                                        throw new AppException(ErrorCode.INSUFFICIENT_PRODUCT_QUANTITY, body.getMessage());
                                    }
                                    return Mono.error(new AppException(ErrorCode.UNCATEGORIZED_ERROR, body.getMessage()));
                                });
                    })
                    .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<OrderResponse>>>() {})
                    .block();
            session.removeAttribute("cart");
            return response;
        } catch (AppException ex) {
            throw new AppException(ex.getErrorCode(), ex.getMessage());
        }
    }


	@Override
	public ApiResponse<List<OrderResponse>> getOrderByCustomerId(String cusId, HttpSession session) {
		try {
			  String accessToken = (String) session.getAttribute("accessToken");

			return webClient.get().uri("/orders/customer/" + cusId)
					 .header("Authorization", "Bearer " + accessToken)
					
					.retrieve()
					.toEntity(new ParameterizedTypeReference<ApiResponse<List<OrderResponse>>>() {
					}).block().getBody();
		} catch (NullPointerException e) {
			log.error("Error: ", e);
			return null;
		}
	}

	@Override
	public ApiResponse<List<OrderResponse>> getOrderById(String id, HttpSession session) {
		try {
			 String accessToken = (String) session.getAttribute("accessToken");
			
			return webClient.get().uri("/orders/" + id)
					 .header("Authorization", "Bearer " + accessToken)
					.retrieve()
					.toEntity(new ParameterizedTypeReference<ApiResponse<List<OrderResponse>>>() {
					}).block().getBody();
		} catch (NullPointerException e) {
			log.error("Error: ", e);
			return null;
		}
	}

}
