
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thangnv
 */
public class Graph {

    int n;
    char Vertex[];
    int[][] adj;//adj[v][i] i se la dinh lien ke cua v,

    public Graph( int n) {
        this.n = n;
        Vertex = new char[n];
        adj = new int[n][n];
    }

    public void visit(int i) {
        System.out.print(Vertex[i] + " ");
    }

    public void depth(boolean visited[], int i) {
        visit(i);
        visited[i] = true;
        for (int k = 0; k < n; k++) {
            if (adj[i][k] > 0 && adj[i][k] < 999 && !visited[k]) {
                depth(visited, k);
            }
        }
    }

    public void depthFirst(int k) {
        boolean visited[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i);
            }
//            System.out.println("");
        }
    }

    public void breadth(boolean visited[], int v) throws Exception {
        Queue q = new Queue();
        q.enqueue(v);
        visited[v] = true;
        while (!q.isEmpty()) {
            int r = q.dequeue();
            visit(r);
            for (int i = 0; i < n; i++) {
                if (adj[r][i] > 0 && !visited[i]) {
                    q.enqueue(i);
                    visited[i] = true;
                }
            }
        }
    }

    public void breadth_full(int v) throws Exception {
        boolean visited[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

//       breadth (visited,v);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                breadth(visited, i);
            }
        }
        System.out.println("");
    }
//    

    public static void main(String[] args) throws InterruptedException, Exception {
        // TODO code application logic here
        int n = 8;
        Graph grap = new Graph(n);
        String s, s1 = null;
        Scanner scan;

        grap.Vertex = "ABCDEFGHIJKLMNOP".toCharArray();
        //A     B       C        D   E       F    G       H        I
        s1 = "0    999     999     999     8       2   15     999     14 "//A
                + "999   0      999     999     999    999   10     999     999 "//B
                + "999  999      0       999    999    999   999     2     999 "//C
                + "999  999     999      0      999     999   999     11     999 "//D
                + "8    999     999     999      0      3   999     999     5 "//E
                + "2   999      999     999     3       0   999     999     999 "//F
                + "15   10      999     999   999      999   0      999     999 "//G
                + "999  999     2       11      999     999   999     0       999 "//H
                + "14    999     999     999     5      999   999     999     0 ";//I

        //   A   B   C  D E  F  G  H  I
        String s2 = "0  0  0  0  1  1  1  0  1 "
                +//A
                "0  0  0  0  0  0  1  0  0 "
                +//B
                "0  0  0  0  0  0  0  1  0 "
                +//C adj[2][8] ==1
                "0  0  0  0  0  0  0  1  0 "
                +//D
                "1  0  0  0  0  1  0  0  1 "
                +//E
                "1  0  0  0  1  0  0  0  1 "
                +//F
                "1  1  0  0  0  0  0  0  0 "
                +//G
                "0  0  1  1  0  0  0  0  0 "
                +//H
                "1  0  0  0  1  1  0  0  0 ";  //I

//    grap.Vertex="123456".toCharArray();
        String s3 = "0 2 4 999 999 999 "
                + "2 0 1 7 999 999 "
                + "4 1 0 999 3 999 "
                + "999 7 999 0 2 1 "
                + "999 999 3 2 0 5 "
                + "999 999 999 1 5 0 ";
        // 0 1 2 3 4 5 6 7 
        String s4 = "0 1 1 1 0 0 0 0 "
                +//0
                "1 0 0 1 0 1 1 0 "
                +//1
                "1 0 0 0 1 0 1 0 "
                +//2
                "1 1 0 0 0 0 0 0 "
                +//3
                "0 0 1 0 0 0 0 1 "
                +//4
                "0 1 0 0 0 0 1 0 "
                +//5
                "0 1 1 0 0 1 0 0 "
                +//6
                "0 0 0 0 0 0 0 0 ";//7
        String s5 = "0 2 999 999 999 4 "
                + "2 0 7 999 999 1 "
                + "999 7 0 1 2 999 "
                + " 999 999 1 0 5 999  "
                + " 999 999 2 5 0 3 "
                + "4 1 999 999 3 0 ";
        String s6 = "0 5 999 7 3 1 "
                + "5 0 4 999 999 1 "
                + "999 4 0 2 999 1 "
                + "7 999 2 0 3 50 "
                + "3 999 999 3 0 999 "
                + "1 1 1 50 999 0 ";
        // 0  1   2   3   4   5   6   7
        String s7 = "0 999 999 999 1 999 999 999    "
                + "999 0 999 7 999 4 1 999   "
                + "999 999 0 999 3 999 999 999   "
                + "999 7 999 0 999 1 999 20   "
                + "1 999 3 999 0 999 999 7   "
                + "999 4 999 1 999 0 6 999   "
                + "999 1 999 999 999 6 0 9   "
                + "999 999 999 20 7 999 9 0    ";
        String s8 = "0 1 0 1 0 "
                + "1 0 1 0 1 "
                + "0 1 0 1 1 "
                + "1 0 1 0 1 "
                + "0 1 1 1 0 ";
        scan = new Scanner(s7);
        
//        System.out.println("s ="+s);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grap.adj[i][j] = scan.nextInt();// Integer.parseInt( scan.next());
            }
        }
        System.out.println("adjacency matrix");
        grap.display();
//        System.out.println("");
//        int from = 0, to = 3;
//        System.out.println("test breadth- first travels");
//        boolean visited[] = new boolean[n];
//        for (int i = 0; i < n; i++) {
//            visited[i] = false;
//        }
//        grap.breadth(visited, from);
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                grap.breadth(visited, i);
//            }
//        }
////        System.out.println("test depth-first");
////        grap.depthFirst(from);
//        System.out.println("");
        int from = 0, to = 3;
        System.out.println("shortest path from Vertex " + grap.Vertex[from] + " to Vertex " + grap.Vertex[to]);
        grap.dijkstra(from, to);
//        System.out.println("");
//        grap.floyd();
//        grap.MST();
//        grap.EulerPath();
//        System.out.println("");
//        ArrayList<Integer> lt = grap.Hamiton(1);
//        for (int i = 0; i < lt.size(); i++) {
//            System.out.print(lt.get(i) + " ");
//        }

    }

    public void dijkstra(int from, int to) {
        int distance[] = new int[n];
        boolean[] visited = new boolean[n];
        int b[][]=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j]=adj[i][j];
            }
        }
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] =adj[from][i];// Integer.MAX_VALUE;
            path[i] = from;
            visited[i] = false;
        }
        System.out.println("");
        distance[from] = 0;
        int sel = from;
        visited[sel] = true;
        for (int t = 0; t < n; t++) {

            for (int i = 0; i < n; i++) {
                //cac dinh lien ke cua from
                if (!visited[i] && (b[sel][i] + distance[sel] < distance[i])) {
                    distance[i] = b[sel][i] + distance[sel];
                    path[i] = sel;
                }

            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (distance[i] < min)) {
                    sel = i;
                    min = distance[i];
                }
            }
            visited[sel] = true;
        }

        int x = to;
         String s1="",s2="";      
        while (true) {
            s1=Vertex[x]+" "+s1;
            s2=distance[x]+" "+s2;
            if (x==from) break;
//            stack.push(x);
            x = path[x];
        }         
        System.out.println(s1+"\n"+s2);

    }

    public void input(int n) {
        this.n = n;
        Scanner scan;//= new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.print("ten dinh thu " + i + ": ");
            scan = new Scanner(System.in);
            String s = scan.nextLine();
            Vertex[i] = s.charAt(0);
        }
        System.out.println("Ma tran lien thuoc");
        for (int i = 0; i < n; i++) {
            System.out.print("Vertex " + Vertex[i] + " ");
            scan = new Scanner(System.in);
            String s = scan.nextLine();//.split("[ ]+");
            scan = new Scanner(s);
            for (int j = 0; j < n; j++) {
                adj[i][j] = scan.nextInt();// Integer.parseInt(s[j]);
            }
        }
    }

    public void display() {
        System.out.println("");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                System.out.print(adj[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void MST() {//prim jarnik
        boolean intree[] = new boolean[n];
        int d[] = new int[n];
        int path[] = new int[n];
        //khoi tao
        for (int i = 0; i < n; i++) {
            intree[i] = false;
            d[i] = 999;
            path[i] = -1;
        }
        d[1] = 0;
        for (int count = 0; count < n - 1; count++) {
            //tim min
            int min = 999, sel = -1;
            for (int i = 0; i < n; i++) {
                if (intree[i] == false && d[i] < min) {
                    min = d[i];
                    sel = i;
                }
            }
//            System.out.println("ind "+sel);
            intree[sel] = true;
            //tinh d[j] tai cac dinh lien ke cua ind
            for (int i = 0; i < n; i++) {
                if (!intree[i] && adj[sel][i] > 0 && adj[sel][i] < d[i]) {
                    d[i] = adj[sel][i];
                    path[i] = sel;
                }
            }

        }

        for (int i = 0; i < n; i++) {
            System.out.println(i + " " + path[i]);
        }

    }

    public void floyd() {
        int path[][] = new int[n][n];
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adj[i][j] > adj[i][k] + adj[k][j]) {
                        adj[i][j] = adj[i][k] + adj[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
        System.out.println("");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (path[i][j] != i && path[i][j] != j && adj[i][j] > 0) {
                    System.out.print(adj[i][j] + "(" + (path[i][j] + 1) + "),");
                } else {
                    System.out.print(adj[i][j] + ",");
                }
            }
            System.out.println("");
        }
    }

    public void EulerPath() {
        Stack s = new Stack();
        int path[] = new int[2 * n];
        boolean ispush[] = new boolean[n];
        int sel = 0;
        int k = 0;
        ispush[sel] = true;
        s.push(sel);
        path[k] = 0;
        int[][] b = new int[2 * n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = adj[i][j];
            }
        }

        while (!s.isEmpty()) {
            int tmp = (int) s.peek();

            int check = -1;
            for (int i = 0; i < n; i++) {
                if (b[tmp][i] > 0 && b[tmp][i] < 999) {
                    b[tmp][i] = 0;
                    b[i][tmp] = 0;
                    check = i;
                    break;
                }
            }
            if (check == -1) {//i la dinh co lap
                sel = (int) s.pop();
                path[++k] = sel;
            } else {
                s.push(check);
            }
        }

        for (int i = 0; i < k; i++) {
            System.out.print(Vertex[path[i]] + " ");
        }
//    else System.out.println("Not Euler path");
    }

    public boolean isHamitonCycle(ArrayList<Integer> lt) {
        if (lt.size() < n) {
            return false;
        }
        boolean check[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            check[i] = false;
        }
        for (int i = 0; i < lt.size(); i++) {
            check[lt.get(i)] = true;
        }
        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                return false;
            }
        }
        return true;
    }

    public int isolated(boolean[] inH, int i, boolean[] bad) {
        for (int j = 0; j < n; j++) {
            if (!bad[j] && adj[i][j] > 0 && !inH[j]) //                b[i][j]--;
            //                b[j][i]--;
            {
                return j;
            }
        }
        return -1;
    }

    public ArrayList<Integer> Hamiton(int v0) {
        ArrayList<Integer> list = new ArrayList<>();
        int b[][] = adj;
        list.add(v0);
        boolean bad[] = new boolean[n];
        boolean inH[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            bad[i] = false;
            inH[i] = false;
        }
  inH[v0] =true;
        while (true) {
           
            if (isHamitonCycle(list)) {
                return list;
            }
            int k = list.get(list.size() - 1);
             
            int nextV = isolated(inH, k, bad);
            if (nextV == -1) {//dinh co lap
                list.remove(k);
                bad[k] = true;
                inH[k]=false;
            } else {//co dinh lien ke thi dua no vao trong H
                list.add(nextV);
                for (int i = 0; i < n; i++) {
                    bad[i] = false;
                }
                inH[nextV] = true;
            }

//    System.out.println(isHamitonCycle(list));
        }
    }
    public void display(ArrayList<Integer> lt){
        System.out.println("");
        for (int i = 0; i < lt.size(); i++) {
            System.out.print (lt.get(i)+" ");
        }
        System.out.println("");
    }
}

class Queue {

    Node head, tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(int t) {
        if (head == null) {
            head = tail = new Node(t);
        } else {
            tail.next = new Node(t);
            tail = tail.next;
        }
    }

    public int dequeue() throws Exception {
        int x = 0;
        if (head != null) {
            x = head.val;
            head = head.next;
        } else {
            throw new Exception("Nothing to dequeue");
        }
        return x;
    }
}

class Node {

    int val;
    Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
        this.next = null;
    }

}
