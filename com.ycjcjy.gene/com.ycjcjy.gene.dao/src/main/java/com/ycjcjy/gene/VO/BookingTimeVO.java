package com.ycjcjy.gene.VO;

import java.sql.Timestamp;

/**
 * @version V1.0
 * @class_name: com.ycjcjy.gene.VO
 * @param: $METHOD_PARAM$
 * @describe: TODO
 * @creat_user: MAT
 * @creat_time: 2018/5/15 17:03
 **/
public class BookingTimeVO {
    private String Time;
    private String flag;
    private Timestamp times;

    public Timestamp getTimes() {
        return times;
    }

    public void setTimes(Timestamp times) {
        this.times = times;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
