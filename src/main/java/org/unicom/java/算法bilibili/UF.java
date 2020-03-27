package org.unicom.java.算法bilibili;

/**
 * @description 算法一：Union Find 并查集
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/10/6 22:06
 */
public class UF {
    int[] id;//点的父节点
    int[] sz;//点的权

    UF(int N)//N:构建器需要对象的数量作为参数
    {
        id = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p,int q){ //实现合并
        int pboot = find(p); //找到此点的根节点
        int qboot = find(q); //同上
        if (pboot == qboot) return; //如果在同一通路，返回
        if (sz[pboot] >= sz[qboot])
        {
            id[qboot] = pboot; sz[pboot] += sz[qboot];
        }
        else
        {
            id[qboot] = pboot; sz[qboot] += sz[pboot];
        } //将权值小的根节点连在权值大的根节点上，并将权值相加赋给连接后的根节点

    }

    /**
     * 实现查找功能
     * quick-find实现
     * 3.1 核心思路
     * 数组中的每个元素的下标为触点，每个元素的值为触点值，触点值相等的的触点在一个连接上所以：
     * 1. 要判断两个触点是否在一个连接上，只需要判断两个触点对应的触点值是否相等即可
     * 2. 要把不在一个连接上的触点连接起来，只需要让两个触点的触点值相等即可。
     * @param p 要找的点
     * @return p的根节点（代表元）
     */
    int find(int p){
        int temp = p;
        while (p != id[p]){//一直向上找父节点，直到节点的父节点为自身，则为根节点
            p = id[p];
        }
        while (temp != id[temp]) //将找过的点全部连在根节点上
        {
            int temp2 = temp;
            temp = id[temp];
            id[temp2] = p;
        }
        return p;
    }

    boolean connected(int p ,int q){ //实现连接
        return find(p) == find(q);
    }


    public static void main(String[] args) {
        UF uf = new UF(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        System.out.println(uf.connected(0,7));
        System.out.println(uf.connected(8,9));

        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        System.out.println(uf.connected(8, 9));
        System.out.println(uf.connected(1, 0));
        System.out.println(uf.connected(6, 7));
        for (int i = 0; i < 10; i++)
        {
            System.out.println(uf.find(i));
        }
    }
}
