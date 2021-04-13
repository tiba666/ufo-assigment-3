package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main_new {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "src/main/resources/FoundationSeries.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        //Reader reader = new FileReader(fileName);
        Map<Integer, Long> freq = new HashMap<>();

        Timer timer = new Timer();
        Random r = new Random();
        int i = 0;
        List<Double> times = new ArrayList<>();
        while (i <= 100) {
            /*
            int number = r.nextInt() % 2;
            if (number==1) {

             */
            timer.play();
            tallyCharsOptimazied(reader, freq);
            double check = timer.check();
            System.out.println(check + " optimized");
            times.add(check);
                /*
            } else {
                timer.play();
                tallyChars(reader, freq);
                double check = timer.check();
                System.out.println(check + " default");
                times.add(check);
            }

                 */
            i++;
        }
        // print_tally(freq);

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


    private static void tallyCharsOptimazied(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            if (freq.get(b) != null) {
                freq.put(b, freq.get(b) + 1);
            } else {
                freq.put(b, 1L);
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

    public static class Timer {
        private long start, spent = 0;

        public Timer() {
            play();
        }

        public double check() {
            double value = System.nanoTime() - start + spent / 1e9;
            return new BigDecimal(String.valueOf(value)).intValue();
        }

        public void pause() {
            spent += System.nanoTime() - start;
        }

        public void play() {
            start = System.nanoTime();
        }
    }


}
