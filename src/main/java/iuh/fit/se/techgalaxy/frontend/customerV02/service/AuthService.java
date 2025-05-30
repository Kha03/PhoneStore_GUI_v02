package iuh.fit.se.techgalaxy.frontend.customerV02.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    public ResponseEntity<Map> login(String username, String password, HttpSession session, HttpServletResponse response);

    void logout(HttpSession session, String accessToken, HttpServletResponse response);
    public ResponseEntity<Map> register(String email, String password, String name);
}
