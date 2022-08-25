public class RegexCheck {
    public static boolean isUsernameValid(String s) {
        return s != null && s.matches("[a-zA-Z]+");
    }

    public static boolean isPasswordValid(String s) {
        //at least one lower case alphabet
        return s != null && s.matches(".+");
    }
}
