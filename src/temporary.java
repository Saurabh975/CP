//                I know stuff but probably my rating tells otherwise...

//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class temporary {
    static trie root = new trie();
    static int _k;

    static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
        k = ni();
        _k = (int) sqrt(k);
        String ar[] = new String[k];

        for (int i = 0; i < k; i++) {
            ar[i]=ns();
            add(ar[i].toCharArray());
        }
        t = ni();

        data[] inp = new data[t];
        for (int i = 0; i < t; i++) inp[i] = new data(ni() - 1, ni() - 1 , i, ns());

        Comparator<data> c = (data a, data b) -> a._l == b._l ? a.r - b.r : a._l - b._l;
        Arrays.sort(inp, c);
        int ans[] = new int[t];
        int l = inp[0].l, r = l-1;
        for (data i : inp) {
            //pl(i.l);
            while (r < i.r) {
                put(ar[++r].toCharArray());
            }
            while (l > i.l) {
                put(ar[--l].toCharArray());
            }
            while (l < i.l) {
                remove(ar[l++].toCharArray());
            }
            while (r > i.r) {
                remove(ar[r--].toCharArray());
            }
            ans[i.i] = find(i.s.toCharArray());
        }

        for(int x:ans)pl(x);

    }

    static class data {
        int l, r, _l, i;
        String s;

        data(int x, int y, int ind, String s) {
            l = x;
            r = y;
            i = ind;
            _l = l / _k;
            this.s = s;
        }
    }

    static void add(char ch[]) {
        trie temp = root;
        n = ch.length;
        for (int i = 0; i < n; i++) {
            //System.out.println(ch[i]+" "+(temp.map[ch[i] - 'a'] == null));
            if (temp.map[ch[i] - 'a'] == null) temp.map[ch[i] - 'a'] = new trie();
            temp = temp.map[ch[i] - 'a'];
        }
        //System.out.println();
    }

    static void remove(char ch[]) {
        trie temp = root;
        n = ch.length;
        for (int i = 0; i < n; i++) {
            if(temp.count.size()>1) {
                if (temp.count.get(n - i) > 1) temp.count.put(n - i, temp.count.get(n - i) - 1);
                else temp.count.remove(n - i);
            }

            temp = temp.map[ch[i] - 'a'];
        }
        if(temp.count.size()>1) {
            if (temp.count.get(0) > 1) temp.count.put(0, temp.count.get(0) - 1);
            else temp.count.remove(0);
        }
    }

    static void put(char ch[]) {
        trie temp = root;
        n = ch.length;
        for (int i = 0; i < n; i++) {
            if (!temp.count.containsKey(n - i)) temp.count.put(n - i, 0);
            temp.count.put(n - i, temp.count.get(n - i) + 1);
            temp = temp.map[ch[i] - 'a'];
        }
        if (!temp.count.containsKey(0)) temp.count.put(0, 0);
        temp.count.put(0, temp.count.get(0) + 1);
    }

    static int find(char ch[]) {
        int ans = Integer.MAX_VALUE >> 1;

        trie temp = root;
        int i;

        n = ch.length;
        for (i = 0; i < n; i++) {
//            for(int j=0;j<26;j++)
//                if(temp.map[j]!=null) {
//                    System.out.print((char)('a'+j));
//                }
//            System.out.println();
            ans = min(ans, temp.count.firstKey() + n - i);
            //pl(ans+ " " + temp.count);
            if (temp.map[ch[i] - 'a'] == null) break;
            temp = temp.map[ch[i] - 'a'];
        }
        ans = min(ans, temp.count.firstKey() + n - i);

        return ans;
    }

    static class trie {
        TreeMap<Integer, Integer> count = new TreeMap<>();

        trie[] map = new trie[27];
        trie() {
            count.put(Integer.MAX_VALUE >> 1,1);
        }

//        //public String toString(){
//            return "["+ count + " " + Arrays.toString(map)+" ]";
//        }

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
//            input = new AwesomeInput(System.in);
//            pw = new PrintWriter(System.out, true);
            input = new AwesomeInput(new FileInputStream("/home/saurabh/Desktop/input.txt"));
            pw = new PrintWriter(new BufferedWriter(new FileWriter("/home/saurabh/Desktop/output.txt")), true);

        }
        catch(Exception e){}

        new Thread(null, null, "AApan_gand_hawai_dusar_ke_kare_dawai", 1 << 25)  //the last parameter is stack size desired.
        {
            public void run() {
                try {
                    double s = System.currentTimeMillis();
                    Mangni_ke_bail_ke_dant_na_dekhal_jye();
                    System.out.println(("\nExecution Time : " + ((double) System.currentTimeMillis() - s) / 1000) + " s");
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

