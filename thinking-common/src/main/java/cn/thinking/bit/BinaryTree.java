package cn.thinking.bit;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Node {
    public Node left = null;
    public Node right = null;
    public int data = 0;
}

public class BinaryTree {
    public static void main(String[] args) {
        List<Integer> nodes = Arrays.asList(3, 4, 76, 8, 9, 109);
        Iterator<Integer> it = nodes.iterator();
        while (it.hasNext()) {
            int n = it.next();
            Node node = new Node();
            while (n-- > 0) {
                int m = it.next();
                buildingBST(node, m); //构建搜索二叉树
            }
            System.out.println(node.data + " ");
        }
    }

    public static Node buildingBST(Node node, int m) {
        if (node.data <= 0) { //无创建
            node.data = m;
            return node;
        }
        if (node.data > m) {
            if (node.left == null) {
                node.left = new Node();
                node.left.data = m;
            } else {
                buildingBST(node.left, m);
            }
            return node;
        }
        if (node.right == null) {
            node.right = new Node();
            node.right.data = m;
        } else {
            buildingBST(node.right, m);
        }
        return node;
    }
}
