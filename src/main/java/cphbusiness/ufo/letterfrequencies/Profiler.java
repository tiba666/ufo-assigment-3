package cphbusiness.ufo.letterfrequencies;

public class Profiler {

    public static double Mark5() {
        int n = 10, count = 1, totalCount = 0;
        double dummy = 0.0, runningTime = 0.0;
        do {
            count *= 2;
            double st = 0.0, sst = 0.0;
            for (int j = 0; j < n; j++) {
                Main.Timer t = new Main.Timer();
                for (int i = 0; i < count; i++) //dummy += multiply(i);
                runningTime = t.check();
                double time = runningTime * 1e9 / count;
                st += time;
                sst += time * time;
                totalCount += count;
            }
            double mean = st / n, sdev = Math.sqrt((sst - mean * mean * n) / (n - 1));
            System.out.printf("%6.1f ns +/- %8.2f %10d%n", mean, sdev, count);
        } while (runningTime < 0.25 && count < Integer.MAX_VALUE / 2);
        return dummy / totalCount;
    }
}
