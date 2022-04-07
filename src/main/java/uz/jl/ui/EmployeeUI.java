package uz.jl.ui;

import uz.jl.services.employee.EmployeeService;

/**
 * @author Elmurodov Javohir, Wed 12:11 PM. 12/8/2021
 */
public class EmployeeUI {
    public static void create() {
        EmployeeService.create();
    }

    public static void delete() {
        EmployeeService.delete();
    }

    public static void list() {
        EmployeeService.list();
    }

    public static void block() {
        EmployeeService.block();
    }

    public static void unBlock() {
        EmployeeService.unBlock();
    }

    public static void blockList() {
        EmployeeService.blockList();
    }
}
