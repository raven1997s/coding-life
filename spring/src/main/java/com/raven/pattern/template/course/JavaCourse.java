package com.raven.pattern.template.course;

/**
 * @PackageName: com.raven.pattern.template.course
 * @ClassName: JavaCourse
 * @Blame: raven
 * @Date: 2021-08-03 16:56
 * @Description:
 */
public class JavaCourse extends AbstractNetworkCourse {

    private boolean needHomeworkFlag;

    public JavaCourse(boolean needHomeworkFlag) {
        this.needHomeworkFlag = needHomeworkFlag;
    }

    /**
     * 流程微调，通过构造参数来确定是否执行 提交作业的逻辑
     * @return
     */
    @Override
    public boolean needHomework() {
        return needHomeworkFlag;
    }

    @Override
    void submitJob() {
        System.out.println("如果java老师有布置作业，我就写！");
    }



}
