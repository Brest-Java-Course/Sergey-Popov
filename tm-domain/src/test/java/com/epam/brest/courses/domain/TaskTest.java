package com.epam.brest.courses.domain;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    private static final Long TASK_ID = 1l;
    private static final String TASK_NAME = "doTest";
    private static final Boolean TASK_STATE = true;
    private static final DateTime TASK_START_DATE = new DateTime(2014, 11, 15, 0, 0, 0);
    private static final DateTime TASK_END_DATE = new DateTime(2014, 11, 18, 0, 0, 0);
    private static final Integer TASK_ELAPSED_TIME = new Period(TASK_START_DATE, TASK_END_DATE, PeriodType.minutes()).getMinutes();

    private static final Long TASK_ID2 = 2l;
    private static final String TASK_NAME2 = "doTest2";
    private static final Boolean TASK_STATE2 = false;
    private static final DateTime TASK_START_DATE2 = new DateTime(2014, 11, 10, 0, 0, 0);
    private static final DateTime TASK_END_DATE2 = new DateTime(2014, 11, 18, 0, 0, 0);
    private static final Integer TASK_ELAPSED_TIME2 = new Period(TASK_START_DATE, TASK_END_DATE, PeriodType.minutes()).getMinutes();

    private Task task;
    private Task task2;

    @Test
    public void testConstructors() throws Exception {

        task = new Task();
        assertNull(task.getTaskId());
        assertNull(task.getTaskName());
        assertNull(task.isTaskState());
        assertNull(task.getStartDate());
        assertNull(task.getEndDate());
        assertNull(task.getElapsedTime());

        task = new Task(TASK_NAME, TASK_STATE, TASK_START_DATE, TASK_END_DATE, TASK_ELAPSED_TIME);
        assertNull(task.getTaskId());
        assertNotNull(task.getTaskName());
        assertTrue(task.isTaskState());
        assertNotNull(task.getStartDate());
        assertNotNull(task.getEndDate());
        assertNotNull(task.getElapsedTime());
        assertEquals(null, task.getTaskId());
        assertEquals(TASK_NAME, task.getTaskName());
        assertEquals(TASK_STATE, task.isTaskState());
        assertEquals(TASK_START_DATE, task.getStartDate());
        assertEquals(TASK_END_DATE, task.getEndDate());
        assertEquals(TASK_ELAPSED_TIME, task.getElapsedTime());

        task = new Task(TASK_ID, TASK_NAME, TASK_STATE, TASK_START_DATE, TASK_END_DATE, TASK_ELAPSED_TIME);
        assertNotNull(task.getTaskId());
        assertNotNull(task.getTaskName());
        assertTrue(task.isTaskState());
        assertNotNull(task.getStartDate());
        assertNotNull(task.getEndDate());
        assertNotNull(task.getElapsedTime());
        assertEquals(TASK_ID, task.getTaskId());
        assertEquals(TASK_NAME, task.getTaskName());
        assertEquals(TASK_STATE, task.isTaskState());
        assertEquals(TASK_START_DATE, task.getStartDate());
        assertEquals(TASK_END_DATE, task.getEndDate());
        assertEquals(TASK_ELAPSED_TIME, task.getElapsedTime());

    }

    @Test
    public void testGettersSetters() throws Exception {

        task = new Task();
        task.setTaskId(TASK_ID);
        task.setTaskName(TASK_NAME);
        task.setTaskState(TASK_STATE);
        task.setStartDate(TASK_START_DATE);
        task.setEndDate(TASK_END_DATE);
        task.setElapsedTime(TASK_ELAPSED_TIME);

        assertNotNull(task.getTaskId());
        assertNotNull(task.getTaskName());
        assertTrue(task.isTaskState());
        assertNotNull(task.getStartDate());
        assertNotNull(task.getEndDate());
        assertNotNull(task.getElapsedTime());
        assertEquals(TASK_ID, task.getTaskId());
        assertEquals(TASK_NAME, task.getTaskName());
        assertEquals(TASK_STATE, task.isTaskState());
        assertEquals(TASK_START_DATE, task.getStartDate());
        assertEquals(TASK_END_DATE, task.getEndDate());
        assertEquals(TASK_ELAPSED_TIME, task.getElapsedTime());

    }

    @Test
    public void testEquals() throws Exception {

        task = new Task(TASK_ID, TASK_NAME, TASK_STATE, TASK_START_DATE, TASK_END_DATE, TASK_ELAPSED_TIME);
        task2 = new Task(TASK_ID2, TASK_NAME2, TASK_STATE2, TASK_START_DATE2, TASK_END_DATE2, TASK_ELAPSED_TIME2);

        assertNotNull(task);
        assertNotNull(task2);
        assertFalse(task.equals(task2));

    }

    @Test
    public void testEqualsSame() throws Exception {

        task = new Task(TASK_ID, TASK_NAME, TASK_STATE, TASK_START_DATE, TASK_END_DATE, TASK_ELAPSED_TIME);

        assertNotNull(task);
        assertTrue(task.equals(task));

    }

    @Test
    public void testHashCode() throws Exception {

        task = new Task(TASK_ID, TASK_NAME, TASK_STATE, TASK_START_DATE, TASK_END_DATE, TASK_ELAPSED_TIME);
        task2 = new Task(TASK_ID2, TASK_NAME2, TASK_STATE2, TASK_START_DATE2, TASK_END_DATE2, TASK_ELAPSED_TIME2);

        assertNotNull(task);
        assertNotNull(task2);
        assertNotEquals(task.hashCode(), task2.hashCode());

    }

    @Test
    public void testHashCodeSame() throws Exception {

        task = new Task(TASK_ID, TASK_NAME, TASK_STATE, TASK_START_DATE, TASK_END_DATE, TASK_ELAPSED_TIME);

        assertNotNull(task);
        assertEquals(task.hashCode(), task.hashCode());

    }

    @Test
    public void testToString() throws Exception {

        task = new Task(TASK_ID, TASK_NAME, TASK_STATE, TASK_START_DATE, TASK_END_DATE, TASK_ELAPSED_TIME);

        assertNotNull(task);
        assertEquals("Task{taskId=1, taskName='doTest', taskState=true, startDate=2014-11-15T00:00:00.000+03:00, endDate=2014-11-18T00:00:00.000+03:00, elapsedTime=4320, personId=null}", task.toString());

    }

}