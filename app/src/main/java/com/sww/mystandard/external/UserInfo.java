package com.sww.mystandard.external;

public class UserInfo {

    private Long userId;
    private String screenName;
    private Integer level;
    private String avatar;

    public UserInfo(Long userId, String screenName, Integer level, String avatar) {
        this.userId = userId;
        this.screenName = screenName;
        this.level = level;
        this.avatar = avatar;
    }

    public Long getUserId() {
        return userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public Integer getLevel() {
        return level;
    }

    public String getAvatar() {
        return avatar;
    }

}
