package com.ycjcjy.gene.VO;

/**
 * @version V1.0
 * @class_name: com.ycjcjy.gene.VO
 * @param: $METHOD_PARAM$
 * @describe: TODO
 * @creat_user: MAT
 * @creat_time: 2018/5/16 23:12
 **/
public class CoachLessonDoneVO {
    private Integer teacherids;
    private Integer lessonDone;

    public Integer getTeacherids() {
        return teacherids;
    }

    public void setTeacherids(Integer teacherids) {
        this.teacherids = teacherids;
    }

    public Integer getLessonDone() {
        return lessonDone;
    }

    public void setLessonDone(Integer lessonDone) {
        this.lessonDone = lessonDone;
    }
}
