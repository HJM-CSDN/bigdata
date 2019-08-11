package org.koudai.java.ArrayList和LinkedList;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @description 输入一个链表，从头到尾打印链表的每个元素的值
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/2 23:19
 */
/**
 * 剑指offer面试题5：从尾到头打印链表
 * 输入一个链表的头结点，从尾到头打印出每个结点的值
 * 解决方案一：首先遍历链表的节点后打印，典型的“后进先出”，可以使用栈来实现这种顺序。
 * 解决方案二：栈的本质就是递归，直接使用递归的方式，打印一个节点的时候先打印它后面的节点，再打印该节点自身，实现反向打印
 * 解决方案三：遍历链表，把链表中的元素复制到ArrayList中，然后逆序打印ArrayList中的元素
 * 解决方案四：前三种解决方案本身属于在打印链表的时候不修改链表本身结构，
 * 在允许修改链表结构的情况下可以把链表中的节点指针反转过来，改变链表方向，然后重新遍历打印改变方向后的链表
 */
public class LinkedList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        /**
         * 方案一:从尾到头打印
         */
        System.out.println("方案一：");
        printLinkedList(listNode);

        System.out.println();

        /**
         * 方案三：从头到尾打印
         */
        System.out.println("方案三：");
        ArrayList arrayList = returnLinkedList(listNode);
        for (int i = 0;i<arrayList.size();i++){
            System.out.print(arrayList.get(arrayList.size()-i-1)+" ");
        }


    }

    /**
     * 方案一：首先遍历链表的节点后打印，典型的“后进先出”，可以使用栈来实现这种顺序。
     */
    public static void printLinkedList(ListNode listNode){
        Stack<Integer> stack = new Stack<>();
        while (listNode!=null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }

    /**
     * 方案三：遍历链表，把链表中的元素复制到ArrayList中，然后逆序打印ArrayList中的元素
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> returnLinkedList(ListNode listNode){
        Stack<Integer> stack = new Stack<>();
        while (listNode!=null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }

}

class ListNode{
    int val;
    ListNode next = null;

    public ListNode(){};
    public ListNode(int val){
        this.val = val;
    }
}
