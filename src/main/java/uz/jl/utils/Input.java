package uz.jl.utils;

import java.util.Scanner;

import static uz.jl.utils.Print.print;

/**
 * @author Elmurodov Javohir, Fri 5:13 PM. 11/5/2021
 */
public class Input {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static Integer getNum() {
        return getNum("");
    }

    public static Integer getNum(String str) {
        print(str);
        return SCANNER_NUM.nextInt();
    }

    public static String getStr() {
        return getStr("");
    }

    public static String getStr(String str) {
        print(str);
        return SCANNER_STR.nextLine();
    }
}
