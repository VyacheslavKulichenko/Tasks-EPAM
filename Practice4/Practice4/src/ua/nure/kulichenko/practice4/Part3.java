package ua.nure.kulichenko.practice4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part3 {

    private Part3() {
    }

    public static final String ENCODING = "CP1251";
    private static String fileName = "part3.txt";

    private static final String REGEXP_FOR_INTEGER = "(^|\\s)(\\d+)(\\s|$)";

    private static final String REGEXP_FOR_DOUBLE = "(^|\\s)([\\d+]*\\.\\d*)(\\s|$)";
    private static final String REGEXP_FOR_CHAR = "(?i)(^|(?<=\\s))[\\u0410-\\u044Fa-zA-Z]($|(?=\\s))";
    private static final String REGEXP_FOR_STRING = "[\\u0410-\\u044Fa-zA-Z]{2,}";

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Part3.fileName = fileName;
    }

    public static void main(String[] args) {
        Part3.input();
    }

    public static void input() {
        Scanner sc = new Scanner(System.in, ENCODING);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            switch (str) {
                case "int":
                    System.out.println(integerValue());
                    continue;
                case "double":
                    System.out.println(doubleValue());
                    continue;
                case "char":
                    System.out.println(charValue());
                    continue;
                case "String":
                    System.out.println(stringValue());
                    continue;
                case "stop":
                    return;
                default:
                    throw new IllegalArgumentException(str + " is illegal");
            }
        }
    }

    public static String integerValue() {
        StringBuilder sb = new StringBuilder();
        Matcher m = getMatcher(REGEXP_FOR_INTEGER);
        while (m.find()) {
            sb.append(m.group(2) + " ");
        }
        return getSubstring(sb);
    }

    public static String doubleValue() {
        StringBuilder sb = new StringBuilder();
        Matcher m2 = getMatcher(REGEXP_FOR_DOUBLE);
        while (m2.find()) {
            sb.append(m2.group(2) + " ");
        }
        return getSubstring(sb);

    }

    public static String charValue() {
        StringBuilder sb = new StringBuilder();
        Matcher m3 = getMatcher(REGEXP_FOR_CHAR);
        while (m3.find()) {
            sb.append(m3.group() + " ");
        }
        return getSubstring(sb);

    }

    public static String stringValue() {
        StringBuilder sb = new StringBuilder();
        Matcher m4 = getMatcher(REGEXP_FOR_STRING);
        while (m4.find()) {
            sb.append(m4.group() + " ");
        }
        return getSubstring(sb);
    }

    private static Matcher getMatcher(String regex) {
        Pattern p2 = Pattern.compile(regex);
        return p2.matcher(getString().get(0));
    }

    private static String getSubstring(StringBuilder sb) {
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static List<String> getString() {
        try {
            return Files.readAllLines(new File(fileName).toPath(), Charset.forName(ENCODING));
        } catch (IOException e) {
            System.out.println(fileName + " read error");
        }
        return Collections.emptyList();
    }
}
