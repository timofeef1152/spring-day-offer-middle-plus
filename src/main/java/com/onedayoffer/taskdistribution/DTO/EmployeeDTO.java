package com.onedayoffer.taskdistribution.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO implements Comparable<EmployeeDTO> {
    private static final int MAX_LEAD_TIME_MINUTES = 7 * 60;

    private String fio;
    private String jobTitle;
    private List<TaskDTO> tasks = new ArrayList<>();

    public EmployeeDTO(String fio, String jobTitle) {
        this.fio = fio;
        this.jobTitle = jobTitle;
        this.tasks = new ArrayList<>();
    }

    public Integer getTotalLeadTime() {
        if (tasks.size() == 0) {
            return 0;
        } else {
            return tasks.stream().mapToInt(TaskDTO::getLeadTime).sum();
        }
    }

    @Override
    public int compareTo(EmployeeDTO o) {
        return getTotalLeadTime().compareTo(o.getTotalLeadTime());
    }

    public boolean addTask(TaskDTO task) {
        final int newLeadTime = getTotalLeadTime() + task.getLeadTime();
        if (MAX_LEAD_TIME_MINUTES > newLeadTime) {
            return tasks.add(task);
        }
        return false;
    }

    public boolean hasFreeLeadTime() {
        return MAX_LEAD_TIME_MINUTES > getTotalLeadTime();
    }

}
