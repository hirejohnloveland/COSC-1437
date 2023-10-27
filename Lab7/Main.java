package Lab7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(affirmative("True") == true);
        System.out.println(affirmative("true") == true);
        System.out.println(affirmative("yes") == true);
        System.out.println(affirmative("Yes") == true);
        System.out.println(affirmative("False") == false);
        System.out.println(conVolCon("fox") == true);
        System.out.println(conVolCon("Cat") == true);
        System.out.println(conVolCon("Leaf") == false);
        System.out.println(conVolCon("owl") == false);
        System.out.println(conVolCon("daddy") == true);
        System.out.println(checkPhoneFormat("(555) 444 3477") == true);
        System.out.println(checkPhoneFormat("(111) 111 1121") == true);
        System.out.println(checkPhoneFormat("111-111-1121") == false);
        System.out.println(twoNum("25") == true);
        System.out.println(twoNum("a2bc5a") == true);
        System.out.println(twoNum("a2b33c5a") == false);
        System.out.println(passwordValidate("12312&A") == true);
        System.out.println(passwordValidate("a2bc5a") == false);
        System.out.println(tripolator("hee") == false);
        System.out.println(tripolator("hee hee") == false);
        System.out.println(tripolator("hee hee hee") == true);
    }

    private static boolean affirmative(String input) {
        String expression = "(?i)true|yes";
        return matchIt(input, expression);
    }

    public static boolean conVolCon(String input) {
        String expression = "\\b[^aeiou][aeiou][^aeiou]";
        return matchIt(input, expression);
    }

    public static boolean checkPhoneFormat(String input) {
        String expression = "\\(\\d\\d\\d\\)\\s\\d\\d\\d\\s\\d\\d\\d\\d";
        return matchIt(input, expression);
    }

    public static boolean twoNum(String input) {
        String expression = "^[^\\d]*\\d[^\\d]*\\d[^\\d]*$";
        return matchIt(input, expression);
    }

    public static boolean passwordValidate(String input) {
        String expression = "^(?=.*\\d)(?=.*\\D)(?=.*[!@#$%^&*]).{4,}$";
        return matchIt(input, expression);
    }

    public static boolean tripolator(String input) {
        String expression = "\\b(\\w+)\\s+\\1\\b\\s+\\1\\b";
        return matchIt(input, expression);
    }

    public static boolean matchIt(String input, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}
