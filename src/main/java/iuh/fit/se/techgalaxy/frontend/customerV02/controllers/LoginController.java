package iuh.fit.se.techgalaxy.frontend.customerV02.controllers;


import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import iuh.fit.se.techgalaxy.frontend.customerV02.service.impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    private final AuthServiceImpl authService;

    @Autowired
    public LoginController( AuthServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping("/signin")
    public String showLoginPage(Model model) {
        model.addAttribute("errorMessage", null);

        return "signin";
    }
    @GetMapping("/main")
    public String showMainPage() {
        return "main"; // Ensure 'main.jsp' is located in the correct directory (e.g., `src/main/webapp/WEB-INF/views/`).
    }


    @PostMapping("/signin")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        HttpServletResponse response,
                        Model model,
                        RedirectAttributes redirectAttributes
                        ) {

        ResponseEntity<Map> loginResponse = authService.login(username, password, session, response);
        
        if (loginResponse.getStatusCode().is2xxSuccessful()) {
            redirectAttributes.addFlashAttribute("successMessage", "Login successfully!");
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Login failed!");
            model.addAttribute("errorMessage", "Login failed!");
            return "redirect:/signin";
        }
    }



    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session, HttpServletResponse response) {
        String accessToken = (String) session.getAttribute("accessToken");

        if (accessToken != null) {
            authService.logout(session, accessToken, response);
        }

        return new ModelAndView("redirect:/signin");
    }

}
