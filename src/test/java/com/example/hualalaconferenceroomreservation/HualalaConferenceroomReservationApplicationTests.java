package com.example.hualalaconferenceroomreservation;

import com.hualala.conference.HualalaConferenceroomReservationApplication;
import com.hualala.conference.service.ConferenceRoomServices;
import com.hualala.conference.service.ReservationServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.Test;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HualalaConferenceroomReservationApplication.class)
public class HualalaConferenceroomReservationApplicationTests {




    @Autowired
   ReservationServices reservationServices;


    @Test
    public void contextLoads() {
        Random random = new Random();
        for (int i = 0;i<10 ;i++) {
            long start =System.currentTimeMillis();
            reservationServices.selectCanUseRoom((long)random.nextInt(100000000)+1533333333,
                    (long)random.nextInt(10000000)+1633333333);
            long stop = System.currentTimeMillis();
            System.out.println((stop-start)+"ms");
        }
    }


}
