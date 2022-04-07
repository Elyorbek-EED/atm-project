package uz.jl.ui;

import uz.jl.dao.atm.ATMDao;
import uz.jl.dao.branch.BranchDao;
import uz.jl.dao.session.SessionBranch;
import uz.jl.enums.atm.ATMStatus;
import uz.jl.enums.atm.ATMType;
import uz.jl.enums.atm.CassetteStatus;
import uz.jl.exceptions.APIException;
import uz.jl.models.atm.ATMEntity;
import uz.jl.models.atm.Cassette;
import uz.jl.models.branch.Branch;
import uz.jl.services.atm.AtmService;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.Locale;
import java.util.Objects;

/**
 * @author Elmurodov Javohir, Wed 12:11 PM. 12/8/2021
 */
public class AtmUI {
    public static void create() {
        AtmService.checkBranchesAndCreate();
        String name = Input.getStr("name :");
        ATMType.listAtmType();
        String choice = Input.getStr("enter type :").toLowerCase(Locale.ROOT);
        ATMType type = AtmService.checkAtmType(choice);
        String cassette1 = Input.getStr("enter cassette1 value:");
        String count1 = Input.getStr("enter count or (default=100) :");
        String cassette2 = Input.getStr("enter cassette2 value:");
        String count2 = Input.getStr("enter count or (default=100) :");
        String cassette3 = Input.getStr("enter cassette3 value:");
        String count3 = Input.getStr("enter count or (default=100) :");
        String cassette4 = Input.getStr("enter cassette4 value:");
        String count4 = Input.getStr("enter count or (default=100) :");
        Cassette cassette_one = new Cassette(cassette1, CassetteStatus.ACTIVE, Integer.parseInt(count1));
        Cassette cassette_two = new Cassette(cassette2, CassetteStatus.ACTIVE, Integer.parseInt(count2));
        Cassette cassette_three = new Cassette(cassette3, CassetteStatus.ACTIVE, Integer.parseInt(count3));
        Cassette cassette_four = new Cassette(cassette4, CassetteStatus.ACTIVE, Integer.parseInt(count4));

        ATMEntity atm = new ATMEntity();
        atm.setBranchID(SessionBranch.getSession().getId());
        atm.setType(type);
        atm.setName(name);
        atm.setStatus(ATMStatus.ACTIVE);
        atm.setCassette1(cassette_one);
        atm.setCassette2(cassette_two);
        atm.setCassette3(cassette_three);
        atm.setCassette4(cassette_four);
        ATMDao.create(atm);
    }

    public static void update() {
        AtmService.checkBranchesAndCreate();
        list();
        String name = Input.getStr("enter update atm ?:");
        ATMEntity entity = ATMDao.getAtmByName(name);
        ATMDao.deleteFile(entity);
        if (Objects.nonNull(entity)) {
            Print.println("1. Update atm name");
            Print.println("2. Update atm type");
            Print.println("3. Update atm cassettes");
            Print.println("4. exit");
            String choice = Input.getStr("?:");
            switch (choice) {
                case "1":
                    String new_name = Input.getStr("enter atm new name: ");
                    entity.setName(name);
                    break;
                case "2":
                    ATMType.listAtmType();
                    String new_choice = Input.getStr("enter type :").toLowerCase(Locale.ROOT);
                    ATMType type = AtmService.checkAtmType(choice);
                    entity.setType(type);
                    break;
                case "3":
                    Print.println("1. cassette");
                    Print.println("2. cassette");
                    Print.println("3. cassette");
                    Print.println("4. cassette");
                    String cassette = Input.getStr("?:");
                    switch (cassette) {
                        case "1":
                            Print.println("1. Update name");
                            Print.println("2. Update count");
                            Print.println("3. Update status");
                            Print.println("4. exit");
                            String up_choice = Input.getStr("?:");
                            switch (up_choice) {
                                case "1":
                                    String val = Input.getStr("enter new val:");
                                    entity.getCassette1().setCurrencyValue(val);
                                    break;
                                case "2":
                                    String count = Input.getStr("enter new count:");
                                    entity.getCassette1().setCurrencyCount(Integer.parseInt(count));
                                    break;
                                case "3":
                                    if (entity.getStatus().equals(ATMStatus.ACTIVE)) {
                                        Print.println("BLOCKED");
                                    } else {
                                        Print.println("ACTIVE");
                                    }
                                    Print.println("3. exit");
                                    String status = Input.getStr("enter choice: ").toLowerCase(Locale.ROOT);
                                    switch (status) {
                                        case "blocked":
                                            entity.getCassette1().setStatus(CassetteStatus.BLOCKED);
                                            break;
                                        case "active":
                                            entity.getCassette1().setStatus(CassetteStatus.ACTIVE);
                                            break;
                                        case "3":
                                            try {
                                                MainMenu.run();
                                            } catch (APIException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        default:
                                            Print.println(Color.RED, "Wrong choice!");
                                    }
                                case "4":
                                    try {
                                        MainMenu.run();
                                    } catch (APIException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        case "2":
                            Print.println("1. Update name");
                            Print.println("2. Update count");
                            Print.println("3. Update status");
                            Print.println("4. exit");
                            String up2_choice = Input.getStr("?:");
                            switch (up2_choice) {
                                case "1":
                                    String val = Input.getStr("enter new val:");
                                    entity.getCassette2().setCurrencyValue(val);
                                    break;
                                case "2":
                                    String count = Input.getStr("enter new count:");
                                    entity.getCassette2().setCurrencyCount(Integer.parseInt(count));
                                    break;
                                case "3":
                                    if (entity.getStatus().equals(ATMStatus.ACTIVE)) {
                                        Print.println("BLOCKED");
                                    } else {
                                        Print.println("ACTIVE");
                                    }
                                    Print.println("3. exit");
                                    String status = Input.getStr("enter choice: ").toLowerCase(Locale.ROOT);
                                    switch (status) {
                                        case "blocked":
                                            entity.getCassette2().setStatus(CassetteStatus.BLOCKED);
                                            break;
                                        case "active":
                                            entity.getCassette2().setStatus(CassetteStatus.ACTIVE);
                                            break;
                                        case "3":
                                            try {
                                                MainMenu.run();
                                            } catch (APIException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        default:
                                            Print.println(Color.RED, "Wrong choice!");
                                    }
                                case "4":
                                    try {
                                        MainMenu.run();
                                    } catch (APIException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        case "3":
                            Print.println("1. Update name");
                            Print.println("2. Update count");
                            Print.println("3. Update status");
                            Print.println("4. exit");
                            String up3_choice = Input.getStr("?:");
                            switch (up3_choice) {
                                case "1":
                                    String val = Input.getStr("enter new val:");
                                    entity.getCassette3().setCurrencyValue(val);
                                    break;
                                case "2":
                                    String count = Input.getStr("enter new count:");
                                    entity.getCassette3().setCurrencyCount(Integer.parseInt(count));
                                    break;
                                case "3":
                                    if (entity.getStatus().equals(ATMStatus.ACTIVE)) {
                                        Print.println("BLOCKED");
                                    } else {
                                        Print.println("ACTIVE");
                                    }
                                    Print.println("3. exit");
                                    String status = Input.getStr("enter choice: ").toLowerCase(Locale.ROOT);
                                    switch (status) {
                                        case "blocked":
                                            entity.getCassette3().setStatus(CassetteStatus.BLOCKED);
                                            break;
                                        case "active":
                                            entity.getCassette3().setStatus(CassetteStatus.ACTIVE);
                                            break;
                                        case "3":
                                            try {
                                                MainMenu.run();
                                            } catch (APIException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        default:
                                            Print.println(Color.RED, "Wrong choice!");
                                    }
                                case "4":
                                    try {
                                        MainMenu.run();
                                    } catch (APIException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        case "4":
                            Print.println("1. Update name");
                            Print.println("2. Update count");
                            Print.println("3. Update status");
                            Print.println("4. exit");
                            String up4_choice = Input.getStr("?:");
                            switch (up4_choice) {
                                case "1":
                                    String val = Input.getStr("enter new val:");
                                    entity.getCassette4().setCurrencyValue(val);
                                    break;
                                case "2":
                                    String count = Input.getStr("enter new count:");
                                    entity.getCassette4().setCurrencyCount(Integer.parseInt(count));
                                    break;
                                case "3":
                                    if (entity.getStatus().equals(ATMStatus.ACTIVE)) {
                                        Print.println("BLOCKED");
                                    } else {
                                        Print.println("ACTIVE");
                                    }
                                    Print.println("3. exit");
                                    String status = Input.getStr("enter choice: ").toLowerCase(Locale.ROOT);
                                    switch (status) {
                                        case "blocked":
                                            entity.getCassette4().setStatus(CassetteStatus.BLOCKED);
                                            break;
                                        case "active":
                                            entity.getCassette4().setStatus(CassetteStatus.ACTIVE);
                                            break;
                                        case "3":
                                            try {
                                                MainMenu.run();
                                            } catch (APIException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        default:
                                            Print.println(Color.RED, "Wrong choice!");
                                    }
                                case "4":
                                    try {
                                        MainMenu.run();
                                    } catch (APIException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        default:
                            Print.println(Color.RED, "Wrong choice!");
                    }
                case "4":
                    try {
                        MainMenu.run();
                    } catch (APIException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    Print.println(Color.RED, "Wrong choice!");
                    update();
            }
            ATMDao.create(entity);
        }
    }

    public static void delete() {
        AtmService.checkBranchesAndCreate();
        list();
        String name = Input.getStr("enter delete atm ?:");
        ATMEntity entity = ATMDao.getAtmByName(name);
        if (Objects.nonNull(entity)) {
            ATMDao.delete(entity);
            System.out.println("successfully deleted");
        }
    }

    public static void list() {
        AtmService.checkBranchesAndCreate();
        AtmService.showAtmList(ATMDao.list());
    }

    public static void block() {
        AtmService.checkBranchesAndCreate();
        list();
        String name = Input.getStr("enter block atm ?:");
        ATMEntity entity = ATMDao.getAtmByName(name);
        ATMDao.deleteFile(entity);
        if (Objects.nonNull(entity)) {
            entity.setStatus(ATMStatus.BLOCKED);
            System.out.println("Successfully block atm");
            ATMDao.create(entity);
        }
    }

    public static void unblock() {
        AtmService.checkBranchesAndCreate();
        AtmService.showBlockAtmList(ATMDao.list());
        String name = Input.getStr("enter Unblock atm ?:");
        ATMEntity entity = ATMDao.getAtmByName(name);
        ATMDao.deleteFile(entity);
        if (Objects.nonNull(entity)) {
            entity.setStatus(ATMStatus.ACTIVE);
            System.out.println("Successfully unblock atm");
            ATMDao.create(entity);
        }
    }

    public static void blockList() {
        AtmService.checkBranchesAndCreate();
        AtmService.showBlockAtmList(ATMDao.list());
    }
}
