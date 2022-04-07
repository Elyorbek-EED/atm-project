package uz.jl.services.auth;

import uz.jl.models.auth.AuthUser;
import uz.jl.models.settings.Language;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

public class ProfileService {

    public static void editUsername(AuthUser user) {
        String name = Input.getStr("enter new name: ");
        if (!name.equalsIgnoreCase(user.getUsername()) && !name.isEmpty()) {
            user.setUsername(name);
        } else {
            Print.println(Color.RED, "Invalid!");
        }
    }

    public static void editPassword(AuthUser user) {
        String password = Input.getStr("enter old password: ");
        if (!password.equals(user.getPassword())) {
            String new_pwd = Input.getStr("enter new password: ");
            user.setPassword(new_pwd);
        } else {
            Print.println("old password different!");
        }
    }

    public static void editPhoneNumber(AuthUser user) {
        String phone = Input.getStr("enter new phone number: ");
        if (!phone.equals(user.getPhoneNumber())) {
            user.setPhoneNumber(phone);
        } else {
            Print.println("old phone different!");
        }
    }

    public static void editLanguage(AuthUser user) {
        Print.println("1. UZ");
        Print.println("2. EN");
        Print.println("3. RU");
        String lang = Input.getStr("?:");
        switch (lang) {
            case "1" -> user.setLanguage(Language.UZ);
            case "2" -> user.setLanguage(Language.EN);
            case "3" -> user.setLanguage(Language.RU);
            default -> {
                Print.println("wrong choice");
                editLanguage(user);
            }
        }
    }
}
