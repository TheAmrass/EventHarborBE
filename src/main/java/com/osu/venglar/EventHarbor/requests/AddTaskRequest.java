package com.osu.venglar.EventHarbor.requests;

import com.osu.venglar.EventHarbor.model.User;
import com.osu.venglar.EventHarbor.model.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {

    private String title;
    private String description;
    private Long userId;
    private Priority priority;
    private List<Long> userIds;
    private Long projectId;
    private Date dueDate;
}
