
package com.elcom.management_library_common.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    
    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
