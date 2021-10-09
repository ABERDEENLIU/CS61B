package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        // construct the list


        int tnumber = 1000;
        while (tnumber <= 128000) {
            SLList<Integer> temp = new SLList<>();
            for (int i = 0; i < tnumber; i++) {
                temp.addLast(i);
            }
            Stopwatch sw = new Stopwatch();
            int ops =0;
            int size = temp.size();
            for (int m=0; m<10000; m++) {
                temp.getLast();
                ops += 1; }
            double t = sw.elapsedTime();
            Ns.addLast(size);
            opCounts.addLast(ops);
            times.addLast(t);
            tnumber = tnumber * 2;
        } printTimingTable(Ns, times, opCounts);
    }


}
