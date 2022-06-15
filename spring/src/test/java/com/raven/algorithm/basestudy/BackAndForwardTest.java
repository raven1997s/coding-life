package com.raven.algorithm.basestudy;

import com.raven.algorithm.structure.base_03_stack.ArrayList;
import com.raven.algorithm.structure.base_03_stack.List;
import com.raven.algorithm.structure.base_03_stack.Stack;
import org.junit.Test;

/**
 * @PackageName: com.raven.algorithm.basestudy
 * @ClassName: BackAndForward
 * @Blame: raven
 * @Date: 2022-06-15 23:25
 * @Description:
 */
public class BackAndForwardTest {

    private List<String> domainList = new ArrayList();

    private Stack<String> one = new Stack<>();
    private Stack<String> two = new Stack<>();

    @Test
    public void test() throws Exception {

        // todo:
        // 输入网址
        inputDomain("1");
        inputDomain("2");
        inputDomain("3");
        System.out.println(back());
        System.out.println(back());
        System.out.println(forward());
        inputDomain("4");
        System.out.println(one.pop());
        System.out.println(two.isEmpty());
        inputDomain("5");

    }


    /**
     * 输入网址
     *
     * @param domain
     */
    private void inputDomain(String domain) {
        // 如果网址曾经访问过，则清除记录two
        if (!domainList.contains(domain)) {
            while (!two.isEmpty()) {
                two.pop();
            }
        }
        domainList.add(domain);
        one.push(domain);
    }

    // 前进下一次
    private String forward() throws Exception {
        // 将two的top元素放到 one中，返回one新的top
        if (two.isEmpty()) {
            throw new Exception("前面没有访问记录了,不能前进了");
        }
        one.push(two.pop());
        return one.top();
    }

    /**
     * 回退上一次访问
     *
     * @return 最新的网站
     */
    private String back() throws Exception {
        if (one.isEmpty()) {
            throw new Exception("前面没有访问记录了,不能后退了");
        }
        // 将one的top元素放到 two中，返回one新的top
        two.push(one.pop());
        return one.top();
    }
}
