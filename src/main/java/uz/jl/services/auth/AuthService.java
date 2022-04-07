package uz.jl.services.auth;

import uz.jl.configs.Session;
import uz.jl.dao.auth.AuthUserDao;
import uz.jl.dao.session.SessionUser;
import uz.jl.enums.http.HttpStatus;
import uz.jl.exceptions.APIException;
import uz.jl.mapper.AuthUserMapper;
import uz.jl.models.auth.AuthUser;
import uz.jl.response.ResponseEntity;
import uz.jl.services.BaseAbstractService;
import uz.jl.services.IBaseCrudService;
import uz.jl.ui.AuthUI;
import uz.jl.ui.MainMenu;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;
import java.util.Objects;

/**
 * @author Elmurodov Javohir, Mon 10:46 AM. 12/6/2021
 */
public class AuthService
        extends BaseAbstractService<AuthUser, AuthUserDao, AuthUserMapper>
        implements IBaseCrudService<AuthUser> {

    private static AuthService service;

    public static AuthService getInstance(AuthUserDao repository, AuthUserMapper mapper) {
        if (Objects.isNull(service)) {
            service = new AuthService(repository, mapper);
        }
        return service;
    }

    protected AuthService(AuthUserDao repository, AuthUserMapper mapper) {
        super(repository, mapper);
    }

    public ResponseEntity<String> login(String username, String password) {
        try {
            AuthUser user = repository.findByUserName(username);
            if (Objects.isNull(user) || !user.getPassword().equals(password))
                return new ResponseEntity<>("Bad Credentials", HttpStatus.HTTP_400);
            Session.getInstance().setUser(user);
            SessionUser.setSession(user);
            return new ResponseEntity<>("success", HttpStatus.HTTP_200);
        } catch (APIException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.getStatusByCode(e.getCode()));
        }
    }

    public static void profile() {
        AuthUser user = SessionUser.getSession();
        Print.println("1. Edit Username");
        Print.println("2. Edit Password");
        Print.println("3. Edit Phone Number");
        Print.println("4. Edit Language");
        Print.println("5. Exit");
        String choice = Input.getStr("?:");
        switch (choice) {
            case "1":
                ProfileService.editUsername(user);
                break;
            case "2":
                ProfileService.editPassword(user);
                break;
            case "3":
                ProfileService.editPhoneNumber(user);
                break;
            case "4":
                ProfileService.editLanguage(user);
                break;
            case "5":
                try {
                    MainMenu.run();
                } catch (APIException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Print.println("Wrong choice bro!");
                profile();
        }
    }


    @Override
    public void create(AuthUser authUser) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public AuthUser get(String id) {
        return null;
    }

    @Override
    public List<AuthUser> getAll() {
        return null;
    }

    @Override
    public void update(String id, AuthUser authUser) {

    }
}
