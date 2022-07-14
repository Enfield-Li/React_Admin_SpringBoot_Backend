package com.example.demo.auth.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService implements UserDetailsService {

  private final ApplicationUserRepository repo;

  public ApplicationUserService(ApplicationUserRepository repo) {
    this.repo = repo;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    return repo
      .findByUsername(username)
      .orElseThrow(
        () ->
          new UsernameNotFoundException(
            String.format("Username %s not found", username)
          )
      );
  }
}
