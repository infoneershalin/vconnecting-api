package murraco.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_USER,ROLE_EXPERT;

  public String getAuthority() {
    return name();
  }

}
