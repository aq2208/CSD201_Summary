
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thangnv
 */
public class Main {

    static Scanner scan = new Scanner(System.in);

    public static int inputNum() {
        int sel;
        do {

            try {
                sel = scan.nextInt();
            } catch (Exception e) {
                System.err.println("invalid num");
                sel = -1;
                scan.nextLine();
            }
        } while (sel == -1);
        return sel;
    }

    public static void menu() {
        System.out.println("");
        System.out.println("------Menu--------                                    Tree");
        System.out.println("1. Pre order                                           44");
        System.out.println("2. InOrder                                         17/       \\88");
        System.out.println("3. PostOrder                                    12/         /65    \\97");
        System.out.println("4. Breadth- First                            8/    \\16     /54    /90  \\100");
        System.out.println("5. Remove by copy (max of left subtree)   7/  \\9 /15     /50  \\58");
        System.out.println("6. Remove by copy (Min of right subtree        /13     /48   /56  ");
        System.out.println("7. Remove by Merge (Left child)                  \\14  /46 ");
        System.out.println("8. Remove by Merge (Right child                         \\47");
        System.out.println("9. Height of a node");
        System.out.println("0. Exit");
        System.out.print("Enter a number (0-9):");
    }

    public static void main(String[] args) throws Exception {
        BSTree tree = new BSTree();
//        System.out.println("read data from file data.txt");
        tree.readDataFromFile("src\\data.txt");
        int x ;
        int sel = 0;
        do {
            menu();
            sel = inputNum();
            switch (sel) {
                case 1:
                    System.out.println("PreOrder traversal");
                    tree.preOrder(tree.root);
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("inOrder traversal");
                    tree.inOrder(tree.root);
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("postOrder traversal");
                    tree.postOrder(tree.root);
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Breadth- First traversal");
                    tree.Bread_First(tree.root);
                    System.out.println("");
                    break;
                case 5:
                    System.out.print("Enter a number to remove:");
                    x = inputNum();
                    System.out.println("Before remove x = " + x);
                    tree.Bread_First(tree.root);
                    System.out.println("");
                    tree.removebyCopyL(x);
                    System.out.println("After remove x = " + x);
                    tree.Bread_First(tree.root);
                    break;
                case 6:
                    System.out.print("Enter a number to remove:");
                    x = inputNum();
                    System.out.println("Before remove x = " + x);
                    tree.Bread_First(tree.root);
                    System.out.println("");
                    tree.removebyCopyR(x);
                    System.out.println("After remove x = " + x);
                    tree.Bread_First(tree.root);
                    break;

                case 7:
                       System.out.print("Enter a number to remove:");
                    x = inputNum();
                    System.out.println("Before remove x = " + x);
                    tree.Bread_First(tree.root);
                    System.out.println("");
                    tree.removeNodebyMergL(x);
                    System.out.println("After remove x = " + x);
                    tree.Bread_First(tree.root);
                    break;
                case 8:
                       System.out.print("Enter a number to remove:");
                    x = inputNum();
                    System.out.println("Before remove x = " + x);
                    tree.Bread_First(tree.root);
                    System.out.println("");
                    tree.removeNodebyMergR(x);
                    System.out.println("After remove x = " + x);
                    tree.Bread_First(tree.root);
                    break;
                case 9:
                    System.out.print("Enter a number to find height:");
                    x = inputNum();
                    System.out.println("heigt = "+tree.height(x));
                    break;
                case 10:
            }
        } while (sel != 0);
    }
}
