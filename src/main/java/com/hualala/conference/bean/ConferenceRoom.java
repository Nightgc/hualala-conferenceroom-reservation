package com.hualala.conference.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConferenceRoom implements Serializable {
    private Integer conferenceRoomId;
    private String conferenceRoomName;
    private String conferenceRoomPosition;
    private Integer seatNumber;
    private Boolean haveProjection;
    private Boolean haveWhiteBoard;

}
