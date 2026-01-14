package com.example.fileintegritychecker.model;


import com.example.fileintegritychecker.service.DecryptionCategory;
import com.example.fileintegritychecker.service.EncryptionCategory;

import java.security.Security;
import java.util.*;

public class AlgorithmProvider
{
    private static final EncryptionCategory category = null;

    /**
     * Return algorithm by category with constant param
     */

    public AlgorithmProvider(){}

    public static <T extends Enum<T>> List<String> getAlgorithmsByCategory(T category){
        if (category == null) return List.of();

        Set<String> algorithms = Security.getAlgorithms(category.toString());
        return new ArrayList<>(algorithms);
    }


    /**
     * @param enumType populates Enumeration classes
     * */
    public static <T extends Enum<T>> Map<T, List<String>> getAlgorithmsForEnum(Class<T> enumType) {
        Map<T, List<String>> result = new HashMap<>();

        for (T constant : enumType.getEnumConstants()) {
            List<String> algos = getAlgorithmsByCategory(constant);
            result.put(constant, algos);
            System.out.println("Loaded algorithms for " + constant + ": " + algos);
        }
        return result;
    }

    public static List<String> getAlgorithmsForEnumAsList() {
        List<String> enumsList = new ArrayList<>();
        for (Object constant : EncryptionCategory.values()) {
//            List<String> algos = getAlgorithmsByCategory((EncryptionCategory) constant);
            enumsList.add(constant.toString());
        }
        System.out.println("Loaded EncryptionCategory: " + enumsList);
        return enumsList;
    }

    static void main() {
        getAlgorithmsByCategory();
        getAlgorithmsForEnumAsList();
    }

    public static List<String> getAlgorithmsByCategory(){
//        if (category == null) return List.of();

        List<String> algorithmList = new ArrayList<>();
        for (Object categoryName : EncryptionCategory.values()){
            algorithmList.add(categoryName.toString());
        }
        System.out.println("Loaded Algorithm: " + algorithmList);
        return algorithmList;
    }

    public static String getAlgorithmAsString(){
        String string = "";

        return string;
    }


}
