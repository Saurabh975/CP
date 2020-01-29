//                I know stuff but probably my rating tells otherwise...

//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class exFAC {

    static void MainSolution() {
        n = ni();
        long ar1[] = new long[n >> 1];
        long ar2[] = new long[n - (n >> 1)];
        for (int i = 0; i < n >> 1; i++) ar1[i] = nl();
        for (int i = 0; i < n - (n >> 1); i++) ar2[i] = ni();
        HashMap<Long, Long> f_pos = new HashMap<>(),
                f_neg = new HashMap<>(),
                s_pos = new HashMap<>(),
                s_neg = new HashMap<>();

        for (int i = 1; i < 1 << (n >> 1); i++) {
            long ans = 0;
            boolean flag = false;
            int c = 0;
            for (int j = 0; j < n >> 1; j++) {
                if (((i >> j) & 1) == 1) {
                    c++;
                    if (flag ^= true) ans += ar1[j];
                    else ans -= ar1[j];
                }
            }
            if (c % 2 == 0) {
                if (!f_neg.containsKey(ans)) f_neg.put(ans, 1L);
                else f_neg.put(ans, f_neg.get(ans) + 1);
            } else {
                if (!f_pos.containsKey(ans)) f_pos.put(ans, 1L);
                else f_pos.put(ans, f_pos.get(ans) + 1);
            }
        }

        for (int i = 1; i < 1 << (n - (n >> 1)); i++) {
            long ans = 0;
            boolean flag = false;
            for (int j = 0; j < n - (n >> 1); j++) {
                if (((i >> j) & 1) == 1) {
                    //pl(i+" "+j);
                    if (flag ^= true) ans += ar2[j];
                    else ans -= ar2[j];
                }
            }
            //if (ans == 0) pl(i);
            if (!s_pos.containsKey(ans)) s_pos.put(ans, 1L);
            else s_pos.put(ans, s_pos.get(ans) + 1);

            if (!s_neg.containsKey(-ans)) s_neg.put(-ans, 1L);
            else s_neg.put(-ans, s_neg.get(-ans) + 1);
        }

        t = ni();
        while (t-- > 0) {
            long ans = 0;
            long x = nl();

            if (f_pos.containsKey(x)) ans += f_pos.get(x);
            if (s_pos.containsKey(x)) ans += s_pos.get(x);
            if (f_neg.containsKey(x)) ans += f_neg.get(x);

            for (Map.Entry<Long, Long> i : f_pos.entrySet()) {
                if (s_neg.containsKey(x - i.getKey())) {
                    ans += (i.getValue() * s_neg.get(x - i.getKey()));
                }
            }

            for (Map.Entry<Long, Long> i : f_neg.entrySet()) {
                if (s_pos.containsKey(x - i.getKey())) {
                    ans += (i.getValue() * s_pos.get(x - i.getKey()));
                }
            }
            pl(ans);
        }
//        pl(f_pos);
//        pl(f_neg);
//        pl(s_pos);
//        pl(s_neg);
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

    static double nd() throws IOException {
        return Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
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
//            input = new AwesomeInput(System.in);
//            pw = new PrintWriter(System.out, true);
            input = new AwesomeInput(new FileInputStream("/home/saurabh/Desktop/input.txt"));
            pw = new PrintWriter(new BufferedWriter(new FileWriter("/home/saurabh/Desktop/output.txt")), true);

        }
        catch(Exception e){}

        new Thread(null, null, "Vengeance", 1 << 25)  //the last parameter is stack size desired.
        {
            public void run() {
                try {
                    double s = System.currentTimeMillis();
                    MainSolution();
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

