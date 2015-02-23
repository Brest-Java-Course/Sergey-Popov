package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Task;

/**
 * Created by beast on 1.12.14. At 13.43
 */
public class TaskHolder {

    /**
     *
     */
    private Task task;

    /**
     *
     */
    private Long personId;

    /**
     *
     * @return task
     */
    public final Task getTask() {
        return task;
    }

    /**
     *
     * @param tsk task
     */
    public final void setTask(final Task tsk) {
        this.task = tsk;
    }

    /**
     *
     * @return personId
     */
    public final Long getPersonId() {
        return personId;
    }

    /**
     *
     * @param persId personId
     */
    public final void setPersonId(final Long persId) {
        this.personId = persId;
    }

    @Override
    public final String toString() {
        final StringBuffer sb = new StringBuffer("TaskHolder{");
        sb.append("task=").append(task);
        sb.append(", personId=").append(personId);
        sb.append('}');
        return sb.toString();
    }
}
