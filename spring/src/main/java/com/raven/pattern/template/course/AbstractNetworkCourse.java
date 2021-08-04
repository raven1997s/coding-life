package com.raven.pattern.template.course;

/**
 * @PackageName: com.raven.pattern.template.course
 * @ClassName: NetworkCourse
 * @Blame: raven
 * @Date: 2021-08-03 16:17
 * @Description: 上网课案例：
 * 定义课程模板类，规范创建课程的步骤
 * 模板方法模式使用抽象类规定整个流程
 */
public abstract class AbstractNetworkCourse {

    protected final void createCourse() {
        // 1.发布预习资料
        this.releasePreviewMaterials();
        // 2.准备PPT
        this.preparePPT();
        // 3.在线授课
        this.onlineDelivery();
        // 4.提交课件
        this.postNote();

        // 5.提交作业
        // 判断是否需要作业，子类继承时指定，如果布置了作业才需要提交作业
        if (needHomework()) {
            this.submitJob();
        }
    }

    /**
     * 钩子方法，实现流程的微调
     * @return
     */
    protected boolean needHomework() {
        return false;
    }

    /**
     * 如果布置作业，则需要提交作业，具体有没有作业，由子类自定义
     */
    abstract void submitJob();

    /**
     * 使用final定义方法，子类不能够覆盖重写
     */
    final void postNote() {
        System.out.println("老师发课件了~");
    }

    final void onlineDelivery() {
        System.out.println("老师在线授课啊");
    }

    final void preparePPT() {
        System.out.println("老师在准备PPT啊");
    }


    final void releasePreviewMaterials() {
        System.out.println("老师发预习资料了~");
    }
}
