package com.ycjcjy.gene.VO;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @version V1.0
 * @class_name: com.ycjcjy.gene.VO
 * @param: $METHOD_PARAM$
 * @describe: TODO
 * @creat_user: MAT
 * @creat_time: 2018/5/15 17:26
 **/
public class AppointmentTimeVO {
    private Timestamp appointment_start_time;
    private Timestamp appointment_end_time;

    public Timestamp getAppointment_start_time() {
        return appointment_start_time;
    }

    public void setAppointment_start_time(Timestamp appointment_start_time) {
        this.appointment_start_time = appointment_start_time;
    }

    public Timestamp getAppointment_end_time() {
        return appointment_end_time;
    }

    public void setAppointment_end_time(Timestamp appointment_end_time) {
        this.appointment_end_time = appointment_end_time;
    }
}
