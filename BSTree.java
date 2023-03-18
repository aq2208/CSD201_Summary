/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bstree;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import sun.misc.Queue;

/**
 *
 * @author thangnv
 */
public class BSTree {

    Node root;

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int x) {
        if (isEmpty()) {
            root = new Node(x);
        } else {
            Node p = root;
            Node par = p;
            while (p != null) {
                if (x > p.inf) {//nam ben phai cua root
                    par = p;
                    p = p.right;
                } else if (x < p.inf) {//x nam ben trai
                    par = p;
                    p = p.left;
                } else {
                    return;
                }
            }
            if (x > par.inf) {
                par.right = new Node(x);
            } else {
                par.left = new Node(x);
            }
        }
    }

    public void visit(Node p) {
        System.out.print(p.inf + " ");
    }

    public void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    public void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    public void breadth_First(Node p) throws InterruptedException {
        if (p == null) {
            return;
        }
        Queue myqueue = new Queue();
        myqueue.enqueue(p);
        Node q = null;
        while (!myqueue.isEmpty()) {
            q = (Node) myqueue.dequeue();
            visit(q);
            if (q.left != null) {
                myqueue.enqueue(q.left);
            }
            if (q.right != null) {
                myqueue.enqueue(q.right);
            }
        }
    }

    public void readDataFromFile(String fname) throws IOException {
        File f = new File(fname);
        if (!f.isFile()) {
            return;
        }
        RandomAccessFile fr = new RandomAccessFile(fname, "r");
        String s;
        while ((s = fr.readLine()) != null) {
            String arr[] = s.split("[ ]+");
            for (int i = 0; i < arr.length; i++) {
                insert(Integer.parseInt(arr[i]));
            }
        }
    }

    public void remove(int x) {
        Node p = root, par = root;
        //tim node chua gia tri x
        //va node cha cua node do
        while (p != null && p.inf != x) {
            if (p.inf < x) {
                par = p;
                p = p.right;
            } else if (p.inf > x) {
                par = p;
                p = p.left;
            }
        }
        if (p == null) {
            return;
        }
//ko tim thay x trong cay
    //xoa node la (ko co con)
        if (p.right == null && p.left == null) {
            if (par.left==p) par.left=null;
            else par.right=null;
        }else if (p.left!=null && p.right==null){
            if (par.left==p) par.left=p.left;
            else par.right=p.left;
        }else if (p.left==null && p.right!=null){
            if (par.left==p) par.left=p.right;
            else par.right=p.right;
        }else{ //co 2 con
            //tim gia tri lon nhat cua cay con trai
            Node k=p.left;
            Node par_k=p;
            while (k.right!=null) {
                par_k=k;k=k.right;
            }
            p.inf=k.inf;
            par_k.right =k.left;

//                Node k = p.right;
//                Node par_k=p;
//                while (k.left!=null){
//                    par_k=k;k=k.left;
//                }
//                p.inf=k.inf;
//                par_k.left=k.right;
        }
    }
}
