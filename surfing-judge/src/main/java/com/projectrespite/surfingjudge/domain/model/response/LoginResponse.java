package com.projectrespite.surfingjudge.domain.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String status;
    private String role;
}
