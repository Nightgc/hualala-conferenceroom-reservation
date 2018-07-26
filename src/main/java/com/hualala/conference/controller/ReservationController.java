package com.hualala.conference.controller;

import com.hualala.conference.bean.Reservation;
import com.hualala.conference.dto.BaseResponse;
import com.hualala.conference.service.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {
    private final ReservationServices reservationServices;

    @Autowired
    public ReservationController(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;

    }

    /**
     * 预约会议室
     * @param reservation reservation
     * @return BaseResponse
     */
    @RequestMapping(value = "/add/reservation", method = RequestMethod.POST)
    public BaseResponse InsertReservation(@RequestBody Reservation reservation) {

        return new BaseResponse<>("000", reservationServices.insertReservation(reservation));

    }

    /**
     * 根据员工姓名取消该姓名的所有预约
     * @param name name
     * @return BaseResponse
     */
    @RequestMapping(value = "/delete/reservation/{name}", method = RequestMethod.DELETE)
    public BaseResponse DeleteReservation(@PathVariable String name) {
        return new BaseResponse<>("000", reservationServices.deleteReservation(name));
    }

    /**
     * 根据预约id取消该id预约信息
     * @param id id
     * @return BaseResponse
     */
    @RequestMapping(value = "/delete/reservationId/{id}",method = RequestMethod.DELETE)
    public BaseResponse deleteReservationById(@PathVariable Long id) {

        return new BaseResponse<>("000",reservationServices.deleteReservationById(id));
    }

    /**
     * 更新预约表
     *
     * @param reservation reservation
     * @return BaseResponse
     */
    @RequestMapping(value = "/update/reservation", method = RequestMethod.PUT)
    public BaseResponse UpdateReservation(Reservation reservation) {
        return new BaseResponse<>("000", reservationServices.updateReservation(reservation));
    }

    /**
     * 通过预约表id获取该id预约表信息
     * @param id id
     * @return BaseResponse
     */
    @RequestMapping(value = "/get/reservation/{id}", method = RequestMethod.GET)
    public BaseResponse SelectReservationById(@PathVariable Long id) {

        return new BaseResponse<>("000", reservationServices.selectReservationById(id));
    }

    /**
     * 通过会议室获取该id的预约表信息
     *
     * @param roomId roomId
     * @return BaseResponse
     */
    @RequestMapping(value = "/get/reservationRoom/{roomId}", method = RequestMethod.GET)
    public BaseResponse SelectReservationByRoomId(@PathVariable Integer roomId) {

        return new BaseResponse<>("000", reservationServices.selectReservationByRoom(roomId));
    }

    /**
     * 通过会议室id查询会议室情况
     * @param id id
     * @return BaseResponse
     */
    @RequestMapping(value = "/getRoomAndReservation/{id}", method = RequestMethod.GET)
    public BaseResponse getRoomAndReservation(@PathVariable Integer id) {
        return new BaseResponse<>("000", reservationServices.getRoom(id));
    }

    /**
     * 更新预约表
     *
     * @param reservation reservation
     * @return BaseResponse
     */
    @RequestMapping(value = "/updateReservation", method = RequestMethod.PUT)
    public BaseResponse updateReservation(@RequestBody Reservation reservation) {
        return new BaseResponse<>("000", reservationServices.updateReservation(reservation));
    }

    /**
     * 给一个时间段返回可用的会议室 输入时需要对startTime和stopTime进行Unix timestamp格式转换
     * @param starttime starttime
     * @param stoptime  stoptime
     * @return BaseResponse
     */
    @RequestMapping(value = "/getCanUse/{starttime}/{stoptime}",method = RequestMethod.GET)
    public BaseResponse canUseRoom(@PathVariable Long starttime,@PathVariable Long stoptime){
        Long startTime = System.currentTimeMillis();
        reservationServices.selectCanUseRoom(starttime,stoptime);
        Long stopTime = System.currentTimeMillis();
        System.out.println((stopTime-startTime)+"ms");
        return new BaseResponse<>("000",reservationServices.selectCanUseRoom(starttime,stoptime));
    }

    @RequestMapping(value = "/save100w", method = RequestMethod.POST)
    public BaseResponse save100w(){
        return new BaseResponse<>("000",reservationServices.save100w());
    }

    @RequestMapping(value = "/getRevation{startTime}/{stopTime}")
    public BaseResponse selectReservationByTime(@PathVariable Long startTime,@PathVariable Long stopTime){
        Long startTime1 = System.currentTimeMillis();
        reservationServices.selectReservationByTime(startTime,stopTime);
        Long stopTime1 = System.currentTimeMillis();
        System.out.println((stopTime1-startTime1)+"ms");
        return new BaseResponse<>("000",reservationServices.selectReservationByTime(startTime,stopTime));

    }
}
