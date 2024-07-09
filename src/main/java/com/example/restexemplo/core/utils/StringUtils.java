package com.example.restexemplo.core.utils;

public class StringUtils {

    public static String FormatPhone(String phone){
        return phone.replaceAll("(\\d{2})(\\d{1})(\\d{4})(\\d{4})", "($1) $2 $3-$4");
    }

    public static String cleanPhone(String phone){
        return phone.replaceAll("[^0-9]", "");
    }
    
}
