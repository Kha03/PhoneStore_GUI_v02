package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.UploadFileResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    ApiResponse<List<UploadFileResponse>> uploadFile(MultipartFile file, String folder);

    List<UploadFileResponse> uploadMultipleFiles(MultipartFile[] files, String folder);
}
