package com.hualala.conference.service;

import com.hualala.conference.bean.ConferenceRoom;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface ConferenceRoomServices {
/*会议室表*/

    /**
     * 获取所有会议室信息
     * @param currPage 页数
     * @param pageSize 数据条数
     * @return
     */
    List<ConferenceRoom> list(int currPage, int pageSize);

    /**
     * 通过会议室id获取会议室信息
     * @param id
     * @return
     */
    ConferenceRoom getConferenceRoomById(Integer id);

    /**
     * 通过会议室id删除会议室信息
     * @param id
     * @return
     */
    String deleteConferenceRoomById(Integer id);

    /**
     * 新增会议室
     * @param conferenceRoom
     * @return
     */
    String insertConferenceRoom(ConferenceRoom conferenceRoom);

    /**
     * 插入200个会议室
     * @param
     * @return
     */
    String insertRoom200();
    /**
     * 更新会议室
     * @param conferenceRoom
     * @return
     */
    String updateConferenceRoom(ConferenceRoom conferenceRoom);

    /**
     * 根据会议室属性查找会议室信息
     * @param map
     * @return
     */
    List<ConferenceRoom> selectRoomByCondition(Map<String, Object> map);

    /**
     * 获取会议室表下的所有会议室id
     * @return List
     */
    List<ConferenceRoom> selectConferenceIdInConferenceRoom();

    /**
     * 获取所有的会议室姓名
     * @return
     */
    List <ConferenceRoom>selectConferenceRoomNameInConferenceRoom();


}
