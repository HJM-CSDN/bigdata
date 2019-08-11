package org.koudai.java.LeetCode刷题指南;


/**
 * @description 83.删除排序链表中的重复元素
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/22 19:44
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        ListNode temp = head.next;
        temp.next = new ListNode(3);
        temp = temp.next;
        temp.next = new ListNode(5);


        //打印去重前链表
        temp = head;
        while (temp != null){
            System.out.print(temp.val+"\t");
            temp = temp.next;
        }

        System.out.println();
        ListNode head2 = deleteDuplicates(head);
        //打印去重后链表
        temp = head2;
        while (temp != null){
            System.out.print(temp.val+"\t");
            temp = temp.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head){
        ListNode current = head;
        while (current != null && current.next != null){
            if (current.next.val == current.val){
                current.next = current.next.next;
            }else {
                current = current.next;
            }
        }
        return head;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
    }


}