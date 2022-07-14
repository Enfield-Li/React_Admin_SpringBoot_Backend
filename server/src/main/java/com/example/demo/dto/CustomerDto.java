package com.example.demo.dto;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

  private Long id;
  private String email;
  private String first_name;
  private String last_name;
  private String address;
  private String zipcode;
  private String city;
  private String avatar;
  private String stateAbbr;
  private Instant birthday;
  private Instant first_seen;
  private Instant last_seen;
  private Instant latest_purchase;
  private Boolean has_ordered;
  private Boolean has_newsletter;
  private Integer nb_commands;
  private Float total_spent;
  private String groupsStr;
  private List<String> groups;
}
