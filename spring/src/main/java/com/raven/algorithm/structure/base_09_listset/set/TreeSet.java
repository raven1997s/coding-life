package com.raven.algorithm.structure.base_09_listset.set;

import com.raven.algorithm.structure.base_09_listset.tree.BinaryTree;
import com.raven.algorithm.structure.base_09_listset.tree.RBTree;

/**
 * Description:
 * date: 2022/7/20 21:31
 *
 * @author raven
 */
public class TreeSet<E> implements Set<E> {
    RBTree<E> rbTree = new RBTree<>();

    @Override
    public int size() {
        return rbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    @Override
    public boolean contains(E element) {
        return rbTree.contains(element);
    }

    @Override
    public void add(E element) {
        rbTree.add(element);
    }

    @Override
    public void remove(E element) {
        rbTree.remove(element);
    }

    @Override
    public void clear() {
        rbTree.clear();
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        rbTree.inorder(new BinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}