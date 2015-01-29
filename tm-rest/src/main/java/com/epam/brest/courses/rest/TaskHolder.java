package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Task;

/**
 * Created by beast on 1.12.14. At 13.43
 */
public class TaskHolder {

    private Task task;

    private Long personId;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TaskHolder{");
        sb.append("task=").append(task);
        sb.append(", personId=").append(personId);
        sb.append('}');
        return sb.toString();
    }
}