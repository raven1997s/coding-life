package com.raven.algorithm.structure.base_03_stack;

/**
 * @PackageName: com.raven.algorithm.structure.base_03_stack
 * @ClassName: Stack
 * @Blame: raven
 * @Date: 2022-06-15 22:52
 * @Description: 栈
 */
public class Stack<E> {

    private ArrayList<E> list = new ArrayList<>();

    public void push(E element) {
        list.add(element);
    }

    public void clear(){
        list.clear();
    }
    public E pop() {
        return list.remove(list.size - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size;
    }

    public E top() {
        return list.get(list.size - 1);
    }

}
