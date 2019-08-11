package org.koudai.java.二叉树;

/**
 * @description 二叉树的先序遍历
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/3 00:37
 */
public class FirstTraversalBinaryTree {
   //https://www.cnblogs.com/yaobolove/p/6213936.html
    public static void main(String[] args) {//逆序建立，先建立子节点，再逆序往上建立，因为非叶子节点会使用到下面的节点，
        // 不逆序的话，初始化节点会报错
        Node J = new Node(8,null,null);
        Node H = new Node(4,null,null);
        Node G = new Node(2,null,null);
        Node F = new Node(7,null,J);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C); //根节点

        System.out.println("先序遍历的结果：");
        theFirstTraversal(A);

    }

    public static void printNode(Node node){
        System.out.print(node.getData()+"\t");
    }

    /**
     * 先序遍历
     * @param root
     */
    public static void theFirstTraversal(Node root){
        printNode(root);//先打印根节点（先序遍历）
        if (root.getLeftNode() != null){//使用递归进行遍历左孩子
            theFirstTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null){//使用递归进行遍历右孩子
            theFirstTraversal(root.getRightNode());
        }

    }
    //同理，中序，后序只需要改变打印根节点的顺序就可以实现。

}


class Node{
    private int data;
    private Node leftNode;
    private Node rightNode;
    public Node(int data,Node leftNode,Node rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}