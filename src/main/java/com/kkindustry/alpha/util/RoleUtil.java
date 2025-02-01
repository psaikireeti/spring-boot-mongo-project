package com.kkindustry.alpha.util;

import com.kkindustry.alpha.constant.SecurityConstants;
import com.kkindustry.alpha.enums.RoleEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class RoleUtil {
  public static boolean checkAdminAccess() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    for (GrantedAuthority role : auth.getAuthorities()) {
      if (role.toString().equals(SecurityConstants.ROLE_ADMIN)) {
        return true;
      }
    }
    return false;
  }

  public static RoleEnum getUserRole() {
    return RoleEnum.valueOf(
        SecurityContextHolder.getContext()
            .getAuthentication()
            .getAuthorities()
            .toArray()[0]
            .toString());
  }
}
