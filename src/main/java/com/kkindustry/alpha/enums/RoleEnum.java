package com.kkindustry.alpha.enums;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
  ROLE_ADMIN,
  ROLE_DOCTOR,
  ROLE_RECEPTIONIST,
  ROLE_PHARMACIST,
  ROLE_PATIENT;

  public static final List<String> roleList =
      Arrays.asList("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_RECEPTIONIST", "ROLE_PHARMACIST", "ROLE_PATIENT");
}
