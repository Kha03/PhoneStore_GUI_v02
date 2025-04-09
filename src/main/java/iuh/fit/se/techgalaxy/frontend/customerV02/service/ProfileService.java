package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import java.util.List;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;

public interface ProfileService {
	
	ApiResponse<List<CustomerResponse>> update(HttpSession session, CustomerRequest customerRequest);

}
