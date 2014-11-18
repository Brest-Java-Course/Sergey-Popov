package com.epam.brest.courses.domain;

import java.util.Date;

/**
 * Created by beast on 17.11.14. At 15.51
 */
public class Task {

    private Long taskId;

    private String taskName;

    private Boolean taskState;

    private Date startDate;

    private Date endDate;

    private Long elapsedTime;

    private Person personId;

    public Task() {

    }

    public Task(String taskName, Boolean taskState, Date startDate, Date endDate, Long elapsedTime) {

        this.taskName = taskName;
        this.taskState = taskState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.elapsedTime = elapsedTime;

    }

    public Task(Long taskId, String taskName, Boolean taskState, Date startDate, Date endDate, Long elapsedTime) {

        this.taskId = taskId;
        this.taskName = taskName;
        this.taskState = taskState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.elapsedTime = elapsedTime;

    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean isTaskState() {
        return taskState;
    }

    public void setTaskState(Boolean taskState) {
        this.taskState = taskState;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;

        }

        if (!(o instanceof Task)) {

            return false;

        }

        Task task = (Task) o;

        return elapsedTime.equals(task.elapsedTime) && endDate.equals(task.endDate)
                && startDate.equals(task.startDate) && taskId.equals(task.taskId)
                && taskName.equals(task.taskName) && taskState.equals(task.taskState);

    }

    @Override
    public int hashCode() {

        int result = taskId.hashCode();
        result = 31 * result + taskName.hashCode();
        result = 31 * result + taskState.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + elapsedTime.hashCode();
        return result;

    }

    @Override
    public String toString() {

        final StringBuffer sb = new StringBuffer("Task{");
        sb.append("taskId=").append(taskId);
        sb.append(", taskName='").append(taskName).append('\'');
        sb.append(", taskState=").append(taskState);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", elapsedTime=").append(elapsedTime);
        sb.append(", personId=").append(personId);
        sb.append('}');
        return sb.toString();

    }
}
