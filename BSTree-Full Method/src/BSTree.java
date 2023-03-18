/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public void visited(Node p) {
        System.out.print(p.inf + " ");
    }

    public void insert(int x) {
        if (isEmpty()) {
            root = new Node(x);
        } else {
            Node p = root;
            Node par = p;
            while (p != null) {
                if (x > p.inf) {
                    par = p;
                    p = p.right;
                } else if (x < p.inf) {
                    par = p;
                    p = p.left;
                } else {
                    return;//x==p.inf da ton tai
                }
            }
            if (par.inf < x) {
                par.right = new Node(x);
            } else {
                par.left = new Node(x);
            }
        }
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

    public void readDataFromFile(String fname) throws IOException {
        File f = new File(fname);
        if (!f.isFile()) {
            return;
        }
        RandomAccessFile fr = new RandomAccessFile(f, "r");
        String s;
        while ((s = fr.readLine()) != null) {
            String[] arr = s.split("[ ]+");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() > 0) {
                    insert(Integer.parseInt(arr[i]));
                }
            }
        }
    }

    public void Bread_First(Node p) throws Exception {
        if (p == null) {
            return;
        }
        Queue myqueue = new Queue<Node>();
        myqueue.enqueue(p);
        Node q = null;
        while (!myqueue.isEmpty()) {
            q = (Node) myqueue.dequeue();
            visited(q);
            if (q.left != null) {
                myqueue.enqueue(q.left);
            }
            if (q.right != null) {
                myqueue.enqueue(q.right);
            }
        }
    }

    public void removebyCopyL(int x) {
        Node p = root, par = root;
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
        //da tim dc node p co gia tri = x va node cha cua p
        if (p.left == null && p.right == null) {//xoa node la
            if (par.left == p) {
                par.left = null;
            } else {
                par.right = null;
            }
        } else if (p.left != null && p.right == null) {
            //xoa node chi co 1 con trai
            if (par.left == p) {
                par.left = p.left;
            } else {
                par.right = p.right;
            }
        } else if (p.left == null && p.right != null) {
            //xoa node chi co 1 con phai
            if (par.left == p) {
                par.left = p.right;
            } else {
                par.right = p.right;
            }
        } else {//xoa node co 2 con
            Node rightmost = p.left;
            Node par_rightmost = p;
            while (rightmost.right != null) {
                par_rightmost = rightmost;
                rightmost = rightmost.right;
            }
            p.inf = rightmost.inf;
            if (par_rightmost == p) {
                par_rightmost.left = rightmost.left;
            } else {
                par_rightmost.right = rightmost.left;
            }
        }

    }

    public void removebyCopyR(int x) {
        Node p = root, par = root;
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
        //da tim dc node p co gia tri = x va node cha cua p
        if (p.left == null && p.right == null) {//xoa node la
            if (par.left == p) {
                par.left = null;
            } else {
                par.right = null;
            }
        } else if (p.left != null && p.right == null) {
            //xoa node chi co 1 con trai
            if (par.left == p) {
                par.left = p.left;
            } else {
                par.right = p.right;
            }
        } else if (p.left == null && p.right != null) {
            //xoa node chi co 1 con phai
            if (par.left == p) {
                par.left = p.right;
            } else {
                par.right = p.right;
            }
        } else {//xoa node co 2 con
            Node lefttmost = p.right;
            Node par_lefttmost = p;
            while (lefttmost.left != null) {
                par_lefttmost = lefttmost;
                lefttmost = lefttmost.left;
            }
            p.inf = lefttmost.inf;
            if (par_lefttmost == p) {
                par_lefttmost.right = lefttmost.right;
            } else {
                par_lefttmost.left = lefttmost.right;
            }
        }

    }

    public int height(int x) {
        if (root == null) {
            return -1;
        }
        Node p = root, par = root;
        while (p != null && p.inf != x) {
            if (x > p.inf) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) {
            return -1;
        }
        if (p.left == null && p.right == null) {
            return 1;
        } else if (p.left != null && p.right == null) {
            return 1 + height(p.left.inf);
        } else if (p.left == null && p.right != null) {
            return 1 + height(p.right.inf);
        } else {
            return 1 + Integer.max(height(p.left.inf), height(p.right.inf));
        }

    }

    public void removeNodebyMergL(int key) {
        if (isEmpty()) {
            return;
        }
        Node p = root;
        Node par = null;
        while (p != null && p.inf != key) {
            if (p.inf < key) {
                par = p;
                p = p.right;
            } else if (p.inf > key) {
                par = p;
                p = p.left;
            }
        }
        if (p == null) {
            return;//ko co node can xoa
        }        //p  la node can xoa, par la node cha cua p
        if (p.left == null && p.right == null) {
             if (par.left == p) {
                par.left = null;
            } else {
                par.right = null;
            }
        } else if (p.left != null && p.right == null) {
            if (par.left == p) {
                par.left = p.left;
            } else {
                par.right = p.left;
            }
        } else if (p.left == null && p.right != null) {

            if (par.left == p) {
                par.left = p.right;
            } else {
                par.right = p.right;
            }
        } else { //if (p.left!=null && p.right!=null)
            //đã có node cần xóa là  P và node cha của nó là par
            //đi tìm node phải cùng của cây con trái (node có 
            //giá trị lớn nhất của cây con trái)

            Node rightMost = p.left;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }
            if (p == root) {
                root = root.left;
            } else {
                if (par.left == p) {
                    par.left = p.left;
                } else {
                    par.right = p.left;
                }
            }
            rightMost.right = p.right;
        }

    }

    public void removeNodebyMergR(int key) {
        if (isEmpty()) {
            return;
        }
        Node p = root;
        Node par = null;
        while (p != null && p.inf != key) {
            if (p.inf < key) {
                par = p;
                p = p.right;
            } else if (p.inf > key) {
                par = p;
                p = p.left;
            }
        }
        if (p == null) {
            return;//ko co node can xoa
        }        //p  la node can xoa, par la node cha cua p
        if (p.left == null && p.right == null) {            
             if (par.left == p) {
                par.left = null;
            } else {
                par.right = null;
            }
        } else if (p.left != null && p.right == null) {
            if (par.left == p) {
                par.left = p.left;
            } else {
                par.right = p.left;
            }
        } else if (p.left == null && p.right != null) {
            if (par.left == p) {
                par.left = p.right;
            } else {
                par.right = p.right;
            }
        } else { //if (p.left!=null && p.right!=null)
            //cách 2 cho con phải lên thay
            //tìm node trái cùng của của cây con phải
            //gắn node trái của node cần xóa 
            //vào node trái cùng đã tìm ở trên
            Node leftMost = p.right;
            while (leftMost.left != null) {
                leftMost = leftMost.left;
            }
            if (p == root) {
                root = root.right;
            } else {
                if (par.left == p) {
                    par.left = p.right;
                } else {
                    par.right = p.right;
                }
            }
            leftMost.left = p.left;
        }
    }
}
