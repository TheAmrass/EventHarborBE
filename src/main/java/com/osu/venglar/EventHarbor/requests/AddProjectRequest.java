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
public class AddProjectRequest {

    private String title;
    private String notes;
    private List<Long> userIds;
    private Long createdBy;
    private Date projectDate;
}
