package iuh.fit.se.techgalaxy.frontend.customerV02.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.exception.AppException;
import iuh.fit.se.techgalaxy.frontend.customerV02.exception.ErrorCode;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.ProfileService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceUImpl implements ProfileService {
	WebClient webClient;

	@Override
	public ApiResponse<List<CustomerResponse>> update(HttpSession session, CustomerRequest customerRequest) {
		try {
			return webClient.put().uri("/customers/profile/{id}", customerRequest.getId())
					.header("Authorization", "Bearer " + session.getAttribute("accessToken")).bodyValue(customerRequest)
					.retrieve().onStatus(HttpStatusCode::is4xxClientError, response -> {
						System.out.println("Status code: " + response.statusCode());
						if (response.statusCode() == HttpStatus.UNAUTHORIZED) {
							throw new AppException(ErrorCode.UNAUTHORIZED_ACCESS);
						}
						return Mono.error(new AppException(ErrorCode.GENERAL_ERROR));
					}).onStatus(HttpStatusCode::is5xxServerError, response -> {
						return Mono.error(new AppException(ErrorCode.SERVER_ERROR));
					}).bodyToMono(new ParameterizedTypeReference<ApiResponse<List<CustomerResponse>>>() {
					}).block();
		} catch (AppException e) {
			return new ApiResponse<>(e.getErrorCode().getCode(), e.getMessage(), null);
		} catch (Exception e) {
			return new ApiResponse<>(ErrorCode.GENERAL_ERROR.getCode(), "An unexpected error occurred.", null);
		}
	}

}
