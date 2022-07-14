package com.example.demo.auth.user;

import static com.example.demo.auth.user.ApplicationUserPermission.*;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
  INTERN(Sets.newHashSet()),
  FORMAL(Sets.newHashSet(READ_SALES, WRITE_REVIEWS)),
  ADMIN(Sets.newHashSet(READ_SALES, WRITE_REVIEWS, WRITE_CATEGORIES, WRITE_SALES));

  private final Set<ApplicationUserPermission> permissions;

  ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
    this.permissions = permissions;
  }

  public Set<ApplicationUserPermission> getPermissions() {
    return permissions;
  }

  public List<GrantedAuthority> getGrantedAuthorities() {
    List<GrantedAuthority> grantedAuthorities = getPermissions()
      .stream()
      .map(
        permission ->
          (GrantedAuthority) new SimpleGrantedAuthority(
            permission.getPermission()
          )
      )
      .collect(Collectors.toList());

    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return grantedAuthorities;
  }
}
