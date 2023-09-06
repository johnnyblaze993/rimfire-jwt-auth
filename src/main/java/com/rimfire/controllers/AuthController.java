package com.rimfire.controllers;

import com.rimfire.dto.LoginAuthenticationRequest;
import com.rimfire.entities.Users;
import com.rimfire.services.AuthService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import java.util.Optional;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/auth")
public class AuthController {

  @Inject
  private AuthService authService;

  @Post("/login")
  public HttpResponse<?> login(@Body LoginAuthenticationRequest request) {
    Users user = authService.authenticateUser(
      request.getUsername(),
      request.getFirstName()
    );

    if (user != null) {
      Optional<String> tokenOptional = authService.generateTokenForUser(
        user.getUserId().toString()
      );
      if (tokenOptional.isPresent()) {
        return HttpResponse
          .ok()
          .header("Authorization", "Bearer " + tokenOptional.get())
          .body("Login successful");
      } else {
        return HttpResponse.serverError();
      }
    } else {
      return HttpResponse.notFound().body("User not found");
    }
  }

  @Post("/logout")
  public HttpResponse<String> logout(@Header String Authorization) {
    authService.handleLogout(Authorization.replace("Bearer ", ""));
    return HttpResponse.ok("Logged out successfully");
  }
}
