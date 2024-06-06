package com.osu.venglar.EventHarbor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osu.venglar.EventHarbor.model.enums.Priority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data //Contains Getters, Setters, toString and more
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity //Automaticaly generate MySQL table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @NonNull
    private String title;

    private String notes;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NonNull
    public User createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_project",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    @NonNull
    public List<User> users;

    private Date projectDate;
}

