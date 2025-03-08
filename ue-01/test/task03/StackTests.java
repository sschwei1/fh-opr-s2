package task03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTests {
    private Stack s1;

    @BeforeEach
    public void setUp() {
        s1 = new Stack();
    }

    @Test
    public void test() {
        s1.push(1);
        s1.push(2);
        s1.push(3);

        Stack s2 = new Stack();
        s2.push(4);
        s2.push(5);

        s1.push(s2);
        System.out.println(s1);

        Stack s3 = s1.clone();
        System.out.println(s3.pop());
        System.out.println(s3);
    }
}
