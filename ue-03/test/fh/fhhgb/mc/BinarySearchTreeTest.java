package fh.fhhgb.mc;

import at.fhhgb.mc.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        this.bst = new BinarySearchTree();
    }

    @Test
    public void testInsert() {
        var data = new int[]{28, 16, 34, 12, 19, 31, 49, 8, 15, 29};

        var data2 = new int[100000];

        for (int i = 0; i < data2.length; i++) {
            data2[i] = new Random().nextInt(2000000000);
        }

        for (int elem : data2) {
            bst.insert(elem);
        }

        System.out.println(bst.toString());

        for (int elem : data2) {
            bst.remove(elem);
        }

        System.out.println(bst.toString());
    }
}
