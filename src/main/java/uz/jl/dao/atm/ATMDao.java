package uz.jl.dao.atm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.jl.models.atm.ATMEntity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elmurodov Javohir, Mon 11:09 AM. 12/6/2021
 */
public class ATMDao extends BaseDao<ATMEntity> {
    private static final Type atmListType = new TypeToken<List<ATMEntity>>() {
    }.getType();

    public static void create(ATMEntity atm) {
        List<ATMEntity> entities = list();
        entities.add(atm);
        try (FileWriter fileWriter = new FileWriter("src/main/resources/db/atms.json"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = new Gson().toJson(entities);
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ATMEntity> list() {
        List<ATMEntity> entities = new ArrayList<>();
        try (FileReader reader = new FileReader("src/main/resources/db/atms.json");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String jsonData = bufferedReader.lines().collect(Collectors.joining());
            entities = new Gson().fromJson(jsonData, atmListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public static ATMEntity getAtmByName(String name) {
        List<ATMEntity> entities = list();
        for (ATMEntity entity : entities) {
            if (entity.getName().equalsIgnoreCase(name)) {
                return entity;
            }
        }
        return null;
    }

    public static void delete(ATMEntity entity) {
        List<ATMEntity> entities = list();
        for (ATMEntity atm : entities) {
            if (atm.getName().equals(entity.getName())) {
                atm.setDeleted(1);
            }
        }
        try (FileWriter fileWriter = new FileWriter("src/main/resources/db/atms.json"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = new Gson().toJson(entities);
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(ATMEntity entity) {
        List<ATMEntity> entities = list();
        entities.removeIf(atm -> atm.getName().equals(entity.getName()));
        try (FileWriter fileWriter = new FileWriter("src/main/resources/db/atms.json"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String jsonData = new Gson().toJson(entities);
            bufferedWriter.write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
