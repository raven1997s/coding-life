package com.raven.pattern.delegate.mvc;

import com.google.common.collect.Lists;
import com.raven.pattern.delegate.mvc.controller.OrderController;
import com.raven.pattern.delegate.mvc.controller.SystemController;
import com.raven.pattern.delegate.mvc.controller.UserController;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @PackageName: com.raven.pattern.delegate.mvc
 * @ClassName: DispatcherServlet
 * @Blame: raven
 * @Date: 2021-08-01 14:22
 * @Description: 模拟手写mvc dispatcherServlet 委派设计模式
 * <p>
 * 模拟 根据不同的uri 调用不同的controller方法
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * 通过工厂模式将controller封装到List集合中
     */
    private List<Handler> handlerMapping = Lists.newArrayList();

    /**
     * 初始化mapping
     * 通过反射将所有的实体属性封装到List中
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        Class<OrderController> orderClazz = OrderController.class;
        try {
            handlerMapping.add(new Handler(orderClazz.newInstance(),
                    orderClazz.getMethod("getOrderById", String.class),
                    "getOrderById"
            ));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 完成调度
//        doDispatch(req, resp);

        doDispatch2(req, resp);
    }

    /**
     * 委派模式 将服务调用的事情委派给dispatch
     * 通过dispatch 完成调度，根据请求不同的uri 调用不同controller代码 执行业务逻辑
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURI = req.getRequestURI();
        String mid = req.getParameter("mid");

        if (StringUtils.equals("getOrderById", requestURI)) {
            new OrderController().getOrderById(mid);
        } else if (StringUtils.equals("getUserById", requestURI)) {
            new UserController().getUserById(mid);
        } else if (StringUtils.equals("logout", requestURI)) {
            new SystemController().logout();
        } else {
            resp.getWriter().write("404 not found !");
        }
    }

    /**
     * 通过反射执行根据不同的url 执行不同的controller中的策略
     * @param req
     * @param resp
     */
    private void doDispatch2(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String mid = req.getParameter("mid");

        Handler handler = null;
        for (Handler h : handlerMapping) {
            if (StringUtils.equals(requestURI, h.getUrl())) {
                handler = h;
                break;
            }
        }

        try {
            Object o = handler.getMethod().invoke(handler.getController(), mid);
            resp.getWriter().write(o.toString());
        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }

    @Data
    class Handler {
        private Object controller;
        private Method method;
        private String url;

        public Handler(Object controller, Method method, String url) {
            this.controller = controller;
            this.method = method;
            this.url = url;
        }
    }
}
