//                I know stuff but probably my rating tells otherwise...

//                  It is strange,-but true; for truth is always strange;
//                      Stranger than fiction: if it could be told,
//                      How much would novels gain by the exchange!
//                      How differently the world would men behold!

//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class _templeq {

    static data seg[] = new data[400005];
    static long aalsi[] = new long[400005];
    static data ar[] = new data[100005];
    static int decoy[] = new int[100005];
    static int ans = 0;

    static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {

        n = ni();
        t = ni();

        for (int i = 0; i < 400005; i++) seg[i] = new data(0, 0);

        for (int i = 0; i < n; i++) {
            ar[i] = new data(nl(), i);
        }

        Arrays.sort(ar, 0, n, (data a, data b) -> a.a == b.a ? a.b - b.b : a.a < b.a ? -1 : 1);

        for (int i = 0; i < n; i++) decoy[ar[i].b] = i;

        build(1, 0, n - 1);

        while (t-- > 0) {
            ans = n;
            switch (ni()) {
                case 1:
                    int ind = ni() - 1;

                    data data = badhka_bsdk(1, 0, n - 1, decoy[ind]);
                    query(1, 0, n - 1, data.a + 1);

                {
                    int temp = decoy[ind];
                    decoy[ind] = decoy[ar[ans - 1].b];
                    decoy[ar[ans - 1].b] = temp;
                }

                {
                    int temp = ar[ans - 1].b;
                    ar[ans - 1].b = ar[data.b].b;
                    ar[data.b].b = temp;
                }

                update(1, 0, n - 1, ans - 1, ans - 1, 1);

                break;
                case 2:
                    query(1, 0, n - 1, ni());
                    pl(n - ans);
                    break;
                case 3:
                    query(1, 0, n - 1, ni());
                    //pl("here'"+ans);
                    update(1, 0, n - 1, ans, n - 1, -1);
                    break;
            }
        }

    }

    static void build(int i, int l, int r) {
        if (l == r) {
            seg[i] = new data(ar[l].a, l);
            return;
        }
        int mid = l + r >> 1;
        i <<= 1;
        build(i, l, mid);
        build(i | 1, mid + 1, r);
        seg[i >> 1] = new data(seg[i]);
    }

    static void aalaspan_dikhao(int i, int l, int r) {

        if (aalsi[i] != 0) {
            if (l != r) {
                int x = i << 1;
                aalsi[x] += aalsi[i];
                aalsi[x | 1] += aalsi[i];
            }
            seg[i].a += aalsi[i];
            aalsi[i] = 0;
        }
    }

    static void update(int i, int l, int r, int l_ind_arr, int r_ind_arr, long val) {

        aalaspan_dikhao(i, l, r);

        if (l > r_ind_arr || r < l_ind_arr  ) return;

        if (l >= l_ind_arr && r <= r_ind_arr) {
            aalsi[i] = val;
            aalaspan_dikhao(i, l, r);
            //if(l == r) ar[l].a = seg[i].a;
            return;
        }

        int mid = l + r >> 1;
        i <<= 1;
        update(i, l, mid, l_ind_arr, r_ind_arr, val);
        update(i | 1, mid + 1, r, l_ind_arr, r_ind_arr, val);
        seg[i >> 1] = new data(seg[i]);
    }

    static void query(int i, int l, int r, long val) {

        aalaspan_dikhao(i, l, r);

        if (l == r) {
            if (seg[i].a >= val) {
                ans = l;
            }
            return;
        }

        int mid = l + r >> 1;
        i <<= 1;
        aalaspan_dikhao(i, l, mid);
        aalaspan_dikhao(i | 1, mid + 1, r);

        if (seg[i | 1].a >= val) {
            ans = seg[i | 1].b;
            query(i, l, mid, val);
        } else query(i | 1, mid + 1, r, val);
    }

    static data badhka_bsdk(int i, int l, int r, int ind) {

        aalaspan_dikhao(i, l, r);

        if (l == r) return new data(seg[i].a, l);
        int mid = l + r >> 1;

        if (ind <= mid) return badhka_bsdk(i << 1, l, mid, ind);

        return badhka_bsdk((i << 1) | 1, mid + 1, r, ind);
    }

    static class data {
        long a;
        int b;

        data(long c, int d) {
            a = c;
            b = d;
        }

        data(data x) {
            a = x.a;
            b = x.b;
        }

        public String toString() {
            return a + "";
        }
    }


    //----------------------------------------The main code ends here------------------------------------------------------
    /*-------------------------------------------------------------------------------------------------------------------*/
    //-----------------------------------------Rest's all dust-------------------------------------------------------------


    static int mod9 = 1_000_000_007;
    static int n, m, l, k, t;
    static AwesomeInput input;
    static PrintWriter pw;

    static long power(long a, long b) {
        long x = max(a, b);
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
        try {
            input = new AwesomeInput(System.in);
            pw = new PrintWriter(System.out, true);
            input = new AwesomeInput(new FileInputStream("/home/saurabh/Desktop/input.txt"));
            pw = new PrintWriter(new BufferedWriter(new FileWriter("/home/saurabh/Desktop/output.txt")), true);

        } catch (Exception e) {
        }

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