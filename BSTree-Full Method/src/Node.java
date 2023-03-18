/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author thangnv
 */
public class Node {
    int inf;
    Node left,right;

    public Node() {
    }
    public Node(int inf, Node left, Node right) {
        this.inf = inf;
        this.left = left;
        this.right = right;
    }
    public Node(int inf ) {
        this.inf = inf;
        this.left = null;
        this.right = null;
    }
}
