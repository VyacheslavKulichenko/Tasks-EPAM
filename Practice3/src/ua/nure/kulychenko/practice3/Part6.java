package ua.nure.kulychenko.practice3;
import java.util.regex.Pattern;

public class Part6 {

        public static String convert(String input){
            Pattern p = Pattern.compile("(\\b\\w+\\b)(?=[\\s\\S]*\\b\\1\\b[\\s\\S]*\\b\\1\\b)", Pattern.UNICODE_CHARACTER_CLASS);
            String res = p.matcher(input+" "+input).replaceAll("_$1");
            res = res.substring(0, res.length() - 1 - p.matcher(input).replaceAll("_$1").length());



            return res.toString();
        }

}
