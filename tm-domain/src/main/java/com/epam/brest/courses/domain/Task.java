package com.epam.brest.courses.domain;

import com.epam.brest.courses.domain.JsonJodaDate.CustomDateDeSerializer;
import com.epam.brest.courses.domain.JsonJodaDate.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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

    /**
     *
     */
    public static final int MAX_NAME_SIZE = 45;

    /**
     *
     */
    public static final int H_NUMBER = 31;

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    /**
     *
     */
    @NotNull
    @Size(min = 1, max = MAX_NAME_SIZE)
    @Column(name = "task_name")
    private String taskName;

    /**
     *
     */
    @NotNull
    @Column(name = "task_state")
    private Boolean taskState;

    /**
     *
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @Column(name = "task_startdate", columnDefinition = "datetime NULL")
    private DateTime startDate;

    /**
     *
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @Column(name = "task_enddate", columnDefinition = "datetime NULL")
    private DateTime endDate;

    /**
     *
     */
    @Column(name = "task_elapsedtime")
    private Integer elapsedTime;

    /**
     *
     */
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false)
    private Person personId;

    /**
     *
     */
    public Task() {

    }

    /**
     *
     * @param tName taskName
     * @param tState taskState
     * @param sDate startDate
     * @param eDate endDate
     * @param elapsTime elapsedTime
     */
    public Task(final String tName,
                final Boolean tState,
                final DateTime sDate,
                final DateTime eDate,
                final Integer elapsTime) {

        this.taskName = tName;
        this.taskState = tState;
        this.startDate = sDate;
        this.endDate = eDate;
        this.elapsedTime = elapsTime;

    }

    /**
     *
     * @param tId taskId
     * @param tName taskName
     * @param tState taskState
     * @param sDate startDate
     * @param eDate endDate
     * @param elapsTime elapsedTime
     */
    public Task(final Long tId,
                final String tName,
                final Boolean tState,
                final DateTime sDate,
                final DateTime eDate,
                final Integer elapsTime) {

        this.taskId = tId;
        this.taskName = tName;
        this.taskState = tState;
        this.startDate = sDate;
        this.endDate = eDate;
        this.elapsedTime = elapsTime;

    }

    /**
     *
     * @return taskId
     */
    public final Long getTaskId() {
        return taskId;
    }

    /**
     *
     * @param tId taskId
     */
    public final void setTaskId(final Long tId) {
        this.taskId = tId;
    }

    /**
     *
     * @return taskName
     */
    public final String getTaskName() {
        return taskName;
    }

    /**
     *
     * @param tName taskName
     */
    public final void setTaskName(final String tName) {
        this.taskName = tName;
    }

    /**
     *
     * @return taskState
     */
    public final Boolean getTaskState() {
        return taskState;
    }

    /**
     *
     * @param tState taskState
     */
    public final void setTaskState(final Boolean tState) {
        this.taskState = tState;
    }

    /**
     *
     * @return startDate
     */
    public final DateTime getStartDate() {
        return startDate;
    }

    /**
     *
     * @param sDate startDate
     */
    public final void setStartDate(final DateTime sDate) {
        this.startDate = sDate;
    }

    /**
     *
     * @return endDate
     */
    public final DateTime getEndDate() {
        return endDate;
    }

    /**
     *
     * @param eDate endDate
     */
    public final void setEndDate(final DateTime eDate) {
        this.endDate = eDate;
    }

    /**
     *
     * @return elapsedTime
     */
    public final Integer getElapsedTime() {
        return elapsedTime;
    }

    /**
     *
     * @param elapsTime elapsedTime
     */
    public final void setElapsedTime(final Integer elapsTime) {
        this.elapsedTime = elapsTime;
    }

    /**
     *
     * @return personId
     */
    public final Person getPerson() {
        return personId;
    }

    /**
     *
     * @param persId personId
     */
    public final void setPerson(final Person persId) {
        this.personId = persId;
    }

    @Override
    public final boolean equals(final Object o) {

        if (this == o) {

            return true;

        }

        if (!(o instanceof Task)) {

            return false;

        }

        Task task = (Task) o;

        return elapsedTime.equals(task.elapsedTime)
                && endDate.equals(task.endDate)
                && startDate.equals(task.startDate)
                && taskId.equals(task.taskId)
                && taskName.equals(task.taskName)
                && taskState.equals(task.taskState);

    }

    @Override
    public final int hashCode() {

        int result = taskId.hashCode();
        result = H_NUMBER * result + taskName.hashCode();
        result = H_NUMBER * result + taskState.hashCode();
        result = H_NUMBER * result + startDate.hashCode();

        return result;

    }

    @Override
    public final String toString() {

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
