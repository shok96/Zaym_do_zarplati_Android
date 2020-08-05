package com.dozarplati.zaim.db;

import android.os.Build;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterList {
        @TypeConverter
        public String fromList(List<String> list) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return list != null ? list.stream().collect(Collectors.joining("#")) : "";
            }
            else{
                return join(list, "#");
            }
        }

        @TypeConverter
        public List<String> toList(String data) {
            return Arrays.asList(data.split("#"));
        }


    static String join( List<String> list , String replacement  ) {
        StringBuilder b = new StringBuilder();
        try{for( String item: list ) {
            b.append( replacement ).append( item );
        }
        if(b.length()>0){
        return b.toString().substring( replacement.length() );}
        else return "";}
        catch (Exception e){
            return "";
        }
    }
}
