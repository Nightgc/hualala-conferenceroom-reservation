package com.hualala.conference.service;

import com.hualala.conference.bean.ConferenceRoom;
import com.hualala.conference.bean.Reservation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReservationServices {
    /**
     * 预约会议室
     * @param reservation
     * @return
     */
    String insertReservation(Reservation reservation);

    /**
     * 通过名字删除预约 将输入的name格式转换成URL编码encodeURI
     * @param name
     * @return
     */
    String deleteReservation(String name);

    /**
     * 通过预约表Id删除预约
     * @param id
     * @return
     */
    String deleteReservationById(Long id);

    /**
     * 更改预约
     * @param reservation
     * @return
     */
    String updateReservation(Reservation reservation);

    /**
     * 通过预约id查找预约情况（1）
     * @param id
     * @return
     */
    Reservation selectReservationById(Long id);

    /**
     * 通过会议室id查找预约情况（n）
     * 看不到会议室的属性
     * @param roomId
     * @return
     */
    List<Reservation> selectReservationByRoom(Integer roomId);

    /**
     * 根据会议室id获取会议室信息，以及预约情况
     * 可以看到会议室的属性
     * @param id
     * @return
     */
    List<Reservation> getRoom( Integer id);

    /**
     * 给定时间段查找可用的会议室id
     * @param startTime
     * @param stopTime
     * @return
     */

    List selectCanUseRoom(Long startTime, Long stopTime);

    /**
     * 查找预约表下的所有会议室id
     * @return
     */
    List <Reservation> selectConferenceRoom();

    /**
     * 查找预约表下的所有预约id
     * @return
     */
    List <Reservation> selectReservationIdInReservation();

    /**
     * 查询预约表的所有员工姓名
     * @return
     */
    List <Reservation>selectEmployeeNameInReservation();

    /**
     * 获取会议室表下的所有会议室id
     * @return List
     */
    List<ConferenceRoom> selectConferenceIdInConferenceRoom();

    /**
     * 根据员工姓名查找该员工预约所有会议室的开始时间
     * @param name
     * @return
     */
    List <Reservation>selectStartTimeInReservation(String name);

    /**
     * 随机插入10万条数据需要的时间
     * @return
     */


    String save100w();

    /**
     * 根据时间段查询预约
     * @param startTime
     * @param stopTime
     * @return
     */
    List<Reservation> selectReservationByTime(Long startTime,Long stopTime);
}
