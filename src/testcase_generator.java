import java.io.*;
import java.util.*;
public class testcase_generator {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("/home/saurabh/Desktop/input.txt"));
        char ch[] = new char[26];
        for (int i = 0; i < 26; i++) ch[i] = (char) ('a' + i);
        int lenPossible = 50000;
        int n =10000;//(int)(Math.random()*10000) + 2 ;
        int eachLength = lenPossible / n;

        bw.write(n+"\n");
        String ran[] = new String[n];
        for(int i=0;i<n;i++){
            ran[i]="";
            for(int j=0;j<eachLength;j++)
            {
                ran[i] += ch[(int)(Math.random()*26)];
            }
        }
        String out[] = new String[n];
        for(int i=0;i<n;i++){
            boolean flag = true;
            out[i]="";
            if(flag^=true){
                out[i]=ran[(int)(Math.random()*(n))].substring(0,(int)(Math.random()*eachLength));
            }
            else{
                for(int j=0;j<(Math.random()*eachLength);j++)
                    out[i]+= ch[(int)(Math.random()*26)];
            }
            bw.write(ran[i]+"\n");
        }

        int q = 100000;//(int)(Math.random()*100000)+ 1 ;
        bw.write(q + "\n");


        for(int i=0;i<q;i++){
            int l = (int)(Math.random()*n) + 1;
            int r = (int)(Math.random()*n) + 1;

////            if(i%2==0){
////                l=(i+1)%(n>>1);
////                if(l==0)l=1;
////                r = n;
////            }
////            else{
////                l=r=(i+1)%(n>>1);
////                if(l==0)l=r=1;
////            }
//            l=1;r=n;

            bw.write(Math.min(l,r)+" "+Math.max(l,r)+" "+out[(int)(Math.random()*n)] + "\n");
        }
        System.out.println(eachLength);
        bw.flush();
    }
}
