package uz.jl;

import uz.jl.exceptions.APIException;
import uz.jl.ui.MainMenu;

/**
 * @author Elmurodov Javohir, Mon 11:47 AM. 11/29/2021
 */

public class App {
    static {
    }

    public static void main(String[] args) throws APIException {
        run();
    }

    private static void run() throws APIException {
        MainMenu.run();
    }
}
