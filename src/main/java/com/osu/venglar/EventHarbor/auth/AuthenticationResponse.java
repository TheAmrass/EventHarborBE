package com.osu.venglar.EventHarbor.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.osu.venglar.EventHarbor.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Created for AuthenticationController

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("logged_id")
    private Long loggedId;
    @JsonProperty("role")
    private Role role;
}
