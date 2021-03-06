//                I know stuff but probably my rating tells otherwise...

//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class _1294D {

    static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
        long x0 = nl(), y0 = nl();
        long ax = nl(), ay = nl(), bx = nl(), by = nl();
        long x = nl(), y = nl(), t = nl();

        ArrayList<data> ar = new ArrayList<>();
        ar.add(new data(x0, y0));
        long a = x0 * ax + bx, b = y0 * ay + by;

        while (a > 0 && b > 0 && a <= (long) (1e18 + 1e16) && b <= (long) (1e18 + 1e16)) {
            if (abs(a - x0) + abs(b - y0) <= t || abs(a - x) + abs(y - b) <= t) {
                if (a >= ar.get(ar.size() - 1).a)
                    ar.add(new data(a, b));
            }
            x0 = a;
            y0 = b;
            a = x0 * ax + bx;
            b = y0 * ay + by;
        }

        long c = 0;
        for (int i = 0; i < ar.size(); i++) {
            long temoc = 0;
            long tempt = t;
            a = ar.get(i).a;
            b = ar.get(i).b;
            //pl(a+" "+b);
            if (abs(a - x) + abs(b - y) <= tempt) {
                tempt -= abs(a - x) + abs(b - y);
                //pl(tempt);
                temoc++;
                int j = i - 1;
                while (j >= 0 && tempt > 0) {
                    x0 = ar.get(j).a;
                    y0 = ar.get(j).b;
                    if (abs(a - x0) + abs(b - y0) <= tempt) {
                        tempt -= abs(a - x0) + abs(b - y0);
                        //pl(tempt);
                        temoc++;
                        a = x0;
                        b = y0;
                    } else break;
                    j--;
                }
                if (t > 0) {
                    j = i + 1;
                    while (j < ar.size() && tempt > 0) {
                        x0 = ar.get(j).a;
                        y0 = ar.get(j).b;
                        if (abs(a - x0) + abs(b - y0) <= tempt) {
                            tempt -= abs(a - x0) + abs(b - y0);
                            //pl(tempt);
                            temoc++;
                            a = x0;
                            b = y0;
                        } else break;
                        j++;
                    }
                }
                c = max(c, temoc);
            }
        }
        pl(c);
        //pl(ar);
    }

    static class data {
        long a, b;

        data(long a, long b) {
            this.a = a;
            this.b = b;
        }

        public String toString() {
            return "(" + a + " " + b + ")";
        }
    }


    //----------------------------------------The main code ends here------------------------------------------------------
    /*-------------------------------------------------------------------------------------------------------------------*/
    //-----------------------------------------Rest's all dust-------------------------------------------------------------


    static int mod9 = 1_000_000_007;
    static int n, m, l, k, t;
    //static AwesomeInput input = new AwesomeInput(System.in);
    static AwesomeInput input;
    static BufferedWriter bw;

    static PrintWriter pw ;

    static long power(long a, long b) {
        if (b == 0) return 1;
        if ((b & 1) == 1) return a * power(a * a, b >> 1);
        return power(a * a, b >> 1);
    }

    // The Awesome Input Code is a fast IO method //
    static class AwesomeInput {
        private InputStream letsDoIT;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        private AwesomeInput(InputStream incoming) {
            this.letsDoIT = incoming;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = letsDoIT.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        private long ForLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        private String ForString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }
    }

    // functions to take input//
    static int ni() {
        return (int) input.ForLong();
    }

    static String ns() {
        return input.ForString();
    }

    static long nl() {
        return input.ForLong();
    }

    //functions to give output
    static void pl() {
        pw.println();
    }

    static void p(Object o) {
        pw.print(o + " ");
    }

    static void pws(Object o) {
        pw.print(o + "");
    }

    static void pl(Object o) {
        pw.println(o);
    }

    // Fast Sort is Radix Sort
    public static int[] fastSort(int[] f) {
        int n = f.length;
        int[] to = new int[n];
        {
            int[] b = new int[65537];
            for (int i = 0; i < n; i++) b[1 + (f[i] & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < n; i++) to[b[f[i] & 0xffff]++] = f[i];
            int[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < n; i++) b[1 + (f[i] >>> 16)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < n; i++) to[b[f[i] >>> 16]++] = f[i];
            int[] d = f;
            f = to;
            to = d;
        }
        return f;
    }

    public static void main(String[] args) {      //threading has been used to increase the stack size.
        try{
            input = new AwesomeInput(System.in);
            pw = new PrintWriter(System.out, true);
//            input = new AwesomeInput(new FileInputStream("/home/saurabh/Desktop/input.txt"));
//            pw = new PrintWriter(new BufferedWriter(new FileWriter("/home/saurabh/Desktop/output.txt")), true);

        }
        catch(Exception e){}

        new Thread(null, null, "AApan_gand_hawai_dusar_ke_kare_dawai", 1 << 25)  //the last parameter is stack size desired.
        {
            public void run() {
                try {
                    double s = System.currentTimeMillis();
                    Mangni_ke_bail_ke_dant_na_dekhal_jye();
                    //System.out.println(("\nExecution Time : " + ((double) System.currentTimeMillis() - s) / 1000) + " s");
                    pw.flush();
                    pw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }
}

