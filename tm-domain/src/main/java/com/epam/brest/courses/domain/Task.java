package com.epam.brest.courses.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by beast on 17.11.14. At 15.51
 */
@Entity
@Table(name = "task")
@NamedQueries({
        @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "task_name")
    private String taskName;

    @NotNull
    @Column(name = "task_state")
    private Boolean taskState;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "task_startdate", columnDefinition = "datetime NULL")
    private DateTime startDate;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "task_enddate", columnDefinition = "datetime NULL")
    private DateTime endDate;

    @Column(name = "task_elapsedtime")
    private Integer elapsedTime;

    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false)
    private Person person;

    public Task() {

    }

    public Task(String taskName, Boolean taskState, DateTime startDate, DateTime endDate, Integer elapsedTime) {

        this.taskName = taskName;
        this.taskState = taskState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.elapsedTime = elapsedTime;

    }

    public Task(Long taskId, String taskName, Boolean taskState, DateTime startDate, DateTime endDate, Integer elapsedTime) {

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

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        sb.append(", personId=").append(person);
        sb.append('}');
        return sb.toString();

    }
}
