package by.belstu.it.kaportsev.basejava;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import static java.lang.Math.*;

public class JavaTest {
    final int fInt = 0;
    public final int pfInt = 0;
    public static final int psfInt = 0;

    static int s_int;

    public static void main(String[] args) {
        System.out.println("Task B... \n");
        char charVar = 'a';
        int intVar = 1;
        short shortVar = 11;
        byte byteVar = 72;
        long longVar = 1234567654332L;
        boolean boolVar = false;

        var strInt = "Hello" + 43;
        var strChar = "Hello" + '!';
        var strDouble = "Hello" + 30.6;

        byte byteInt = (byte)(byteVar + intVar);
        int doubleLong = (int)(11.23 + longVar);

        long bigNumber = intVar + 2147483647L;
        System.out.println(bigNumber);
        System.out.println("Default value of static int is " + s_int);

        boolVar = boolVar && true;
        boolVar = boolVar ^ false;
        // bool_var = bool_var + false;

        long bigValue = 9223372036854775807L;
        long someValue = 0x7ffffffffffL;
        System.out.println();

        charVar = '\u0061';
        charVar = 97;
        charVar = 'a' + 97 + '\u0061';

        var divisionRemainder = 3.45 % 2.4;
        // intVar = 1/0;
        // intVar = 0/0;
        double logResult = log(-345);
        float positiveInfinity = Float.intBitsToFloat(0x7F800000);
        float negInfinity = Float.intBitsToFloat(0xFF800000);

        System.out.println("Task D... \n");
        System.out.println("PI: " + Math.PI);
        System.out.println("E: " + Math.E);
        System.out.println("Round PI: " + Math.round(Math.PI));
        System.out.println("Round E: " + Math.round(Math.E));
        System.out.println("Min PI & E: " + Math.min(Math.PI, Math.E));
        System.out.println("Math Random: " + Math.random());

        System.out.println("Task E... \n");
        Boolean newBool = true;
        Character newChar = 'c';
        Integer newInteger = 14;
        Byte newByte = 12;
        Long newLong = 124325325215L;
        Double newDouble = 3.14;

        System.out.println(newByte + newDouble);
        System.out.println(newByte - newInteger);
        System.out.println(newInteger >>> 2);
        System.out.println(newBool & !newBool);

        System.out.println("Long min:" + Long.MIN_VALUE);
        System.out.println("Long max:" + Long.MAX_VALUE);
        System.out.println("Float min:" + Float.MIN_VALUE);
        System.out.println("Float min:" + Float.MAX_VALUE);

        byte newByteVar = newByte;
        int newIntVar = newInteger/2;
        newIntVar = newInteger;

        Integer integer2 =13;
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.toHexString(12));
        System.out.println(Integer.compare(12,13));
        System.out.println(Integer.toString(34));
        System.out.println(Integer.bitCount(4));
        System.out.println(Float.isNaN(12.2f));

        System.out.println("Task F... \n");
        String strNumb = "2345";
        Integer number = new Integer(strNumb);
        Integer number2 = Integer.valueOf("2345");
        int number3 = Integer.parseInt("2345");

        byte[] byteArray = strNumb.getBytes();
        System.out.println(Arrays.toString(byteArray));
        String strNmb = new String(byteArray);
        System.out.println(strNmb);

        String bool = "True";

        boolean bl1 = Boolean.parseBoolean(bool);
        boolean bl2 = Boolean.valueOf(bool);

        String str1 = "Olezhik";
        String str2 = "Olezha";

        int x = 0;
        do {
            if(str1 == str2)
                System.out.println("true");
            else
                System.out.println("false");

            if(str1.equals(str2))
                System.out.println("true");
            else
                System.out.println("false");

            if(str1.compareTo(str2) == 0)
                System.out.println("true");
            else
                System.out.println("false");
            str2 = null;
            x++;
        } while (x < 1);

        String[] strArray = str1.split("r");
        for (String s:strArray)
            System.out.println(s);
        if (str1.contains("O"))
            System.out.println("true");
        System.out.println(str1.hashCode());
        System.out.println(str1.indexOf(2));
        System.out.println(str1.length());
        System.out.println(str1.replace('r','R'));

        System.out.println("Task G... \n");
        char[][] c1 ;
        char[] c2[] = new char[2][5];
        char[] c4[] = new char[2][5];
        char c3[][] = new char[3][2];

        c1 = new char[3][];
        c1[0] = new char[2];
        c1[1] = new char[3];
        c1[2] = new char[4];

        boolean comRez = c2 == c3;
        c2 = c3;


        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c1[i].length; j++) {
                c1[i][j] = 'a';
            }
        }

        for (var h:c1) {
            for(var g: h)
                System.out.print(g);
            System.out.println();
        }
    }
}