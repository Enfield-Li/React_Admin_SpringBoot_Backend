package com.example.demo.controller;

import static com.example.demo.util.Constants.*;

import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.RegisterUserDto;
import com.example.demo.dto.UserLoginResponseDto;
import com.example.demo.dto.UserRegisterResponseDto;
import com.example.demo.entity.ApplicationUser;
import com.example.demo.repository.ApplicationUserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User_Auth")
@RequestMapping(USER_AUTH)
class ApplicationUserController {

  private final ApplicationUserRepository userRepo;
  private final PasswordEncoder passwordEncoder;

  public ApplicationUserController(
    ApplicationUserRepository userRepo,
    PasswordEncoder passwordEncoder
  ) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping(TEST)
  public String test() {
    return "Have access";
  }

  @PostMapping(LOGOUT)
  public void logout() {}

  @PostMapping(LOGIN)
  public void login(@Valid @RequestBody LoginUserDto dto) {}

  @GetMapping("me")
  public Authentication me() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
    userRepo.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping(REGISTER)
  public ResponseEntity<UserRegisterResponseDto> register(
    @Valid @RequestBody RegisterUserDto dto,
    HttpSession session
  ) {
    try {
      ApplicationUser newUser = new ApplicationUser();
      newUser.setRole(dto.getRole());
      newUser.setUsername(dto.getUsername());
      newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

      ApplicationUser savedUser = userRepo.save(newUser);
      session.setAttribute(ApplicationUserInSession, savedUser);

      return ResponseEntity.ok(
        new UserRegisterResponseDto(new UserLoginResponseDto(savedUser), null)
      );
    } catch (DataIntegrityViolationException err) {
      return ResponseEntity
        .badRequest()
        .body(new UserRegisterResponseDto(null, "Username already exist."));
    }
  }
}
