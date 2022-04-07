package uz.jl.dao.session;

import com.google.gson.Gson;
import uz.jl.configs.AppConfig;
import uz.jl.models.branch.Branch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.stream.Collectors;

public class SessionBranch {
    public static void setSession(Branch branch) {
        try (FileWriter fileWriter = new FileWriter(AppConfig.get("db.sessionBranch.path")); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = new Gson().toJson(branch);
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteSession() {
        try (FileWriter fileWriter = new FileWriter(AppConfig.get("db.sessionBranch.path")); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = new Gson().toJson("");
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Branch getSession() {
        Branch branch = null;
        try (FileReader fileReader = new FileReader(AppConfig.get("db.sessionBranch.path")); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String jsonData = bufferedReader.lines().collect(Collectors.joining());
            branch = new Gson().fromJson(jsonData, Branch.class);
            return branch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return branch;
    }
}
