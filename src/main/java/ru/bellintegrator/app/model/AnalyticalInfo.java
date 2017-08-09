package ru.bellintegrator.app.model;

import java.io.Serializable;
import java.util.Map;

public class AnalyticalInfo implements Serializable{

    private static final long serialVersionUID = -9048196856895867642L;
    private int userCount;
    private Map<Integer, Integer> eachUserContactCount;
    private Map<Integer, Integer> eachUserGroupCount;
    private double avgUserCountInGroups;
    private int inactiveUserCount;
    private double avgUsersContactCount;

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public Map<Integer, Integer> getEachUserContactCount() {
        return eachUserContactCount;
    }

    public void setEachUserContactCount(Map<Integer, Integer> eachUserContactCount) {
        this.eachUserContactCount = eachUserContactCount;
    }

    public Map<Integer, Integer> getEachUserGroupCount() {
        return eachUserGroupCount;
    }

    public void setEachUserGroupCount(Map<Integer, Integer> eachUserGroupCount) {
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
