package org.koudai.java.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description 二叉树层序遍历
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/12/24 18:02
 */

/**
 *  剑指offer面试题23：从上到下打印二叉树（树的层序遍历）
 *  题目：从上往下打印出二叉树的每个节点，同一层的结点按照从左往右的顺序打印。
 *  解题思路：二叉树的层序遍历，在打印一个节点的时候，要把他的子节点保存起来,打印第一层要把第二层的节点保存起来，
 *  打印第二层要把第三层的结点保存起来，以此类推。可以使用的容器是队列，每一次打印一个结点的时候，
 *  如果该结点有子结点，则把该点的子结点放到队列的末尾，
 *  接下来从队列的头部取出最早进入队列的节点，重复打印操作。
 */
public class 二叉树层序遍历 {
    static class BinaryTreeNode{
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
        public BinaryTreeNode(int value,BinaryTreeNode left,BinaryTreeNode right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void printBinaryTree(BinaryTreeNode node){
        if (node == null){
            throw new RuntimeException("invalid parameter");
        }
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(node);

        while (!queue.isEmpty()){
            //pop：相当于get的操作，就是只是查看。从此列表所表示的堆栈处弹出一个元素。
            //poll：相当于先get然后再remove掉，就是查看的同时，也将这个元素从容器中删除掉。
            BinaryTreeNode currentNode = queue.poll();
            System.out.print(currentNode.value+",");
            if (currentNode.left!=null){
                queue.add(currentNode.left);
            }
            if (currentNode.right!=null){
                queue.add(currentNode.right);
            }
        }

    }

    public static void main(String[] args) {
        BinaryTreeNode node1=new BinaryTreeNode(5,null,null);
        BinaryTreeNode node2=new BinaryTreeNode(7,null,null);
        BinaryTreeNode node3=new BinaryTreeNode(9,null,null);
        BinaryTreeNode node4=new BinaryTreeNode(11,null,null);
        BinaryTreeNode node5=new BinaryTreeNode(6,node1,node2);
        BinaryTreeNode node6=new BinaryTreeNode(10,node3,node4);
        BinaryTreeNode node7=new BinaryTreeNode(8,node5,node6);
        printBinaryTree(node7);
    }
}
