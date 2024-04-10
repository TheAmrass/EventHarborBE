package com.osu.venglar.EventHarbor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osu.venglar.EventHarbor.model.enums.Priority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data //Contains Getters, Setters, toString and more
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity //Automaticaly generate MySQL table
// @Table(name="name_of _the_table") -- custom table name
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    private Date dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User user;

    @Enumerated(EnumType.ORDINAL)
    public Priority priority;

    private int completed = 0;
}

