package iuh.fit.se.techgalaxy.frontend.customerV02.service.impl;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.request.ProductFeedbackRequestV2;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.ProductFeedbackResponseV2;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.UploadFileResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.CustomerService;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.FileService;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.ProductFeedBackService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class ProductFeedBackServiceImpl implements ProductFeedBackService {
    WebClient webClient;
    CustomerService customerService;
    FileService fileService;


    @Override
    public void upFeedBack(String content, String variantId, MultipartFile[] files, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            CustomerResponse customerResponse = customerService.getInfoByMail(email, session).getData().get(0);
            String id = customerResponse.getId();
            ProductFeedbackRequestV2 request = new ProductFeedbackRequestV2();
            request.setCustomerId(id);
            request.setProductVariantId(variantId);
            request.setFeedbackText(content);
            if (files != null && files.length > 0) {
                List<MultipartFile> validFiles = Arrays.stream(files)
                        .filter(file -> file != null && !file.isEmpty())
                        .toList();
                if (!validFiles.isEmpty()) {
                    List<UploadFileResponse> response = fileService.uploadMultipleFiles(
                            validFiles.toArray(new MultipartFile[0]),
                            "productfeedback"
                    );
                    if (response != null && !response.isEmpty()) {
                        request.setImagePaths(response.stream()
                                .map(p -> "productfeedback/" + p.getFileName())
                                .toList());
                    }
                }
            }
            webClient.post()
                    .uri("/product-feedbacks/v2")
                    .header("Authorization", "Bearer " + session.getAttribute("accessToken"))
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(ApiResponse.class)
                    .block();
        }
    }


    @Override
    public ApiResponse<List<ProductFeedbackResponseV2>> getFeedBacks(String variantId) {
        return webClient.get()
                .uri("/product-feedbacks/product-variantv2/" + variantId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<ProductFeedbackResponseV2>>>() {
                })
                .block();
    }
}
