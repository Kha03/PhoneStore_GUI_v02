package iuh.fit.se.techgalaxy.frontend.customerV02.service.impl;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.UploadFileResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.FileService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileServiceImpl implements FileService {
    WebClient webClient;

    @Override
    public ApiResponse<List<UploadFileResponse>> uploadFile(MultipartFile file, String folder) {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", file.getResource());
        bodyBuilder.part("folder", folder);
        return webClient.post()
                .uri("/file")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(bodyBuilder.build())
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        clientResponse -> clientResponse.bodyToMono(ApiResponse.class)
                                .flatMap(errorBody -> {
                                    String message = errorBody.getMessage();
                                    return Mono.error(new RuntimeException("Error from API: " + message));
                                })
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<UploadFileResponse>>>() {
                })
                .block();
    }

    @Override
    public List<UploadFileResponse> uploadMultipleFiles(MultipartFile[] files, String folder) {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        if (files == null || files.length == 0) {
            throw new RuntimeException("File list is empty");
        }
        for (MultipartFile file : files) {
            bodyBuilder.part("files", file.getResource());
        }
        bodyBuilder.part("folder", folder);

        ApiResponse<List<List<UploadFileResponse>>> response = webClient.post()
                .uri("/files")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(bodyBuilder.build())
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        clientResponse -> clientResponse.bodyToMono(ApiResponse.class)
                                .flatMap(errorBody -> {
                                    String message = errorBody.getMessage();
                                    return Mono.error(new RuntimeException("Error from API: " + message));
                                })
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<List<UploadFileResponse>>>>() {
                })
                .block();
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            throw new RuntimeException("Error when upload files");
        }
        return response.getData().get(0);
    }
}
