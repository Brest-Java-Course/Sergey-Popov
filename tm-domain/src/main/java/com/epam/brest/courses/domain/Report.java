package com.epam.brest.courses.domain;

/**
 * Created by beast on 4.12.14. At 13.06
 */
public class Report {

    /**
     *
     */
    private Long personId;

    /**
     *
     */
    private String personFirstName;

    /**
     *
     */
    private String personLastName;

    /**
     *
     */
    private Long timeTotal;

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

    /**
     *
     * @return personFirstName
     */
    public final String getPersonFirstName() {
        return personFirstName;
    }

    /**
     *
     * @param personFName personFirstName
     */
    public final void setPersonFirstName(final String personFName) {
        this.personFirstName = personFName;
    }

    /**
     *
     * @return personLastName
     */
    public final String getPersonLastName() {
        return personLastName;
    }

    /**
     *
     * @param personLName personLastName
     */
    public final void setPersonLastName(final String personLName) {
        this.personLastName = personLName;
    }

    /**
     *
     * @return timeTotal
     */
    public final Long getTimeTotal() {
        return timeTotal;
    }

    /**
     *
     * @param tTotal timeTotal
     */
    public final void setTimeTotal(final Long tTotal) {
        this.timeTotal = tTotal;
    }

    @Override
    public final String toString() {
        final StringBuffer sb = new StringBuffer("Report{");
        sb.append("personId=").append(personId);
        sb.append(", personFirstName='").append(personFirstName).append('\'');
        sb.append(", personLastName='").append(personLastName).append('\'');
        sb.append(", timeTotal=").append(timeTotal);
        sb.append('}');
        return sb.toString();
    }
}
