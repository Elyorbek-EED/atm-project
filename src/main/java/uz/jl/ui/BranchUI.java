package uz.jl.ui;

import uz.jl.dao.branch.BranchDao;
import uz.jl.enums.atm.Status;
import uz.jl.models.branch.Branch;
import uz.jl.services.branch.BranchService;
import uz.jl.utils.Input;

import java.util.Objects;

/**
 * @author Elmurodov Javohir, Wed 12:11 PM. 12/8/2021
 */
public class BranchUI {
    public static void create() {
        String name = Input.getStr("enter branch name: ");
        Branch branch = new Branch();
        branch.setName(name);
        branch.setStatus(Status.ACTIVE);
        BranchDao.create(branch);
    }

    public static void update() {
        list();
        String name = Input.getStr("enter update branch name: ");
        Branch branch = BranchDao.getByName(name);
        if (Objects.nonNull(branch)) {
            BranchDao.fileDelete(branch);
            String new_name = Input.getStr("enter new name: ");
            branch.setName(new_name);
            BranchDao.create(branch);
        } else {
            System.out.println("Branch not found bro!");
        }
    }

    public static void delete() {
        list();
        String name = Input.getStr("enter delete branch: ");
        BranchDao.delete(name);
    }

    public static void list() {
        BranchService.show();
    }

    public static void block() {
        list();
        String name = Input.getStr("enter block branch: ");
        BranchService.branchBlock(name);
    }

    public static void unblock() {
        BranchService.showBlocked();
        String name = Input.getStr("enter unblock branch: ");
        BranchService.branchUnblock(name);
    }

    public static void blockList() {
        BranchService.showBlocked();
    }
}
