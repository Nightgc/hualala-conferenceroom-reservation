package com.hualala.conference.service;

import com.hualala.conference.bean.Reservation;
import com.hualala.conference.dao.Daos;
import com.hualala.conference.bean.ConferenceRoom;
import com.hualala.conference.global.GreatThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

@Service
public class ConferenceRoomServicesImpl implements ConferenceRoomServices {

    private final Daos daos;

    @Autowired
    public ConferenceRoomServicesImpl(Daos daos) {
        this.daos = daos;
    }

    @Override
    public List<ConferenceRoom> list(int currPage, int pageSize) {


        int offset = (currPage - 1) * pageSize;
        if (offset < 0) {
            throw new RuntimeException("对不起，您的起始页码必须大于0");
        }
        if (pageSize < 0) {
            pageSize = 10;
        }
        return daos.list(offset, pageSize);
    }

    @Override
    public ConferenceRoom getConferenceRoomById(Integer id) {

        if(id < 0){
            throw new RuntimeException("对不起，您删除的会议室id必须大于0");
        }

        if(!daos.selectConferenceIdInConferenceRoom().contains(id)){
            throw new RuntimeException("对不起，没有对应的会议室id ");
        }

        return daos.getConferenceRoomById(id);

    }

    @Override
    public String deleteConferenceRoomById(Integer id) {

        if(id < 0){
            throw new RuntimeException("对不起，您删除的会议室id必须大于0");
        }
        if(!daos.selectConferenceIdInConferenceRoom().contains(id)){
            throw new RuntimeException("对不起，没有对应的会议室id ");
        }

        return daos.deleteConferenceRoomById(id) == 1 ? "删除会议室成功" : "删除会议室失败";

    }

    @Override
    public String insertConferenceRoom(ConferenceRoom conferenceRoom) {

        if(conferenceRoom.getSeatNumber()<1){
            throw new RuntimeException("对不起，添加的会议室座位数必须大于０");
        }

        if(daos.selectConferenceRoomNameInConferenceRoom().contains(conferenceRoom.getConferenceRoomName())){
            throw new RuntimeException("会议室名字不能重复，表中已有该会议室名字");
        }

        return daos.insertConferenceRoom(conferenceRoom) == 1 ? "添加会议室成功" : "添加会议室失败";

    }

    @Override
    public String insertRoom200() {
        ConferenceRoom conferenceRoom;
        Random random = new Random();

        for (int i = 0; i < 201; i++) {
            conferenceRoom = new ConferenceRoom();
            conferenceRoom.setConferenceRoomName(UUID.randomUUID().toString());
            conferenceRoom.setConferenceRoomPosition(random.toString());
            conferenceRoom.setSeatNumber(random.nextInt(20) + 1);
            conferenceRoom.setHaveProjection(random.nextBoolean());
            conferenceRoom.setHaveWhiteBoard(random.nextBoolean());
            daos.insertConferenceRoom(conferenceRoom);
        }
        return "插入成功";
    }

    @Override
    public String updateConferenceRoom(ConferenceRoom conferenceRoom) {

        if(conferenceRoom.getConferenceRoomName().length()==0){
            throw new RuntimeException("会议室名字不能为空");
        }
        if(conferenceRoom.getSeatNumber()<1){
            throw new  RuntimeException("更新的会议室座位数必须大于0");
        }

        return daos.updateConferenceRoom(conferenceRoom) == 1 ? "更新会议室成功" : "更新会议室失败";

    }

    @Override
    public List<ConferenceRoom> selectRoomByCondition(Map<String, Object> map) {

        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(0);
        list1.add(2);

        if (!list1.contains  ((Integer)(map.get(String.valueOf("option"))))){

                throw new GreatThree("456","判断条件没有");
        }

        return daos.selectRoomByCondition(map);

    }

    @Override
    public List<ConferenceRoom> selectConferenceIdInConferenceRoom() {
        return daos.selectConferenceIdInConferenceRoom();
    }

    @Override
    public List<ConferenceRoom> selectConferenceRoomNameInConferenceRoom() {
        return daos.selectConferenceRoomNameInConferenceRoom();
    }


}
