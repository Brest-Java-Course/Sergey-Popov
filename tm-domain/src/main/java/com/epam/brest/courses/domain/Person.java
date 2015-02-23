package com.epam.brest.courses.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by beast on 17.11.14. At 15.42
 */
@Entity
@Table(name = "person")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
})
public class Person {

    /**
     *
     */
    public static final int MAX_NAME_SIZE = 45;

    /**
     *
     */
    public static final int HNUMBER = 31;

    /**
     *
     */
    @Id
    @NotNull
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    /**
     *
     */
    @NotNull
    @Size(min = 1, max = MAX_NAME_SIZE)
    @Column(name = "person_fname")
    private String personFirstName;

    /**
     *
     */
    @NotNull
    @Size(min = 1, max = MAX_NAME_SIZE)
    @Column(name = "person_lname")
    private String personLastName;

    /**
     *
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "personId")
    private Set<Task> taskSet;

    /**
     *
     */
    public Person() {

    }

    /**
     *
     * @param personFName personFirstName
     * @param personLName personLastName
     */
    public Person(final String personFName, final String personLName) {

        this.personFirstName = personFName;
        this.personLastName = personLName;

    }

    /**
     *
     * @param persId personId
     * @param personFName personFirstName
     * @param personLName personLastName
     */
    public Person(final Long persId,
                  final String personFName,
                  final String personLName) {

        this.personId = persId;
        this.personFirstName = personFName;
        this.personLastName = personLName;

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
     * @return taskSet
     */
    public final Set<Task> getTaskSet() {
        return taskSet;
    }

    /**
     *
     * @param tSet taskSet
     */
    public final void setTaskSet(final Set<Task> tSet) {
        this.taskSet = tSet;
    }

    /**
     *
     * @param object object
     * @return equals
     */
    @Override
    public final boolean equals(final Object object) {

        if (this == object) {

            return true;

        }

        if (!(object instanceof Person)) {

            return false;

        }

        Person person = (Person) object;

        return personFirstName.equals(person.personFirstName)
                && personId.equals(person.personId)
                && personLastName.equals(person.personLastName);

    }

    /**
     *
     * @return hashCode
     */
    @Override
    public final int hashCode() {

        int result = personId.hashCode();
        int taskSetHashCode;
        result = HNUMBER * result + personFirstName.hashCode();
        result = HNUMBER * result + personLastName.hashCode();

        if (taskSet == null) {
            taskSetHashCode = 0;
        } else {
            taskSetHashCode = taskSet.hashCode();
        }

        result = HNUMBER * result + taskSetHashCode;
        return result;

    }

    /**
     *
     * @return toString
     */
    @Override
    public final String toString() {

        final StringBuffer stringBuffer = new StringBuffer("Person{");
        stringBuffer.append("personId=").append(personId);
        stringBuffer.append(", personFirstName='").append(personFirstName).append('\'');
        stringBuffer.append(", personLastName='").append(personLastName).append('\'');
        stringBuffer.append('}');
        return stringBuffer.toString();

    }

}
