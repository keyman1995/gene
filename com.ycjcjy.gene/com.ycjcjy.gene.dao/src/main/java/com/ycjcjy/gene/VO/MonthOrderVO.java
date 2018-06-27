package com.ycjcjy.gene.VO;

/**
 * @version V1.0
 * @class_name: com.ycjcjy.gene.VO
 * @param: $METHOD_PARAM$
 * @describe: TODO
 * @creat_user: MAT
 * @creat_time: 2018/6/12 15:19
 **/
public class MonthOrderVO {
    private String months;
    private Integer used;
    private Integer total;
    private Integer leftnum;
    private Integer overused;

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLeftnum() {
        return leftnum;
    }

    public void setLeftnum(Integer leftnum) {
        this.leftnum = leftnum;
    }

    public Integer getOverused() {
        return overused;
    }

    public void setOverused(Integer overused) {
        this.overused = overused;
    }
}
