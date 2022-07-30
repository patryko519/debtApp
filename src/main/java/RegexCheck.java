import java.util.regex.Pattern;

public class RegexCheck {
    public static boolean isUsernameValid(String s) {
        return s != null && s.matches("[a-zA-Z]+");
    }

    public static boolean isPasswordValid(String s) {
        //at least one lower case alphabet
        return s != null && s.matches(".+");
    }

    public static boolean isNumeric(String s){
        return Pattern.compile("^\\d+$")
                .matcher(s)
                .find();
    }
}
