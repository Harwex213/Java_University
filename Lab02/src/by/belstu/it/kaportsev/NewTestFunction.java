package by.belstu.it.kaportsev;

public class NewTestFunction {

    public int test = 10;

    public String getValue() {
        return "Oh, Hello there!";
    }

    public NewTestFunction() {
        this.test = 11;
    }

    public NewTestFunction(int test) {
        this.test = test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getTest() {
        if (test > 9)
            test = 11;
        while (test < 20)
            test++;
        return test;
    }
}
