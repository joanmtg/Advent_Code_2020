package adventcode.day4;

import adventcode.day2.Password;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static List<HashMap<String, String>> passports;


    public static void readFile(String filename){

        passports = new LinkedList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {

                HashMap<String, String> passport = new HashMap<>();

                while(line != null && !line.equals("")){
                    String[] info = line.split(" ");

                    for(String attribute: info){
                        String key = attribute.split(":")[0];
                        String value = attribute.split(":")[1];
                        passport.put(key, value);
                    }
                    line = reader.readLine();
                }
                passports.add(passport);

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void firstValidation(){
        readFile("/home/joan/dev/java/AdventCode/inputs/input_4_1.txt");

        int validPassports = 0;

        for(HashMap<String, String> passport: passports){
            boolean valid = passport.get("byr") != null &&
                    passport.get("iyr") != null &&
                    passport.get("eyr") != null &&
                    passport.get("hgt") != null &&
                    passport.get("hcl") != null &&
                    passport.get("ecl") != null &&
                    passport.get("pid") != null;

            if(valid){
                validPassports++;
            }
        }

        System.out.println("Valid passports: " + validPassports);
    }

    public static boolean validateYear(String year, int min, int max){
        try{
            boolean result = year != null && year.length() == 4 && Integer.parseInt(year) >= min && Integer.parseInt(year) <= max;
            return result;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean validateHeight(String height){

        if(height == null){
            return false;
        }

        try {
            if(height.endsWith("cm")){
                String numberStr = height.replace("cm", "");
                int number = Integer.parseInt(numberStr);

                return number >= 150 && number <= 193;
            }
            else if (height.endsWith("in")){
                String numberStr = height.replace("in", "");
                int number = Integer.parseInt(numberStr);

                return number >= 59 && number <= 76;

            }else{
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean validateHairColor(String hairColor){

        if(hairColor == null){
            return false;
        }

        Pattern pattern = Pattern.compile("#([0-9]|[abcdef]){6}");
        Matcher matcher = pattern.matcher(hairColor);

        boolean result = matcher.find();

        return result;
    }

    public static boolean validateEyeColor(String eyeColor){

        if(eyeColor == null){
            return false;
        }

        List<String> validColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

        boolean result = validColors.contains(eyeColor);

        return result;
    }

    public static boolean validatePassportId(String id){

        if(id == null){
            return false;
        }

        try{
            Integer.parseInt(id);
        }catch (NumberFormatException e){
            return false;
        }

        return id.length() == 9;

    }

    public static void secondValidation(){
        readFile("/home/joan/dev/java/AdventCode/inputs/input_4_2.txt");

        int validPassports = 0;

        for(HashMap<String, String> passport: passports){

            boolean valid = validateYear(passport.get("byr"), 1920, 2002) &&
                    validateYear(passport.get("iyr"), 2010, 2020) &&
                    validateYear(passport.get("eyr"), 2020, 2030) &&
                    validateHeight(passport.get("hgt")) &&
                    validateHairColor(passport.get("hcl")) &&
                    validateEyeColor(passport.get("ecl")) &&
                    validatePassportId(passport.get("pid"));

            if(valid){
                validPassports++;
            }
        }

        System.out.println("Valid passports: " + validPassports);
    }

    public static void main(String[] args) {
        firstValidation();
        secondValidation();
    }
}
