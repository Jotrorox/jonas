package com.jotrorox.jonas.util.jsonInterfaces;

/**
 * Represents the response from the MeowFacts API
 * @see <a href="https://github.com/wh-iterabb-it/meowfacts">Meow Facts API</a>
 */
public class MeowFactsResponse {

    /**
     * The data from the response
     */
    private String[] data;

    /**
     * Gets the data from the response
     * @return The data from the response
     */
    public String[] getData() {
        return data;
    }

    /**
     * Sets the data from the response
     * @param data
     */
    public void setData(String[] data) {
        this.data = data;
    }

    /**
     * Gets the fact from the response
     * @return
     */
    public String getFact() {
        return data[0];
    }
}
