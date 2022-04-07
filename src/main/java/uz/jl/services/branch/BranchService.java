package uz.jl.services.branch;

import uz.jl.dao.atm.BaseDao;
import uz.jl.dao.branch.BranchDao;
import uz.jl.enums.atm.Status;
import uz.jl.models.branch.Branch;

import java.util.List;
import java.util.Objects;

public class BranchService {
    public static void show() {
        List<Branch> branches = BranchDao.list();
        int count = 1;
        boolean empty = true;
        for (Branch branch : branches) {
            if (branch.getDeleted() != 1) {
                if (branch.getStatus().equals(Status.ACTIVE)) {
                    System.out.println(count++ + "." + branch.getName());
                    empty = false;
                }
            }
        }
        if (empty) {
            System.out.println("you dont have branches bro!");
        }
    }

    public static void showBlocked() {
        List<Branch> branches = BranchDao.list();
        int count = 1;
        boolean empty = true;
        for (Branch branch : branches) {
            if (branch.getDeleted() != 1) {
                if (branch.getStatus().equals(Status.BLOCKED)) {
                    System.out.println(count++ + "." + branch.getName());
                    empty = false;
                }
            }
        }
        if (empty) {
            System.out.println("you dont have blocked branches!");
        }
    }

    public static void branchBlock(String name) {
        Branch branch = BranchDao.getByName(name);
        if (Objects.nonNull(branch)) {
            BranchDao.fileDelete(branch);
            branch.setStatus(Status.BLOCKED);
            BranchDao.create(branch);
        } else {
            System.out.println("branch not found bro!");
        }
    }

    public static void branchUnblock(String name) {
        Branch branch = BranchDao.getByName(name);
        if (Objects.nonNull(branch)) {
            BranchDao.fileDelete(branch);
            branch.setStatus(Status.ACTIVE);
            BranchDao.create(branch);
        } else {
            System.out.println("branch not found bro!");
        }
    }
}
