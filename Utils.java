package sample;

public class Utils {

    public static boolean isInteger(String strNum){
        if (strNum == null)
            return false;

        try {
            int n = Integer.parseInt(strNum);
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
