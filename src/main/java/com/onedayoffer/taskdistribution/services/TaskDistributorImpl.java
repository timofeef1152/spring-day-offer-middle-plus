package com.onedayoffer.taskdistribution.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.DTO.TaskDTO;

@Component
public class TaskDistributorImpl implements TaskDistributor {
    @Override
    public void distribute(List<EmployeeDTO> employees, List<TaskDTO> tasks) {
        Queue<TaskDTO> tasksQueue = new PriorityQueue<>(tasks);
        Collection<EmployeeDTO> employeeQueue = new PriorityQueue<>(employees);

        while (!tasksQueue.isEmpty() && employeeQueue.stream().anyMatch(e -> e.hasFreeLeadTime())) {
            TaskDTO task = tasksQueue.poll();
            for (EmployeeDTO employee : employeeQueue) {
                if (employee.addTask(task)) {
                    break;
                }
            }
            employeeQueue = new PriorityQueue<>(employees);
        }
    }
}
