package ru.bellintegrator.app.model;

import ru.bellintegrator.app.validation.ContactEditorValidator;

import java.io.Serializable;
import java.util.Map;

public class AnalyticalInfo implements Serializable{

    private static final long serialVersionUID = -9048196856895867642L;
    private int userCount;
    private Map<Integer, Long> eachUserContactCount;
    private Map<Integer, Long> eachUserGroupCount;
    private double avgUserCountInGroups;
    private int inactiveUserCount;
    private double avgUsersContactCount;
    private static AnalyticalInfo instance;

    private AnalyticalInfo() {
    }

    public static AnalyticalInfo getInstance() {

        if (instance == null) {
            instance = new AnalyticalInfo();
        }

        return instance;

    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public Map<Integer, Long> getEachUserContactCount() {
        return eachUserContactCount;
    }

    public void setEachUserContactCount(Map<Integer, Long> eachUserContactCount) {
        this.eachUserContactCount = eachUserContactCount;
    }

    public Map<Integer, Long> getEachUserGroupCount() {
        return eachUserGroupCount;
    }

    public void setEachUserGroupCount(Map<Integer, Long> eachUserGroupCount) {
        this.eachUserGroupCount = eachUserGroupCount;
    }

    public double getAvgUserCountInGroups() {
        return avgUserCountInGroups;
    }

    public void setAvgUserCountInGroups(double avgUserCountInGroups) {
        this.avgUserCountInGroups = avgUserCountInGroups;
    }

    public int getInactiveUserCount() {
        return inactiveUserCount;
    }

    public void setInactiveUserCount(int inactiveUserCount) {
        this.inactiveUserCount = inactiveUserCount;
    }

    public double getAvgUsersContactCount() {
        return avgUsersContactCount;
    }

    public void setAvgUsersContactCount(double avgUsersContactCount) {
        this.avgUsersContactCount = avgUsersContactCount;
    }

    @Override
    public String toString() {
        return "AnalyticalInfo{" +
                "userCount=" + userCount +
                ", eachUserContactCount=" + eachUserContactCount +
                ", eachUserGroupCount=" + eachUserGroupCount +
                ", avgUserCountInGroups=" + avgUserCountInGroups +
                ", inactiveUserCount=" + inactiveUserCount +
                ", avgUsersContactCount=" + avgUsersContactCount +
                '}';
    }

}
