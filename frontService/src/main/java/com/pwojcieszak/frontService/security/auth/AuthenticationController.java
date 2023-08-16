package com.pwojcieszak.frontService.security.auth;

import com.pwojcieszak.frontService.security.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @ModelAttribute("regReq") RegisterRequest request,
             HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public String authenticate(
            @ModelAttribute("authReq") AuthenticationRequest request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws ServletException, IOException {
        service.authenticate(request);
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("/front/");
        handler.setAlwaysUseDefaultTargetUrl(true);
        handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, null);

        return "redirect:/";
    }
}
