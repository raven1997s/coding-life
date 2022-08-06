package com.raven.data_structures_and_algorithms.basestudy;

import com.raven.data_structures_and_algorithms.structure.base_03_stack.ArrayList;
import com.raven.data_structures_and_algorithms.structure.base_03_stack.List;
import com.raven.data_structures_and_algorithms.structure.base_03_stack.Stack;
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
        // ONE 1 2 3
        System.out.println(back()); // one 1 2 two 3
        System.out.println(back()); // one 1  two 3 2
        System.out.println(forward()); // one 1 2 two 3
        inputDomain("4"); // 4
        // ONE 1 2 4 two 0
        System.out.println(one.top() == "4"); // 4
        System.out.println(back());
        // one 1 2 tow 4
        System.out.println( "two.isEmpty()" + two.isEmpty());
        System.out.println( "one.size()" + one.size());
        System.out.println(two.size() == 1);
    }


    /**
     * 输入网址
     *
     * @param domain
     */
    private void inputDomain(String domain) {
        while (!two.isEmpty()) {
            two.pop();
        }
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
