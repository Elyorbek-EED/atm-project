package uz.jl.services.atm;

import uz.jl.dao.atm.ATMDao;
import uz.jl.dao.branch.BranchDao;
import uz.jl.dao.session.SessionBranch;
import uz.jl.enums.atm.ATMStatus;
import uz.jl.enums.atm.ATMType;
import uz.jl.exceptions.APIException;
import uz.jl.mapper.ATMMapper;
import uz.jl.models.atm.ATMEntity;
import uz.jl.models.branch.Branch;
import uz.jl.services.BaseAbstractService;
import uz.jl.services.branch.BranchService;
import uz.jl.ui.AtmUI;
import uz.jl.ui.MainMenu;
import uz.jl.ui.menus.Menu;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;

/**
 * @author Elmurodov Javohir, Mon 10:46 AM. 12/6/2021
 */
public class AtmService extends BaseAbstractService<ATMEntity, ATMDao, ATMMapper> {

    public AtmService(ATMDao repository, ATMMapper mapper) {
        super(repository, mapper);
    }

    public static ATMType checkAtmType(String type) {
        for (ATMType value : ATMType.values()) {
            if (value.toString().equalsIgnoreCase(type)) {
                return value;
            }
        }
        return null;
    }

    public static void showAtmList(List<ATMEntity> entities) {
        if (entities.isEmpty()) {
            System.out.println("you dont have atm");
            Menu.show();
        } else {
            boolean empty = true;
            for (ATMEntity entity : entities) {
                if (entity.getBranchID().equals(SessionBranch.getSession().getId())) {
                    if (entity.getDeleted() != 1) {
                        if (!entity.getStatus().equals(ATMStatus.BLOCKED)) {
                            Print.println(Color.YELLOW, entity.getName());
                            empty = false;
                        }
                    }
                }
            }
            if (empty) {
                Print.println(Color.RED, "This branch dont have atms!");
            }
        }
    }

    public static void showBlockAtmList(List<ATMEntity> entities) {
        if (entities.isEmpty()) {
            System.out.println("you dont have atm");
            Menu.show();
        } else {
            boolean empty = true;
            for (ATMEntity entity : entities) {
                if (entity.getBranchID().equals(SessionBranch.getSession().getId())) {
                    if (entity.getDeleted() != 1) {
                        if (entity.getStatus().equals(ATMStatus.BLOCKED)) {
                            Print.println(Color.YELLOW, entity.getName());
                            empty = false;
                        }
                    }
                }
            }
            if (empty) {
                Print.println(Color.RED, "this atm dont have blocked atms!");
            }
        }
    }

    public static void checkBranchesAndCreate() {
        if (BranchDao.getActiveBranches().isEmpty()) {
            System.out.println("you dont have branches bro!");
            try {
                MainMenu.run();
            } catch (APIException e) {
                e.printStackTrace();
            }
        } else {
            BranchService.show();
            String name = Input.getStr("enter branch name: ");
            Branch branch = BranchDao.getByName(name);
            SessionBranch.setSession(branch);
        }
    }
}
