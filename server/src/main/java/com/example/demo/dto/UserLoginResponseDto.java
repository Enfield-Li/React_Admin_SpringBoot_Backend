package com.example.demo.dto;

import com.example.demo.entity.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginResponseDto {

  @NotNull
  private Long id;

  @NotNull
  private String fullName;

  @NotNull
  private String avatar;

  public UserLoginResponseDto(ApplicationUser user) {
    this.id = user.getId();
    this.fullName = user.getUsername();
    this.avatar = "https://robohash.org/" + user.getUsername() + ".png";
  }

  public static String toJSON(ApplicationUser user) throws IOException {
    UserLoginResponseDto dto = new UserLoginResponseDto(user);
    return new ObjectMapper().writeValueAsString(dto);
  }
}
