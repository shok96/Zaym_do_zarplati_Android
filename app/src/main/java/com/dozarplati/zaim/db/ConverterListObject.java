package com.dozarplati.zaim.db;

import androidx.room.TypeConverter;

import com.dozarplati.zaim.models.Cards;
import com.dozarplati.zaim.models.Cards_credit;
import com.dozarplati.zaim.models.Cards_debit;
import com.dozarplati.zaim.models.Cards_installment;
import com.dozarplati.zaim.models.Countries;
import com.dozarplati.zaim.models.Credits;
import com.dozarplati.zaim.models.Loans;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class ConverterListObject {

    public static class CardsOBJ {
        @TypeConverter
        public static List<Cards> stringToCards(String json) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards>>() {
            }.getType();
            List<Cards> measurements = gson.fromJson(json, type);
            return measurements;
        }

        @TypeConverter
        public static String CardsToString(List<Cards> list) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards>>() {
            }.getType();
            String json = gson.toJson(list, type);
            return json;
        }
    }

    public static class LoansOBJ {
        @TypeConverter
        public static List<Loans> stringToLoans(String json) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Loans>>() {
            }.getType();
            List<Loans> measurements = gson.fromJson(json, type);
            return measurements;
        }

        @TypeConverter
        public static String LoansToString(List<Loans> list) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Loans>>() {
            }.getType();
            String json = gson.toJson(list, type);
            return json;
        }
    }

    public static class CountriesOBJ {
        @TypeConverter
        public static List<Countries> stringToConutries(String json) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Countries>>() {
            }.getType();
            List<Countries> measurements = gson.fromJson(json, type);
            return measurements;
        }

        @TypeConverter
        public static String CountriessToString(List<Countries> list) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Countries>>() {
            }.getType();
            String json = gson.toJson(list, type);
            return json;
        }
    }

    public static class Cards_creditOBJ {
        @TypeConverter
        public static List<Cards_credit> stringToCards_credit(String json) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards_credit>>() {
            }.getType();
            List<Cards_credit> measurements = gson.fromJson(json, type);
            return measurements;
        }

        @TypeConverter
        public static String Cards_creditToString(List<Cards_credit> list) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards_credit>>() {
            }.getType();
            String json = gson.toJson(list, type);
            return json;
        }
    }

    public static class CreditsOBJ {
        @TypeConverter
        public static List<Credits> stringToCredits(String json) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Credits>>() {
            }.getType();
            List<Credits> measurements = gson.fromJson(json, type);
            return measurements;
        }

        @TypeConverter
        public static String CreditsToString(List<Credits> list) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Credits>>() {
            }.getType();
            String json = gson.toJson(list, type);
            return json;
        }
    }

    public static class Cards_installmentsOBJ {
        @TypeConverter
        public static List<Cards_installment> stringToCards_installment(String json) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards_installment>>() {
            }.getType();
            List<Cards_installment> measurements = gson.fromJson(json, type);
            return measurements;
        }

        @TypeConverter
        public static String Cards_installmentToString(List<Cards_installment> list) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards_installment>>() {
            }.getType();
            String json = gson.toJson(list, type);
            return json;
        }
    }

    public static class Cards_debitOBJ {
        @TypeConverter
        public static List<Cards_debit> stringToCards_debit(String json) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards_debit>>() {
            }.getType();
            List<Cards_debit> measurements = gson.fromJson(json, type);
            return measurements;
        }

        @TypeConverter
        public static String Cards_debitToString(List<Cards_debit> list) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cards_debit>>() {
            }.getType();
            String json = gson.toJson(list, type);
            return json;
        }
    }
}
