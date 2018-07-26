package com.hualala.conference.controller;

import com.hualala.conference.bean.ConferenceRoom;
import com.hualala.conference.dto.BaseResponse;
import com.hualala.conference.service.ConferenceRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConferenceRoomController {

    private final ConferenceRoomServices conferenceRoomServices;

    @Autowired
    public ConferenceRoomController(ConferenceRoomServices conferenceRoomServices) {
        this.conferenceRoomServices = conferenceRoomServices;

    }

    /**
     * 分页打印会议室表信息
     * @param currPage 页码
     * @param pageSize 每页的数据个数
     * @return BaseResponse
     */
    //接收currPage参数表示显示第几页的数据，pageSize表示每页显示的数据条数
    @RequestMapping(value = "/show/{currPage}/{pageSize}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse list(@PathVariable("currPage") int currPage, @PathVariable("pageSize") int pageSize) {
        return new BaseResponse<>("000", conferenceRoomServices.list(currPage, pageSize));

    }


    /**
     * 更新会议室表信息
     * @param conferenceRoom
     * @return BaseResponse
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse UpdateConferenceRoom(@RequestBody ConferenceRoom conferenceRoom) {

        return new BaseResponse<>("000",conferenceRoomServices.updateConferenceRoom(conferenceRoom)) ;
    }

    /**
     *通过会议室id获取会议室信息
     * @param id
     * @return BaseResponse
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse GetConferenceRoom(@PathVariable Integer id) {

        return new BaseResponse<>("000", conferenceRoomServices.getConferenceRoomById(id));
    }

    /**
     * 通过会议室id删除会议室信息
     * @param id
     * @return
     */

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse DeleteConferenceRoom(@PathVariable Integer id) {

        return new BaseResponse<>("000",conferenceRoomServices.deleteConferenceRoomById(id)) ;
    }

    /**
     * 添加会议室
     * @param conferenceRoom
     * @return BaseResponse
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse AddConferenceRoom(@RequestBody ConferenceRoom conferenceRoom) {

        return new BaseResponse<>("000",conferenceRoomServices.insertConferenceRoom(conferenceRoom));
    }

    /**根据会议室属性查找会议室信息
     * @param conferenceRoomPosition 　位置
     * @param seatNumber             　座位数
     * @param haveProjection         　是否有投影仪
     * @param haveWhiteBoard         是否有白板
     * @return BaseResponse
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse SelectRoomByCondition(@RequestParam(value = "conferenceRoomPosition", required = false) String conferenceRoomPosition,
            @RequestParam(value = "seatNumber", required = false) Integer seatNumber,
            @RequestParam(value = "option", required = false, defaultValue = "０") Integer option,
            @RequestParam(value = "haveProjection", required = false) Integer haveProjection,
            @RequestParam(value = "haveWhiteBoard", required = false) Integer haveWhiteBoard) {
        Map<String, Object> params = new HashMap<>();
        params.put("conferenceRoomPosition", conferenceRoomPosition);
        params.put("seatNumber", seatNumber);
        params.put("option", option);
        params.put("haveProjection", haveProjection);
        params.put("haveWhiteBoard", haveWhiteBoard);


        return new BaseResponse<>("000", conferenceRoomServices.selectRoomByCondition(params));
    }

    @RequestMapping(value = "/insert200",method = RequestMethod.POST)
    public BaseResponse insertRoom200(){
        return new BaseResponse<>("000",conferenceRoomServices.insertRoom200());
    }


}
