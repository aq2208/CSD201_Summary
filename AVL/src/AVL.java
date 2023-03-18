
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import sun.misc.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thangnv
 */
public class AVL {

    Node root;

    public boolean isEmpty() {
        return root == null;
    }

    public void visited(Node p) {
        System.out.print(p.info + " ");
    }

   
    public void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visited(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visited(p);
        inOrder(p.right);
    }

    public void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visited(p);
    }

    public void breadth(Node p) throws InterruptedException {
        if (p == null) {
            return;
        }
        Queue myqueue = new Queue();
        myqueue.enqueue(p);
        Node q = null;
        while (!myqueue.isEmpty()) {
            q = (Node) myqueue.dequeue();
//            System.out.print(q.info + "(" + (height(q.right) - height(q.left)) + ") ");
            System.out.print(q.info + " ");
            if (q.left != null) {
                myqueue.enqueue(q.left);
            }
            if (q.right != null) {
                myqueue.enqueue(q.right);
            }
        }
    }

    public void remove(int x) {
        Node p = root, par = p;
        while (p != null && p.info.price != x) {
            if (x > p.info.price) {
                par = p;
                p = p.right;
            } else if (p.info.price > x) {
                par = p;
                p = p.left;
            }
        }
        if (p == null) {
            return;
        }
        //tìm dc node p chứa x và node cha của p la par
        if (p.left == null && p.right == null) {// p là node lá (ko có con)
            if (par.left == p) {
                par.left = null;
            } else {
                par.right = null;
            }
        } else if (p.left != null && p.right == null) {//chỉ có 1 con trái
            if (par.left == p) {
                par.left = p.left;
            } else {
                par.right = p.left;
            }
        } else if (p.left == null && p.right != null) {//chỉ có 1 con phải
            if (par.left == p) {
                par.left = p.right;
            } else {
                par.right = p.right;
            }
        } else {//có cả 2 con
            //c1 tìm max của cây con trái
            Node k = p.left;
            Node par_K = p;
            while (k.right != null) {
                par_K = k;
                k = k.right;
            }
            p.info = k.info;
            if (par_K == p) {
                p.left = k.left;
            } else {
                par_K.right = k.left;
            }
            //c2 tìm min của cấy con phải

        }
        root = balance(root,x);
    }

    int max(int a, int b) {
        int max = a > b ? a : b;
        return max;
    }

    public int height(Node p) {
        if (p == null) {
            return 0;
        } else if (p.left == null && p.right == null) {
            return 1;
        } else if (p.left == null && p.right != null) {
            return 1 + height(p.right);
        } else if (p.left != null && p.right == null) {
            return 1 + height(p.left);
        } else {
            return 1 + max(height(p.left), height(p.right));
        }
    }

    public int factor(Node p) {
        return height(p.right) - height(p.left);
    }
 public void insert(String xOwner, int xPrice) {
        if (isEmpty()) {
            root = new Node(new Car(xOwner, xPrice));
            return;
        }
        Node p = root;
        Node par = p;
        while (p != null) {
            if (xPrice > p.info.price) {
                par = p;
                p = p.right;
            } else if (xPrice < p.info.price) {
                par = p;
                p = p.left;
            } else {
                return;
            }
        }
        //p==null; par la node cha cua node can chen
        Node X = new Node(new Car(xOwner, xPrice));
        if (xPrice > par.info.price) {
            par.right = X;//new Node(x);          
        } else {
            par.left = X;//new Node(x);
        }
        root = balance(root,xPrice);
    }

    public Node balance(Node p,int x) {

        if (p == null) {
            return null;
        }
        if (p.info.price > x) {
            p.left = balance(p.left,x);
        } else
        if (p.info.price < x) {
            p.right = balance(p.right,x);
        }
//        if (p.left!=null) p.left=balance(p.left,x);
//        else if (p.right!=null) p.right=balance(p.right,x);
//        
        if (factor(p) == -2) { //lệch trái
            if (factor(p.left) == -1) {//lệch trái - trái
                p = right_Rotate(p);
            } else {
                p = left_right_Rotate(p);//lệch trái- phải
            }
        } else if (factor(p) == 2) {
            // if (height(p.right.right)>height(p.right.left)){//phai phai
            if (factor(p.right) == 1) { //lệch phải phải
                p = left_Rotate(p);
            } else {
                p = right_left_Rotate(p); //lệch trái trái
            }
        }
        return p;
    }

    public void readDataFromFile(String fname) throws FileNotFoundException, IOException {
        File f = new File(fname);
        if (!f.isFile()) {
            return;
        }
        RandomAccessFile fr = new RandomAccessFile(fname, "r");
        String line1 = fr.readLine();
        String line2 = fr.readLine();
        String[] arr1 = line1.split("[ ]+");
        String[] arr2 = line2.split("[ ]+");
        System.out.println("data input");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print("(" + arr1[i] + " " + arr2[i] + ") ");
            insert(arr1[i], Integer.parseInt(arr2[i]));
        }
        System.out.println("");
    }

    public Node right_Rotate(Node p) {
        if (p == null || p.left == null) {
            return p;
        }
        Node newroot = p.left;
        p.left = newroot.right;
        newroot.right = p;
        return newroot;
    }

    public Node left_Rotate(Node p) {
        if (p == null || p.right == null) {
            return p;
        }
        Node newroot = p.right;
        p.right = newroot.left;
        newroot.left = p;
        return newroot;
    }

    public Node left_right_Rotate(Node p) {
        p.left = left_Rotate(p.left);
        p = right_Rotate(p);
        return p;
    }

    public Node right_left_Rotate(Node p) {
        p.right = right_Rotate(p.right);
        p = left_Rotate(p);
        return p;
    }

}
