package com.hualala.conference.bean;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
public class Reservation implements Serializable {
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Long startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Long stopTime;
    private Integer conferenceRoomId;
    private String employeeName;
    private Long reservationId;

    private ConferenceRoom conferenceRoom;

}
