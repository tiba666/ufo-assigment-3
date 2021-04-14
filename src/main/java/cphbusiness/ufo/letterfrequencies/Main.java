package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Timer t = new Timer();
//118313
        int numberOfTimeInLoop = 100;
        double[] runTimes = new double[numberOfTimeInLoop];
        String fileName = "C:\\Users\\tiba666\\Desktop\\ufo assigment 3\\ufo-assigment-3\\src\\main\\resources\\FoundationSeries.txt";

        Map<Integer, Long> freq = new HashMap<>();
        for (int i = 0; i < numberOfTimeInLoop; i++) {
            var reader = new FileReader(fileName);
            t.play();
            tallyChars(reader, freq);
            runTimes[i] =  t.checkInSec();
        }
        //print_tally(freq);
        for (var e : runTimes) {
            System.out.println("Run  : " + (e * 1_000 + " ms"));
        }
        System.out.println("Average: " + (Arrays.stream(runTimes).sum() / runTimes.length) * 1_000 + " ms");

        double[] runTimes2 = new double[numberOfTimeInLoop];
        String fileName2 = "C:\\Users\\tiba666\\Desktop\\ufo assigment 3\\ufo-assigment-3\\src\\main\\resources\\FoundationSeries.txt";
        Map<Integer, Long> freq2 = new HashMap<>();
        for (int i = 0; i < numberOfTimeInLoop; i++) {
            var reader2 = new FileReader(fileName2);
            t.play();
            tallyChars2(reader2, freq2);
            runTimes2[i] =  t.checkInSec();
        }
        //print_tally(freq2);
        for (var e : runTimes2) {
            System.out.println("Run  : " + (e * 1_000 + " ms"));
        }
        System.out.println("Average optimized: " + (Arrays.stream(runTimes2).sum() / runTimes2.length) * 1_000 + " ms");

        System.out.println("Average unoptimized: " + (Arrays.stream(runTimes).sum() / runTimes.length) * 1_000 + " ms");
        //print_tally(freq2);

    }

    private static void tallyChars(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {

            try {
                freq.put(b, freq.get(b) + 1);
            } catch (NullPointerException np) {
                freq.put(b, 1L);
            }

        }
    }

    private static void tallyChars2(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        String temp ;
        BufferedReader breader = new BufferedReader(reader,10000);
        while ((temp = breader.readLine()) != null) {
            for (char c : temp.toCharArray()){
                 b = Character.getNumericValue(c);

                try {
                    freq.put(b, freq.get(b) + 1);
                } catch (NullPointerException np) {
                    freq.put(b, 1L);
                }
            }

        }
    }

    private static void print_tally(Map<Integer, Long> freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
        }
        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Character c : sorted.keySet()) {
            System.out.println("" + c + ": " + sorted.get(c));
            ;
        }
    }
}
