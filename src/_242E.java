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

public class _242E {

      static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
            n = ni();
            init();

            for (int i = 1; i <= n; i++) ar[i] = ni();

            build(1, 1, n);

            t = ni();
            while (t-- > 0) {
                  int i = ni(), l = ni(), r = ni();
                  if (i == 1) {
//                long ans = 0;
//                for (i = l; i <= r; i++) ans += ar[i];
                        //pl(ans);

                        pl(query(1, 1, n, l, r));
                  } else {
                        int k = ni();
//                for (i = l; i <= r; i++) ar[i] ^= k;
                        update(1, 1, n, l, r, k);
                  }
            }

      }

      static long seg[][];
      static int lazy[];
      static int ar[];

      static void init() {
            seg = new long[(n << 2) + 5][21];
            lazy = new int[(n << 2) + 5];
            ar = new int[n + 1];
      }

      static void build(int i, int l, int r) {
            if (l == r) {
                  //p(i);p(l);pl(ar[l]);
                  for (int b = 0; b < 21; b++) {
                        seg[i][b] = ar[l] & (1 << b);
                  }
                  return;
            }
            int mid = l + r >> 1;
            build(i << 1, l, mid);
            build(i << 1 | 1, mid + 1, r);

            for (int j = 0; j < 21; j++) {
                  seg[i][j] = seg[i << 1][j] + seg[i << 1 | 1][j];
            }
      }

      static void laziness(int i, int l, int r) {
            if (lazy[i] == 0) return;

            lazy[i << 1] ^= lazy[i];
            lazy[i << 1 | 1] ^= lazy[i];

            int mid = l + r >> 1;

            for (int b = 0; b < 21; b++) {

                  if ((lazy[i] & (1 << b)) != 0) {
                        seg[i << 1][b] = (1L << b) * (mid - l + 1) - seg[i << 1][b];
                        seg[i << 1 | 1][b] = (1L << b) * (r - (mid + 1) + 1) - seg[i << 1 | 1][b];
                  }
            }
            lazy[i] = 0;
      }

      static void update(int i, int l, int r, int ql, int qr, int val) {
            //p(ql);p(l);p(qr);p(r);pl(val);
            if (ql == l && qr == r) {
                  //p("HERE");p(ql);p(l);p(qr);p(r);pl(val);
                  lazy[i] ^= val;

                  for (int b = 0; b < 21; b++) {

                        if ((val & (1 << b)) != 0)
                              seg[i][b] = (1L << b) * (r - l + 1) - seg[i][b];

                  }
                  return;
            }

            laziness(i, l, r);

            int mid = l + r >> 1;

            if (qr <= mid) update(i << 1, l, mid, ql, qr, val);

            else if (ql > mid) update(i << 1 | 1, mid + 1, r, ql, qr, val);

            else {
                  update(i << 1, l, mid, ql, mid, val);
                  update(i << 1 | 1, mid + 1, r, mid + 1, qr, val);
            }

            for (int j = 0; j < 21; j++) {
                  seg[i][j] = seg[i << 1][j] + seg[i << 1 | 1][j];
            }

      }

      static long query(int i, int l, int r, int ql, int qr){
            //p(i);p(l);p(r);p(ql);pl(qr);
            if (ql == l && qr == r) {

                  long ans = 0;

                  for (int b = 0; b < 21; b++) {
                        ans += seg[i][b];
                  }
                  //pl("ANS IS: "+ans);
                  return ans;
            }

            laziness(i, l, r);

            int mid = l + r >> 1;

            if (qr <= mid) return query(i << 1, l, mid, ql, qr);

            else if (ql > mid) return query(i << 1 | 1, mid + 1, r, ql, qr);

            else {
                  return query(i << 1, l, mid, ql, mid) +
                          query(i << 1 | 1, mid + 1, r, mid + 1, qr);
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

      static long modpow(long a, long b, long m) {
            if (b == 0) return a % m;

            if ((b & 1) == 1) return a * modpow(a * a % m, b >> 1, m);
            return modpow(a * a % m, b >> 1, m);
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