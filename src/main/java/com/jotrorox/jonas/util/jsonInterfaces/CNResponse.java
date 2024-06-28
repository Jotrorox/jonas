package com.jotrorox.jonas.util.jsonInterfaces;

/**
 * This class is used to map the JSON response from the API to a Java object.
 * @@see <a href="https://api.chucknorris.io">API</a>
 */
public class CNResponse {

    /**
     * The icon URL.
     */
    private String icon_url;

    /**
     * The ID.
     */
    private String id;

    /**
     * The URL.
     */
    private String url;

    /**
     * The value.
     */
    private String value;

    /**
     * Getter for the icon URL.
     *
     * @return The icon URL.
     */
    public String getIcon_url() {
        return icon_url;
    }

    /**
     * Setter for the icon URL.
     *
     * @param icon_url The icon URL.
     */
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    /**
     * Getter for the ID.
     *
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the ID.
     *
     * @param id The ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the URL.
     *
     * @return The URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for the URL.
     *
     * @param url The URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter for the value.
     *
     * @return The value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter for the value.
     *
     * @param value The value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return (
            "CNResponse{" +
            "icon_url=" +
            icon_url +
            ", id=" +
            id +
            ", url=" +
            url +
            ", value=" +
            value +
            '}'
        );
    }
}
