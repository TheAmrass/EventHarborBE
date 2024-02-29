package com.osu.venglar.EventHarbor.model;

import com.osu.venglar.EventHarbor.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.util.Date;

@Getter
@Setter

@Entity //Automaticaly generate MySQL table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @NotNull
    private String password;
    @NotNull
    private Date registered;
    @NotNull
    private Date lastLogged;

    }

