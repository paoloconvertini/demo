package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class Meeting {
    private String id;
    private String roomId;
    private String branchId;
    private String title;
    private String description;
    private String requestUser;
    private java.sql.Timestamp requestDateTime;
    private java.sql.Timestamp meetingDate;
    private Date meetingStartTime;
    private java.sql.Timestamp meetingEndTime;
    private long membersNumber;
    private String managementYn;
    private String videoYn;
    private String conferenceRoomStaticId;
    private String status;
    private String idMultiMeeting;
    private String deleteUser;
    private java.sql.Timestamp deleteDatetime;
    private String invitationYn;
    private List<Member> memberList;

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }


    public java.sql.Timestamp getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(java.sql.Timestamp requestDateTime) {
        this.requestDateTime = requestDateTime;
    }


    public java.sql.Timestamp getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(java.sql.Timestamp meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Date getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(Date meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public java.sql.Timestamp getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(java.sql.Timestamp meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }


    public long getMembersNumber() {
        return membersNumber;
    }

    public void setMembersNumber(long membersNumber) {
        this.membersNumber = membersNumber;
    }


    public String getManagementYn() {
        return managementYn;
    }

    public void setManagementYn(String managementYn) {
        this.managementYn = managementYn;
    }


    public String getVideoYn() {
        return videoYn;
    }

    public void setVideoYn(String videoYn) {
        this.videoYn = videoYn;
    }


    public String getConferenceRoomStaticId() {
        return conferenceRoomStaticId;
    }

    public void setConferenceRoomStaticId(String conferenceRoomStaticId) {
        this.conferenceRoomStaticId = conferenceRoomStaticId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getIdMultiMeeting() {
        return idMultiMeeting;
    }

    public void setIdMultiMeeting(String idMultiMeeting) {
        this.idMultiMeeting = idMultiMeeting;
    }


    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }


    public java.sql.Timestamp getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(java.sql.Timestamp deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }


    public String getInvitationYn() {
        return invitationYn;
    }

    public void setInvitationYn(String invitationYn) {
        this.invitationYn = invitationYn;
    }

}
