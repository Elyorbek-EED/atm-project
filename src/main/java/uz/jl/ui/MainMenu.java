package uz.jl.ui;

import uz.jl.configs.AppConfig;
import uz.jl.exceptions.APIException;
import uz.jl.ui.menus.Menu;
import uz.jl.ui.menus.MenuKey;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

/**
 * @author Elmurodov Javohir, Wed 11:30 AM. 12/8/2021
 */
public class MainMenu {
    static {
        try {
            AppConfig.init();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
    public static void run() throws APIException {

        Menu.show();
        String choice = Input.getStr("?: ");
        MenuKey key = MenuKey.getByValue(choice);

        switch (key) {
            case LOGIN -> AuthUI.login();
            case PROFILE -> AuthUI.profile();
            case LOGOUT -> AuthUI.logout();

            case CREATE_ADMIN -> SuperAdminUI.getInstance().create();
            case DELETE_ADMIN -> SuperAdminUI.getInstance().delete();
            case LIST_ADMIN -> SuperAdminUI.getInstance().list();
            case BLOCK_ADMIN -> SuperAdminUI.getInstance().block();
            case UN_BLOCK_ADMIN -> SuperAdminUI.getInstance().unBlock();
            case BLOCK_LIST_ADMIN -> SuperAdminUI.getInstance().blockList();

            case CREATE_HR -> AdminUI.getInstance().create();
            case DELETE_HR -> AdminUI.getInstance().delete();
            case LIST_HR -> AdminUI.getInstance().list();
            case BLOCK_HR -> AdminUI.getInstance().block();
            case UN_BLOCK_HR -> AdminUI.getInstance().unBlock();
            case BLOCK_LIST_HR -> AdminUI.getInstance().blockList();

            case CREATE_EMPLOYEE -> HrUI.create();
            case DELETE_EMPLOYEE -> HrUI.delete();
            case LIST_EMPLOYEE -> HrUI.list();
            case BLOCK_EMPLOYEE -> HrUI.block();
            case UN_BLOCK_EMPLOYEE -> HrUI.unBlock();
            case BLOCK_LIST_EMPLOYEE -> HrUI.blockList();

            case CREATE_USER -> EmployeeUI.create();
            case DELETE_USER -> EmployeeUI.delete();
            case LIST_USER -> EmployeeUI.list();
            case BLOCK_USER -> EmployeeUI.block();
            case UN_BLOCK_USER -> EmployeeUI.unBlock();
            case BLOCK_LIST_USER -> EmployeeUI.blockList();

            case CREATE_BRANCH -> BranchUI.create();
            case UPDATE_BRANCH -> BranchUI.update();
            case DELETE_BRANCH -> BranchUI.delete();
            case LIST_BRANCH -> BranchUI.list();
            case BLOCK_BRANCH -> BranchUI.block();
            case UN_BLOCK_BRANCH -> BranchUI.unblock();
            case BLOCK_LIST_BRANCH -> BranchUI.blockList();

            case CREATE_ATM -> AtmUI.create();
            case UPDATE_ATM -> AtmUI.update();
            case DELETE_ATM -> AtmUI.delete();
            case LIST_ATM -> AtmUI.list();
            case BLOCK_ATM -> AtmUI.block();
            case UN_BLOCK_ATM -> AtmUI.unblock();
            case BLOCK_LIST_ATM -> AtmUI.blockList();

            case EXIT -> {
                Print.println(Color.YELLOW, "Good bye");
                return;
            }
            default -> // TODO: 12/8/2021 do translations here
                    Print.println(Color.RED, "Wrong Choice");
        }
        run();
    }
}
