package com.example.fileintegritychecker.model;


import java.security.Security;
import java.util.*;

public class AlgorithmProvider
{


    /**
     * @param category from enum Type classes
     * @return Populating category as Enums in ArrayList
     * */

    /**
     * Return algorithm by category with constant param
     */
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

    public static String getAlgorithmAsString(){
        String string = "";

        return string;
    }


}
