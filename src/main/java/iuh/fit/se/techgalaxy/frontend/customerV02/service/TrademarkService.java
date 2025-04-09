package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import iuh.fit.se.techgalaxy.frontend.customerV02.entities.Trademark;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;

import java.util.List;

public interface TrademarkService {
    ApiResponse<List<Trademark>> getTrademarkAll();
}
