package uz.jl.dao.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.jl.configs.AppConfig;
import uz.jl.models.branch.Branch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FRWBranch extends FRWBase<Branch> {

    private static FRWBranch frwBranch;

    public static FRWBranch getInstance() {
        if (Objects.isNull(frwBranch)) {
            frwBranch = new FRWBranch();
        }
        return frwBranch;
    }

    public FRWBranch() {
        super(AppConfig.get("db.branches.path"));
    }

    @Override
    public List<Branch> getAll() {
        if (list.isEmpty()) {
            try (FileReader fileReader = new FileReader(path); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String jsonData = bufferedReader.lines().collect(Collectors.joining());
                list = new Gson().fromJson(jsonData, new TypeToken<List<Branch>>(){}.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void writeAll(List<Branch> dataList) {
        try (FileWriter fileWriter = new FileWriter(path, false); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = gson.toJson(dataList);
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
