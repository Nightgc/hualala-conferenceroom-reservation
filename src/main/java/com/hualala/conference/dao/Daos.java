package com.hualala.conference.dao;

import com.hualala.conference.bean.ConferenceRoom;
import com.hualala.conference.bean.Reservation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Daos {
    List<ConferenceRoom> list(@Param("offset")int offset,@Param("pageSize") int pageSize);

    ConferenceRoom getConferenceRoomById(@Param("id") Integer id);

    Integer deleteConferenceRoomById(@Param("id") Integer id);

    Integer insertConferenceRoom(@Param("conferenceRoom") ConferenceRoom conferenceRoom);

    Integer updateConferenceRoom(@Param("conferenceRoom") ConferenceRoom conferenceRoom);

    List<ConferenceRoom> selectRoomByCondition( Map<String, Object> map);

    Integer insertReservation(@Param("reservation") Reservation reservation);

    Integer deleteReservation(@Param("name")  String name);

    Integer deleteReservationById(@Param("id") Long id);

    Integer updateReservation(@Param("reservation") Reservation reservation);

    Reservation selectReservationById(@Param("id") Long id);


    List<Reservation> selectReservationByRoom(@Param("roomId") Integer roomId);

    List<Reservation> getRoom(@Param("id") Integer id);

    List selectCanUseRoom(@Param("startTime") Long startTime,@Param("stopTime") Long stopTime);

    List selectConferenceRoom();

    List selectConferenceIdInConferenceRoom();

    List selectReservationIdInReservation();

    List selectEmployeeNameInReservation();

    List selectConferenceRoomNameInConferenceRoom();

    List selectStartTimeInReservation(String name);

    Integer insert100w(@Param("list") List<Reservation> list);

    List<Reservation> selectReservationByTime(@Param("startTime") Long startTime,@Param("stopTime") Long stopTime);



}
