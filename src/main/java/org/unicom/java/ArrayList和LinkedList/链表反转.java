package org.unicom.java.ArrayList和LinkedList;

/**
 * @description 链表反转（单链表反转，因为双链表反转无意义）
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/21 22:27
 */

public class 链表反转 {
    private static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
        }
    }
    private static Node head;

    public static void main(String[] args) {
        //初始化链表
        head = new Node(3);
        head.next = new Node(5);
        Node temp = head.next;
        temp.next = new Node(1);
        temp = temp.next;
        temp.next = new Node(4);
        temp = temp.next;
        temp.next = new Node(9);

        //逆序前输出链表
        temp = head;
        while (temp != null){
            System.out.print(temp.data+"\t");
            temp = temp.next;
        }

        System.out.println();
        reverseLinkedList();
        /**
         * 逆序输出后链表
         */

        temp = head;
        while (temp != null){
            System.out.print(temp.data+"\t");
            temp = temp.next;
        }

    }

    public static void reverseLinkedList(){
        if (head == null || head.next == null){
            return;
        }

        Node p1 = head;
        Node p2 = head.next;
        Node p3 = null;

        while (p2 != null){
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        head.next = null;
        head = p1;
    }

}
