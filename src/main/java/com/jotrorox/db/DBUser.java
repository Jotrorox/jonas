package com.jotrorox.db;

public class DBUser {
    private final String id;
    private final String name;
    private final String avatarUrl;
    private final String guildId;

    public DBUser(String id, String name, String avatarUrl, String guildId) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.guildId = guildId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGuildId() {
        return guildId;
    }
}
