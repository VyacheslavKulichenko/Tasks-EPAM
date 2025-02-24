package ua.nure.kulichenko.practice4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Part2 {

    private Part2() {
    }

    private static final String RAW_DATA = "part2.txt";

    private static final String SORTED_DATA = "part2_sorted.txt";

    public static final int N = 20;

    public static final int MAX = 50;

    static void fillFIle(String file) throws IOException {
        Integer[] arr = new Integer[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(MAX);
        }
        write(arr, file);
    }

    static Integer[] sortArray(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }

    private static void write(Integer[] arr, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : arr) {
                sb.append(i).append(' ');
            }
            byte[] buffer = sb.delete(sb.length() - 1, sb.length())
                    .toString().getBytes("CP1251");
            fos.write(buffer, 0, buffer.length);
        }
    }

    static Integer[] read(String fileName) throws IOException {
        FileInputStream inputStream = new FileInputStream(fileName);
        int data = inputStream.read();
        StringBuilder sb = new StringBuilder();
        while (data != -1) {
            sb.append((char) data);
            data = inputStream.read();
        }
        inputStream.close();
        String[] split = sb.toString().split(" ");
        List<Integer> ints = new ArrayList<>();
        for (String s : split) {
            ints.add(Integer.parseInt(s));
        }
        return ints.toArray(new Integer[ints.size()]);
    }

    public static void main(String[] args) throws IOException {
        Integer[] arr = read(RAW_DATA);

        System.out.print("input ==> ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        sortArray(arr);
        write(arr, SORTED_DATA);

        arr = read(SORTED_DATA);
        System.out.print("output ==> ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
