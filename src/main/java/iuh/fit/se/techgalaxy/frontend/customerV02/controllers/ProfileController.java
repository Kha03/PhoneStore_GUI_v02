package iuh.fit.se.techgalaxy.frontend.customerV02.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.UploadFileResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.FileService;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import iuh.fit.se.techgalaxy.frontend.customerV02.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.frontend.customerV02.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.frontend.customerV02.service.CustomerService;
import iuh.fit.se.techgalaxy.frontend.customerV02.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RequestMapping({"/profile"})
@Slf4j
public class ProfileController {
    CustomerService customerService;
    ProfileService profileService;
    FileService fileService;

    @GetMapping()
    public String getProfile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        System.out.println("Email: " + email);
        // Check if email exists in session
        if (email == null) {
            model.addAttribute("error", "User is not logged in.");
            return "redirect:/login"; // Redirect to login page
        }

        ApiResponse<List<CustomerResponse>> customerResponse = customerService
                .getInfoByMail((String) session.getAttribute("email"), session);
        CustomerResponse customerResponse1 = customerResponse.getData().get(0);
        if (customerResponse1 == null) {
            model.addAttribute("error", "Not customer");
            return "redirect:/home";

        }
        session.setAttribute("username", customerResponse1.getName());
        session.setAttribute("profileImage", customerResponse1.getAvatar());
        System.out.println("Profile Image: " + customerResponse1.getAvatar());
        CustomerResponse customer = customerResponse.getData().get(0);
        System.out.println("Customer: " + customer.getName());
        List<Map<String, String>> fields = List.of(
                Map.of("label", "Full Name", "icon", "fas fa-user", "type", "text", "value", customer.getName(), "id",
                        "fullName", "modal", "#editFullNameModal"),
                Map.of("label", "E-mail Address", "icon", "fas fa-envelope", "type", "email", "value",
                        customer.getAccount().getEmail() != null ? customer.getAccount().getEmail() : "", "id",
                        "emailAddress", "modal", "#editEmailModal"),
                Map.of("label", "Phone Number", "icon", "fas fa-phone", "type", "text", "value",
                        customer.getPhone() != null ? customer.getPhone() : "", "id", "phoneNumber", "modal",
                        "#editPhoneModal"),
                Map.of("label", "Date of Birth", "icon", "fas fa-calendar-alt", "type", "date", "value",
                        customer.getDateOfBirth() != null ? customer.getDateOfBirth().toString() : "", "id",
                        "dateOfBirth", "modal", "#editDateOfBirthModal"),
                Map.of("label", "Avatar", "icon", "fas fa-file-image", "type", "file", "value",
                        customer.getAvatar() != null ? customer.getAvatar() : "", "id", "avatar", "modal",
                        "#editAvatarModal")
        );

        // Add fields to the model
        model.addAttribute("fields", fields);
        model.addAttribute("customer", customer);

        return "PersonalData";
    }

    @PostMapping("/save")
    public String saveProfile(
            @RequestParam Map<String, String> formData,
            @RequestParam(value = "avatar", required = false) MultipartFile file,
            Model model,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId(formData.get("id"));
        customerRequest.setName(formData.get("fullName"));

        String email = formData.get("emailAddress");
        String phone = formData.get("phoneNumber");
        customerRequest.setPhone(phone);
        // Optional Date of Birth Handling
        if (formData.get("dateOfBirth") != null && !formData.get("dateOfBirth").isBlank()) {
            LocalDate date = LocalDate.parse(formData.get("dateOfBirth"));
            customerRequest.setDateOfBirth(date);
        } else {
            customerRequest.setDateOfBirth(null); // Handle null date
        }

        // Optional Avatar Upload Handling
        if (file != null && !file.isEmpty()) {
            ApiResponse<List<UploadFileResponse>> response = fileService.uploadFile(file, "customer/avatar");
            UploadFileResponse uploadFileResponse = response.getData().get(0);
            customerRequest.setAvatar(uploadFileResponse.getFileName());
        } else {
            if (session.getAttribute("profileImage") != null) {
                customerRequest.setAvatar(session.getAttribute("profileImage").toString());
            }
        }
        System.out.println("Avatar: " + customerRequest.getAvatar());
        System.out.println("Customer Request: " + customerRequest);
        // Call the service to update the customer profile
        ApiResponse<List<CustomerResponse>> customerResponse = profileService.update(session, customerRequest);

        // If update is successful, update session and model
        if (customerResponse.getData() != null && !customerResponse.getData().isEmpty()) {
            CustomerResponse customer = customerResponse.getData().get(0);

            List<Map<String, String>> fields = List.of(
                    Map.of("label", "Full Name", "icon", "fas fa-user", "type", "text", "value", customer.getName(), "id",
                            "fullName", "modal", "#editFullNameModal"),
                    Map.of("label", "E-mail Address", "icon", "fas fa-envelope", "type", "email", "value",
                            customer.getAccount().getEmail() != null ? customer.getAccount().getEmail() : "", "id",
                            "emailAddress", "modal", "#editEmailModal"),
                    Map.of("label", "Phone Number", "icon", "fas fa-phone", "type", "text", "value",
                            customer.getPhone() != null ? customer.getPhone() : "", "id", "phoneNumber", "modal",
                            "#editPhoneModal"),
                    Map.of("label", "Date of Birth", "icon", "fas fa-calendar-alt", "type", "date", "value",
                            customer.getDateOfBirth() != null ? customer.getDateOfBirth().toString() : "", "id",
                            "dateOfBirth", "modal", "#editDateOfBirthModal"),
                    Map.of("label", "Avatar", "icon", "fas fa-file-image", "type", "file", "value",
                            customer.getAvatar() != null ? customer.getAvatar() : "", "id", "avatar", "modal",
                            "#editAvatarModal")
            );

            // Add updated fields to the model
            model.addAttribute("fields", fields);
            model.addAttribute("customer", customer);

            // Update session attributes
            session.setAttribute("username", customer.getName());
            session.setAttribute("profileImage", customer.getAvatar());
        }

        // Redirect to the profile page after update
        return "redirect:/profile";
    }

}
