package com.osu.venglar.EventHarbor.auth;


import com.osu.venglar.EventHarbor.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
    private Date registered;
    private Date lastLogged;
}
