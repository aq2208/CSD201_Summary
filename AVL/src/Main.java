
import java.io.IOException;

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
    

    public static void main(String[] args) throws IOException, InterruptedException {
        AVL avltree = new AVL();
        avltree.readDataFromFile("src\\data.txt");
        System.out.println("AVL Tree");
        avltree.breadth(avltree.root);
        System.out.println("");
        System.out.println("AVL Tree after remove 65  + 50 ");
        avltree.remove(65);avltree.remove(50);
        avltree.breadth(avltree.root);
    }
    
}
