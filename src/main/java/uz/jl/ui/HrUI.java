package uz.jl.ui;

import uz.jl.dao.auth.AuthUserDao;
import uz.jl.dao.db.FRWAuthUser;
import uz.jl.enums.auth.Role;
import uz.jl.enums.auth.UserStatus;
import uz.jl.enums.http.HttpStatus;
import uz.jl.exceptions.APIException;
import uz.jl.models.auth.AuthUser;
import uz.jl.response.ResponseEntity;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import static uz.jl.utils.Color.GREEN;
import static uz.jl.utils.Color.RED;

/**
 * @author Elmurodov Javohir, Wed 12:10 PM. 12/8/2021
 */
public class HrUI {


    public static void create() {
        String name = Input.getStr("name : ");
        String password = Input.getStr("password : ");
        try {
            if (!AuthUserDao.getInstance().findByUserName(name).getUsername().equals(name)) {
                AuthUser user = AuthUser.childBuilder().username(name).password(password).role(Role.EMPLOYEE).childBuild();
                FRWAuthUser.getInstance().getAll().add(user);
                FRWAuthUser.getInstance().writeAll(user);
                new ResponseEntity<>("Success", HttpStatus.HTTP_200);
            }
        } catch (APIException e) {
            new ResponseEntity<>("This name is already exist ! ", HttpStatus.HTTP_404);
        }
    }

    public static void delete() {
        String name = Input.getStr("name");
        try {
            if (AuthUserDao.getInstance().findByUserName(name).getUsername().equals(name)
                    && AuthUserDao.getInstance().findByUserName(name).getRole().equals(Role.EMPLOYEE)) {
                AuthUserDao.getInstance().findByUserName(name).setDeleted(1);
                FRWAuthUser.getInstance().getAll().remove(AuthUserDao.getInstance().findByUserName(name));
                FRWAuthUser.getInstance().writeAll(AuthUserDao.getInstance().findByUserName(name));
                new ResponseEntity<>("Success", HttpStatus.HTTP_200);
            }
        } catch (APIException e) {
            new ResponseEntity<>("Wrong name ! ", HttpStatus.HTTP_404);
        }
    }

    public static void list() {
        unblockList();
        blockList();
    }

    public static void block() {
        unblockList();
        String username = Input.getStr("Username : ");
        try {
            AuthUser user = AuthUserDao.getInstance().findByUserName(username);
            if (user.getUsername().equals(username) &&
                    user.getRole().equals(Role.EMPLOYEE) && user.getDeleted() != 1) {
                user.setStatus(UserStatus.BLOCKED);
                new ResponseEntity<>("Success", HttpStatus.HTTP_200);

            }
        } catch (APIException e) {
            new ResponseEntity<>("User not found ! ", HttpStatus.HTTP_404);
        }
    }

    public static void unBlock() {
        blockList();
        String username = Input.getStr("Username : ");
        try {
            AuthUser user = AuthUserDao.getInstance().findByUserName(username);
            if (user.getUsername().equals(username) && user.getRole().equals(Role.EMPLOYEE) && user.getDeleted() != 1)
                user.setStatus(UserStatus.ACTIVE);
            new ResponseEntity<>("Success", HttpStatus.HTTP_200);
        } catch (APIException e) {
            new ResponseEntity<>("User not found ! ", HttpStatus.HTTP_404);
        }
    }

    public static void blockList() {
        for (AuthUser authUser : FRWAuthUser.getInstance().getAll()) {
            if (authUser.getStatus().equals(UserStatus.BLOCKED) && authUser.getRole().equals(Role.EMPLOYEE) && authUser.getDeleted() != 1) {
                Print.println(RED, "🔒+  " + authUser.getUsername());
            }
        }
    }

    public static void unblockList() {
        for (AuthUser authUser : FRWAuthUser.getInstance().getAll()) {
            if (authUser.getStatus().equals(UserStatus.ACTIVE) && authUser.getRole().equals(Role.EMPLOYEE) && authUser.getDeleted() != 1) {
                Print.println(GREEN, "🔓+  " + authUser.getUsername());
            }
        }
    }

}
