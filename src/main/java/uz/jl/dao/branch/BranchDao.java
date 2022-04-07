package uz.jl.dao.branch;

import uz.jl.dao.db.FRWBranch;
import uz.jl.enums.atm.Status;
import uz.jl.models.atm.ATMEntity;
import uz.jl.models.branch.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchDao {
    public static void create(Branch branch) {
        List<Branch> branches = list();
        branches.add(branch);
        FRWBranch.getInstance().writeAll(branches);
    }

    public static List<Branch> list() {
        return FRWBranch.getInstance().getAll();
    }

    public static void delete(String name) {
        List<Branch> branches = list();
        for (Branch branch : branches) {
            if (branch.getName().equalsIgnoreCase(name)) {
                branch.setDeleted(1);
                break;
            }
        }
        FRWBranch.getInstance().writeAll(branches);
    }

    public static void fileDelete(Branch branch) {
        List<Branch> branches = list();
        for (Branch branch1 : branches) {
            if (branch1.getName().equals(branch.getName())) {
                branches.remove(branch1);
                break;
            }
        }
        FRWBranch.getInstance().writeAll(branches);
    }

    public static Branch getByName(String name) {
        List<Branch> branches = list();
        for (Branch branch : branches) {
            if (branch.getName().equalsIgnoreCase(name)) {
                return branch;
            }
        }
        return null;
    }

    public static List<Branch> getActiveBranches() {
        List<Branch> branches = list();
        List<Branch> act_branches = new ArrayList<>();
        for (Branch branch : branches) {
            if (branch.getStatus().equals(Status.ACTIVE)) {
                act_branches.add(branch);
            }
        }
        return act_branches;
    }
}
