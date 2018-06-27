package com.ycjcjy.gene.web.action.system.util;

/**
 * Created by szc on 2018/5/8.
 */
public class ResponseBean {
    private String message="系统繁忙";
    private Integer code = 1;
    private Integer flag = 0;
    private Object data;

    public void setSuccess(Object data){
        this.data = data;
        code = 1;
        flag = 0;
        message = "";
    }

    public void setSuccess(Object data,String message){
        this.data = data;
        code = 1;
        flag = 0;
        this.message = message;
    }

    public void setSuccess(Object data,String message,Integer flag){
        this.data = data;
        code = 1;
        this.flag = flag;
        this.message = message;
    }

    public void setError(Integer flag,String message){
        data = null;
        code = 0;
        this.flag = flag;
        this.message = message;
    }

    public void setError(String message){
        data = null;
        code = 0;
        flag = 0;
        this.message = message;
    }
    public void setError(){
        data = null;
        code = 0;
        flag = 0;
        message="系统繁忙";
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int init(int totalCount,int pageSize) {
        int totalPages = 0;
        if (totalCount % pageSize == 0) {
          totalPages = totalCount / pageSize;
        } else {
           totalPages = totalCount / pageSize + 1;
        }
        return totalPages;
    }
}
