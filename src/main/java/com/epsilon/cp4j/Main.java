package com.epsilon.cp4j;

import com.epsilon.cp4j.segmenttree.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);

        int n = sc.nextInt(), m = sc.nextInt();
        Long[] arr = new Long[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextLong();

        SegmentTree<Long> segTree = new SegmentTree<>(arr, 0L, (A, B) -> A + B);

        while (m --> 0) {
            int t = sc.nextInt();
            if (t == 1) {
                int idx = sc.nextInt(), value = sc.nextInt();
                segTree.pointUpdate(idx, (long) value);
            }
            else {
                int l = sc.nextInt(), r = sc.nextInt();
                pw.println(segTree.rangeQuery(l, r - 1));
            }
        }

        pw.close();
    }

    static class FastScanner {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
