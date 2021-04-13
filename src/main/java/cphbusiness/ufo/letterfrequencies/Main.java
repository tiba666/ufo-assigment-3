package cphbusiness.ufo.letterfrequencies;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "C:\\Users\\Tiba\\Desktop\\ufo assigment 3\\letterfrequencies\\src\\main\\resources\\FoundationSeries.txt";
        Reader reader = new FileReader(fileName);
        Map<Integer, Long> freq = new HashMap<>();
        Map<Integer, Long> freq2 = new HashMap<>();
        Timer timer = new Timer();
        tallyCharsOptimazied(reader,freq);

       System.out.println(timer.check()+ " optimized");
        timer.play();
        tallyChars(reader, freq2);
        System.out.println(timer.check() + " default");
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
            if(freq.get(b) !=null){
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
            return (System.nanoTime() - start + spent) / 1e9;
        }
        public void pause() {
            spent += System.nanoTime() - start;
        }
        public void play() {
            start = System.nanoTime();
        }
    }


}
