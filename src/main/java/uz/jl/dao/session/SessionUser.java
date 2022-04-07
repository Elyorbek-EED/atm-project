package uz.jl.dao.session;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.jl.configs.AppConfig;
import uz.jl.models.auth.AuthUser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.stream.Collectors;

public class SessionUser {
    public static void setSession(AuthUser user) {
        try (FileWriter fileWriter = new FileWriter(AppConfig.get("db.sessionUser.path")); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = new Gson().toJson(user);
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setSession() {
        try (FileWriter fileWriter = new FileWriter(AppConfig.get("db.sessionUser.path")); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = "";
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AuthUser getSession() {
        AuthUser user = null;
        try (FileReader fileReader = new FileReader(AppConfig.get("db.sessionUser.path")); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String jsonData = bufferedReader.lines().collect(Collectors.joining());
            user = new Gson().fromJson(jsonData, AuthUser.class);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
