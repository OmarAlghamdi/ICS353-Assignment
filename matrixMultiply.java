import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver{
    public static void main(String[] args) {
        int t, n, b;
        String input = args[3], output = args[4];
        t = Integer.parseInt(args[0]);
        n = Integer.parseInt(args[1]);
        b = Integer.parseInt(args[2]);

        long[][] x, y, z;
        long[][][] m = read(input, n);
        x = m[0];
        y = m[1];
        long startTime = System.nanoTime();
        if(t == 0)
            z = Matrix.iterative(x, y, n);
        else
            z = Matrix.strassen(x, y, b, n);
        long endTime = System.nanoTime();

        write(output, z, n, convertTime(startTime, endTime));

    }
    public static long[][][] read(String input, int n){
        long[][][] arr = new long[2][n][n];
        Scanner in = null;
        try {
            in = new Scanner(new File(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[0][i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[1][i][j] = in.nextInt();
            }
        }
        in.close();
        return arr;
    }
    public static void write(String output, long[][] arr, int n, String time){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.println(time);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pw.print(arr[i][j] + " ");
            }
            pw.println("");
        }
        pw.close();
    }
    public static String convertTime(long startTime, long endTime){
        long time = endTime - startTime;
        long hours = TimeUnit.NANOSECONDS.toHours(time);
        long minutes = TimeUnit.NANOSECONDS.toMinutes(time);
        long seconds = TimeUnit.NANOSECONDS.toSeconds(time);
        long millis = TimeUnit.NANOSECONDS.toMillis(time);

        return hours + ":" + minutes + ":" + seconds + "." + millis;
    }
}