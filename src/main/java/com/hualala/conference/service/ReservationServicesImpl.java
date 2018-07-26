package com.hualala.conference.service;

import com.hualala.conference.bean.ConferenceRoom;
import com.hualala.conference.bean.Reservation;
import com.hualala.conference.dao.Daos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ReservationServicesImpl implements ReservationServices {

    private final Daos dao;

    @Autowired
    public ReservationServicesImpl(Daos dao) {
        this.dao = dao;
    }

    @Override
    public String insertReservation(Reservation reservation) {

        if(reservation.getStartTime()%900>60){
            throw new RuntimeException("对不起，开始预约时间必须为整点，15分，30分，45分");
        }

        if (reservation.getStopTime() - reservation.getStartTime() > 14400) {
            throw new RuntimeException("对不起，预约时间不能超过4小时");
        }
        List list = dao.selectCanUseRoom(reservation.getStartTime(), reservation.getStopTime());
        if (!list.contains(reservation.getConferenceRoomId())) {
            throw new RuntimeException("对不起，该会议室已经被预约,请预约有空余时间的会议室");
        }
        if(reservation.getStartTime()>reservation.getStopTime()){
            throw new RuntimeException("预约结束时间不能早于预约开始时间，不符合预约条件");
        }
        if(dao.selectStartTimeInReservation(reservation.getEmployeeName()).contains(reservation.getStartTime())){
            throw new RuntimeException("一个人不能在同一时间预约两个会议室");
        }
        else {
            return dao.insertReservation(reservation) == 1 ? "预约成功" : "预约失败";
        }

    }

    @Override
    public String deleteReservation(String name) {

        if (!dao.selectEmployeeNameInReservation().contains(name)){
            throw new RuntimeException("对不起，没有该员工的预约信息");
        }

        return dao.deleteReservation(name) > 1 ? "取消预约成功" : "取消预约失败";

    }

    @Override
    public String deleteReservationById(Long id) {

        if (!dao.selectReservationIdInReservation().contains(id)){
            throw new RuntimeException("预约表中没有与该id对应的预约信息，无法取消预约");
        }

        return dao.deleteReservationById(id) == 1 ? "取消预约成功":"取消预约失败";

    }

    @Override
    public String updateReservation(Reservation reservation) {

        List list = dao.selectCanUseRoom(reservation.getStartTime(), reservation.getStopTime());
        if (!list.contains(reservation.getConferenceRoomId())) {
            throw new RuntimeException("对不起，该会议室已经被预约,请预约有空余时间的会议室");
        }

       return dao.updateReservation (reservation) == 1 ? "更新预约成功" : "更新预约失败";

    }

    @Override
    public Reservation selectReservationById(Long id) {

        System.out.println();
        if (!dao.selectReservationIdInReservation().contains(id)){
            throw new RuntimeException("预约表中没有与该id对应的预约信息，无法查询预约");
        }

        return dao.selectReservationById(id);

    }

    @Override
    public List<Reservation> selectReservationByRoom(Integer roomId) {

        if (!dao.selectConferenceIdInConferenceRoom().contains(roomId)){
            throw new RuntimeException("对不起，会议室表中没有对应的id");
        }

        Long  startTime = System.currentTimeMillis();
        dao.selectReservationByRoom(roomId);
        Long stopTime = System.currentTimeMillis();
        System.out.println((stopTime-startTime)+"ms");
        return dao.selectReservationByRoom(roomId);

    }

    @Override
    public List<Reservation> getRoom(Integer id) {

        if(!dao.selectConferenceRoom().contains(id)){
            throw new RuntimeException("对不起，预约表中没有对应的Id");
        }

        return dao.getRoom(id);

    }

    @Override
    public List selectCanUseRoom(Long startTime, Long stopTime) {



        if (startTime>stopTime){
            throw new RuntimeException("预约结束时间不能早于预约开始时间");
        }

        if(dao.selectCanUseRoom(startTime,stopTime).isEmpty()){
            throw new RuntimeException("对不起，没有可以预约的会议室。");
        }

        return dao.selectCanUseRoom(startTime, stopTime);

    }

    @Override
    public List<Reservation> selectConferenceRoom() {
        return dao.selectConferenceRoom();
    }


    @Override
    public List<Reservation> selectReservationIdInReservation() {
        return dao.selectReservationIdInReservation();
    }

    @Override
    public List<Reservation> selectEmployeeNameInReservation() {
        return dao.selectEmployeeNameInReservation();
    }

    @Override
    public List<ConferenceRoom> selectConferenceIdInConferenceRoom() {
        return dao.selectConferenceIdInConferenceRoom();
    }

    @Override
    public List<Reservation> selectStartTimeInReservation(String name) {
        return dao.selectStartTimeInReservation(name);
    }

    @Override
    public String save100w() {
        List<Reservation> list = new ArrayList();
        Reservation reservation;
        Random random = new Random();
        //List<ConferenceRoom> list1 = dao.selectConferenceIdInConferenceRoom();
        Long startTime = System.currentTimeMillis();
        System.out.println();

        for (int i = 0;i<100000;i++){
            reservation = new Reservation();
            reservation.setStartTime(random.nextLong());
            reservation.setStopTime(random.nextLong());
            reservation.setEmployeeName(UUID.randomUUID().toString());
            reservation.setConferenceRoomId(random.nextInt(170)+30);
            list.add(reservation);
        }
        long insertStartTime = System.currentTimeMillis();
        System.out.println("准备数据花费时间"+(insertStartTime-startTime)+"ms");
        Integer rows=dao.insert100w(list);
        long insertEndTime = System.currentTimeMillis();
        System.out.println("插入数据时间"+(insertEndTime-insertStartTime)+"ms");

        return "插入"+rows+"条数据";
    }

    @Override
    public List<Reservation> selectReservationByTime(Long startTime, Long stopTime) {
        return dao.selectReservationByTime(startTime,stopTime);
    }
}
