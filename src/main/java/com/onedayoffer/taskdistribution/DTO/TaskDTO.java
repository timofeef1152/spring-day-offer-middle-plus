package com.onedayoffer.taskdistribution.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO implements Comparable<TaskDTO> {
    private Integer id;
    private String name;
    private TaskType taskType;
    private TaskStatus status;
    private Integer priority;
    private Integer leadTime;

    @Override
    public int compareTo(TaskDTO o) {
        int k = o.priority % priority;
        return Integer.valueOf(leadTime * k).compareTo(leadTime);
    }
}
