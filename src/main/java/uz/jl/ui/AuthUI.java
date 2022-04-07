package uz.jl.ui;

import uz.jl.configs.Session;
import uz.jl.dao.auth.AuthUserDao;
import uz.jl.dao.session.SessionUser;
import uz.jl.exceptions.APIException;
import uz.jl.mapper.AuthUserMapper;
import uz.jl.response.ResponseEntity;
import uz.jl.services.auth.AuthService;
import uz.jl.utils.Input;

/**
 * @author Elmurodov Javohir, Wed 12:08 PM. 12/8/2021
 */
public class AuthUI extends BaseUI {
    static AuthService service = AuthService.getInstance(
            AuthUserDao.getInstance(),
            AuthUserMapper.getInstance());

    public static void login() {
        String username = Input.getStr("Username : ");
        String password = Input.getStr("Password : ");
        ResponseEntity<String> response = service.login(username, password);
        showResponse(response);
    }

    public static void register() {
    }

    public static void profile() {
        AuthService.profile();
    }

    public static void logout() {
        Session.deleteSessionUser();
        SessionUser.setSession();
        try {
            MainMenu.run();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}