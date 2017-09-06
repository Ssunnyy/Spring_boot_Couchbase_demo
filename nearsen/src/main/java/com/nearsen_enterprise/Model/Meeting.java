package com.nearsen_enterprise.Model;

public class Meeting {

    private final long meetingId;
    private final String meetingName;

    public Meeting(long meetingId, String meetingName) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
    }

    public long getMeetingId() {
        return meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }
}
