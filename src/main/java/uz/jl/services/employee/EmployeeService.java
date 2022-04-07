package uz.jl.services.employee;

import uz.jl.dao.auth.AuthUserDao;
import uz.jl.dao.db.FRWAuthUser;
import uz.jl.enums.auth.Role;
import uz.jl.enums.auth.UserStatus;
import uz.jl.enums.http.HttpStatus;
import uz.jl.models.auth.AuthUser;
import uz.jl.response.ResponseEntity;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;
import java.util.Objects;

public class EmployeeService {
    public static List<AuthUser> list = FRWAuthUser.getInstance().getAll();

    public static ResponseEntity<String> create() {
        String name = Input.getStr("enter name: ");
        String password = Input.getStr("enter password: ");
        if (!hasUser(name)) {
            AuthUser user = AuthUser.childBuilder().username(name).password(password).status(UserStatus.ACTIVE).role(Role.CLIENT).childBuild();
            list.add(user);
            FRWAuthUser.getInstance().writeAll(list);
            return new ResponseEntity<>("Success", HttpStatus.HTTP_200);
        } else {
            return new ResponseEntity<>("User already created!", HttpStatus.HTTP_404);
        }
    }

    public static ResponseEntity<String> delete() {
        list();
        String name = Input.getStr("enter delete username: ");
        for (AuthUser user : list) {
            if (user.getUsername().equals(name)) {
                list.remove(user);
                user.setDeleted(1);
                list.add(user);
                FRWAuthUser.getInstance().writeAll(list);
                return new ResponseEntity<>("Success", HttpStatus.HTTP_200);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.HTTP_404);
    }

    public static void list() {
        int count = 1;
        boolean empty = true;
        for (AuthUser user : list) {
            if (user.getDeleted() != 1) {
                if (user.getRole().equals(Role.CLIENT)) {
                    if (user.getStatus().equals(UserStatus.ACTIVE)) {
                        Print.println(count++ + ". " + user.getUsername());
                        empty = false;
                    }
                }
            }
        }
        if (empty) {
            Print.println("you dont have clients");
        }
    }

    public static void block() {
        list();
        String name = Input.getStr("enter block client name: ");
        AuthUser user = getByUsername(name);
        if (Objects.nonNull(user)) {
            list.remove(user);
            user.setStatus(UserStatus.BLOCKED);
            list.add(user);
            FRWAuthUser.getInstance().writeAll(list);
        } else {
            Print.println(Color.RED, "User not found bro!");
        }
    }

    public static void unBlock() {
        blockList();
        String name = Input.getStr("enter unblocked client name: ");
        AuthUser user = getByUsername(name);
        if (Objects.nonNull(user)) {
            list.remove(user);
            user.setStatus(UserStatus.ACTIVE);
            list.add(user);
            FRWAuthUser.getInstance().writeAll(list);
        } else {
            Print.println(Color.RED, "User not found bro!");
        }
    }

    public static void blockList() {
        int count = 1;
        boolean empty = true;
        for (AuthUser user : list) {
            if (user.getDeleted() != 1) {
                if (user.getRole().equals(Role.CLIENT)) {
                    if (user.getStatus().equals(UserStatus.BLOCKED)) {
                        Print.println(count++ + ". " + user.getUsername());
                        empty = false;
                    }
                }
            }
        }
        if (empty) {
            Print.println("you dont have blocked clients");
        }
    }

    private static AuthUser getByUsername(String name) {
        for (AuthUser user : list) {
            if (user.getUsername().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    private static boolean hasUser(String name) {
        for (AuthUser user : list) {
            if (user.getUsername().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
