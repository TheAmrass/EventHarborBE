package com.osu.venglar.EventHarbor.requests;

import com.osu.venglar.EventHarbor.model.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectRequest {

    private String title;
    private String description;
    private Priority priority;
    private Date dueDate;
    private int completed;
}