package uz.jl.ui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.jl.dao.auth.AuthUserDao;
import uz.jl.dao.db.FRWAuthUser;
import uz.jl.enums.auth.Role;
import uz.jl.enums.auth.UserStatus;
import uz.jl.enums.http.HttpStatus;
import uz.jl.exceptions.APIException;
import uz.jl.models.auth.AuthUser;
import uz.jl.response.ResponseEntity;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;
import java.util.Objects;

/**
 * @author Elmurodov Javohir, Wed 12:10 PM. 12/8/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuperAdminUI {
    private static SuperAdminUI superAdminUI;
    private final List<AuthUser> all = FRWAuthUser.getInstance().getAll();

    public static SuperAdminUI getInstance() {
        if (Objects.isNull(superAdminUI))
            superAdminUI = new SuperAdminUI();
        return superAdminUI;
    }

    public ResponseEntity<String> create() {
        String username = Input.getStr("Username: ");
        String password = Input.getStr("Password: ");

        if (!hasUser(username)) {
            AuthUser user = AuthUser.childBuilder().username(username).password(password).status(UserStatus.ACTIVE).role(Role.ADMIN).childBuild();
            all.add(user);
            return new ResponseEntity<>("Success", HttpStatus.HTTP_200);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.HTTP_404);
        }
    }

    public ResponseEntity<String> delete() throws APIException {
        list();
        String username = Input.getStr("Who is?\n->");
        try {
            AuthUser user = AuthUserDao.getInstance().findByUserName(username);
            user.setDeleted(-1);
            return new ResponseEntity<>("Success", HttpStatus.HTTP_200);
        } catch (APIException e) {
            return new ResponseEntity<>("User not found", HttpStatus.HTTP_404);
        }
    }

    public void list() {
        unBlockList();
        blockList();
    }

    public ResponseEntity<String> block() {
        unBlockList();
        String username = Input.getStr("Username: ");
        try {
            AuthUser user = AuthUserDao.getInstance().findByUserName(username);
            if(user.getRole().equals(Role.ADMIN) && user.getStatus().equals(UserStatus.ACTIVE)){
                user.setStatus(UserStatus.BLOCKED);
                return new ResponseEntity<>("Success", HttpStatus.HTTP_200);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.HTTP_404);
            }
        } catch (APIException e) {
            e.printStackTrace();
            return new ResponseEntity<>("User not found", HttpStatus.HTTP_404);
        }
    }

    public ResponseEntity<String> unBlock() {
        blockList();
        String username = Input.getStr("Username: ");
        try {
            AuthUser user = AuthUserDao.getInstance().findByUserName(username);
            if(user.getRole().equals(Role.ADMIN) && user.getStatus().equals(UserStatus.BLOCKED)){
                user.setStatus(UserStatus.ACTIVE);
                return new ResponseEntity<>("Success", HttpStatus.HTTP_200);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.HTTP_404);
            }
        } catch (APIException e) {
            e.printStackTrace();
            return new ResponseEntity<>("User not found", HttpStatus.HTTP_404);
        }
    }

    public void blockList() {
        if (all.isEmpty())
            Print.println("You have not user");
        for (AuthUser user : all) {
            if (user.getRole().equals(Role.ADMIN) && user.getStatus().equals(UserStatus.BLOCKED) && user.getDeleted() == 0) {
                Print.println(Color.RED, "♦️ " + user.getUsername());
            }
        }
    }

    public void unBlockList() {
        if (all.isEmpty())
            Print.println("You have not user");
        for (AuthUser user : all) {
            if (user.getRole().equals(Role.ADMIN) && user.getStatus().equals(UserStatus.ACTIVE) && user.getDeleted() == 0)
                Print.println(Color.GREEN, "♦️ " + user.getUsername());
        }
    }

    private boolean hasUser(String username) {
        for (AuthUser authUser : all) {
            if (authUser.getUsername().equalsIgnoreCase(username) && authUser.getDeleted() == 0)
                return true;
        }
        return false;
    }
}