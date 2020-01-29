//                I know stuff but probably my rating tells otherwise...

//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class _1294F {
    static LinkedList<Integer> adj[];
    static int node1,node2,node3, level;
    static int vis[];
    static TreeSet<Integer> path = new TreeSet<>();
    static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
        n = ni();
        adj = new LinkedList[n + 1];
        vis = new int[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            int x = ni(), y = ni();
            adj[x].add(y);
            adj[y].add(x);
        }

        node1 = 1;
        level = 0;
        dfs(node1, level, 0);
        level = 0;
        vis = new int[n + 1];
        dfs(node1, level, 1);
        vis = new int[n + 1];
        path.add(node2);
        dfspath(node1);
        //pl(path);
        vis = new int[n + 1];
        //for(int i:path)vis[i]=1;
        level = 0;
        for (int i : path) {
            for (int j : adj[i])
                dfs1(j, 1);
        }
        pl(path.size() - 1 + level);
        if (node3 != 0)
            pl(node1 + " " + node2 + " " + node3);
        else {
            path.remove(node1);
            path.remove(node2);
            pl(node1 + " " + node2 + " " + path.first());
        }
    }

    static void dfs(int i, int lev, int y) {
        if (vis[i] == 1) return;
        vis[i] = 1;
        if (level < lev) {
            level = lev;
            if (y == 0)
                node1 = i;
            else node2 = i;
        }
        for (int x : adj[i]) dfs(x, lev + 1, y);
    }

    static boolean dfspath(int i) {
        if (i == node2) return true;
        boolean temp = false;
        if (vis[i] == 1) return false;
        vis[i] = 1;
        for (int x : adj[i]) temp |= dfspath(x);
        if (temp) path.add(i);
        return temp;
    }

    static void dfs1(int i, int lev) {
        if (vis[i] == 1) return;
        vis[i] = 1;
        if (path.contains(i)) return;
        if (lev > level) {
            node3 = i;
            level = lev;
        }
        for (int x : adj[i]) dfs1(x, lev + 1);
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

