package com.epam.brest.courses.domain;

/**
 * Created by beast on 4.12.14. At 13.06
 */
public class Report {

    private Long personId;

    private String personFirstName;

    private String personLastName;

    private Long timeTotal;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public Long getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(Long timeTotal) {
        this.timeTotal = timeTotal;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Report{");
        sb.append("personId=").append(personId);
        sb.append(", personFirstName='").append(personFirstName).append('\'');
        sb.append(", personLastName='").append(personLastName).append('\'');
        sb.append(", timeTotal=").append(timeTotal);
        sb.append('}');
        return sb.toString();
    }
}