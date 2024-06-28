package com.jotrorox.jonas.util.jsonInterfaces;

/**
 * This class is a POJO for the JSON response from the Dog API.
 * @see <a href="https://dog.ceo/dog-api/">Dog API</a>
 */
public class DogResponse {

    /**
     * The message from the API.
     * It will contain the link to the image.
     */
    private String message;

    /**
     * The status from the API.
     */
    private String status;

    /**
     * Gets the message.
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     * @param message The message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the status.
     * @return The status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * @param status The status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
