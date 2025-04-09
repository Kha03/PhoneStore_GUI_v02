package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.Memory;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;

import java.util.List;
import java.util.Set;

public interface MemoriesService {

    ApiResponse<List<Memory>> getAllMemories();

    ApiResponse<List<Memory>> getMemoriesById(Set<String> ids);
}
