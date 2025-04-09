package iuh.fit.se.techgalaxy.frontend.customerV02.service.impl;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.Memory;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.MemoriesService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemoriesServiceImpl implements MemoriesService {
    WebClient webClient;


    @Override
    public ApiResponse<List<Memory>> getAllMemories() {
        try {
            return webClient.get()
                    .uri("/memories")
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiResponse<List<Memory>>>() {
                    })
                    .block().getBody();
        } catch (NullPointerException e) {
            log.error("Error: ", e);
            return null;
        }
    }

    @Override
    public ApiResponse<List<Memory>> getMemoriesById(Set<String> ids) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/memories/ids")
                            .queryParam("ids", ids)
                            .build())
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiResponse<List<Memory>>>() {
                    })
                    .block().getBody();
        } catch (NullPointerException e) {
            log.error("Error: ", e);
            return null;
        }
    }
}
