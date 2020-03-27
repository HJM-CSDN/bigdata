package org.unicom.java.二叉树;

/**
 * @description 求二叉树的深度
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2015/12/28 15:05
 */

public class BinTree {
    private Node root;

    //添加节点
    public void add(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            root.addNode(data);
        }
    }

    public void printBinaryTree() {
        root.print();
    }


    //定义一个二叉树节点
    class Node {
        int data; //节点值
        private Node left;
        private Node right;

        public Node(int data) { //带参构造节点方法
            this.data = data;
        }


        //添加节点(左/右)
        public void addNode(int data) {
            if (this.data > data) {
                if (this.left == null) {
                    this.left = new Node(data);
                } else {
                    //递归插入左子树
                    this.left.addNode(data);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(data);
                } else {
                    this.right.addNode(data);
                }
            }

        }

        public void print() {
            if (this.left != null) {
                this.left.print();
            }
            System.out.print(this.data + "->");
            if (this.right != null) {
                this.right.print();
            }
        }
    }

    //深度
    public static int getMaxDepth(Node root) {
        //根节点是空直接返回深度为0
        if (root == null)
            return 0;
            //根节点不为空,但是其左右子树为空
        else if (root.left == null && root.right == null) {
            return 1;
        } else {
            //根节点左右子树有不为空的情况
            int left = getMaxDepth(root.left);
            int right = getMaxDepth(root.right);
            return 1 + (left > right ? left : right);
        }
    }
    public static void main(String[] args) {
        BinTree bt = new BinTree();
        bt.add(1);
        bt.add(3);
        bt.add(5);
        bt.add(2);
        bt.add(6);
        bt.printBinaryTree();
        System.out.println("树的深度为:");
        System.out.println(getMaxDepth(bt.root));
    }
}
