package com.epam.brest.courses.rest.exception;

/**
 * Created by beast on 22.11.14. At 13.04
 */
public class NotFoundException extends RuntimeException {

    /**
     *
     */
    private String objectId;

    /**
     * Constructs a new exception with the specified detail message and
     * objectId value.
     *
     * @param message the detail message
     * @param objId id of the object that cannot be found.
     */
    public NotFoundException(final String message, final String objId) {
        super(message);
        this.objectId = objId;
    }

    /**
     *
     * @return objectId
     */
    public final String getObjectId() {
        return objectId;
    }

}
