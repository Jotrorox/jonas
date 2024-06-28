package com.jotrorox.jonas.util.jsonInterfaces;

/**
 * Represents a response from the Useless Facts API.
 * @see <a href="https://uselessfacts.jsph.pl/">Useless Facts API</a>
 */
public class UFResponse {

    /**
     * The ID of the fact.
     */
    private String id;

    /**
     * The text of the fact.
     */
    private String text;

    /**
     * The source of the fact.
     */
    private String source;

    /**
     * The URL of the source.
     */
    private String source_url;

    /**
     * The language of the fact.
     */
    private String language;

    /**
     * The permalink of the fact.
     */
    private String permalink;

    /**
     * Returns the ID of the fact.
     * @return The ID of the fact.
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the text of the fact.
     * @return The text of the fact.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the source of the fact.
     * @return The source of the fact.
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the URL of the source.
     * @return The URL of the source.
     */
    public String getSourceURL() {
        return source_url;
    }

    /**
     * Returns the language of the fact.
     * @return The language of the fact.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the permalink of the fact.
     * @return The permalink of the fact.
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * Sets the ID of the fact.
     * @param id The ID of the fact.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Sets the text of the fact.
     * @param text The text of the fact.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets the source of the fact.
     * @param source The source of the fact.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Sets the URL of the source.
     * @param source_url The URL of the source.
     */
    public void setSourceURL(String source_url) {
        this.source_url = source_url;
    }

    /**
     * Sets the language of the fact.
     * @param language The language of the fact.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Sets the permalink of the fact.
     * @param permalink The permalink of the fact.
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * Returns a string representation of the fact.
     * @return A string representation of the fact.
     */
    @Override
    public String toString() {
        return (
            "ID: " +
            id +
            "\nText: " +
            text +
            "\nSource: " +
            source +
            "\nSource URL: " +
            source_url +
            "\nLanguage: " +
            language +
            "\nPermalink: " +
            permalink
        );
    }
}
