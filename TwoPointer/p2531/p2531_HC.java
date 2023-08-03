package TwoPointer.p2531;

import java.io.*;
import java.util.*;

public class p2531_HC {

    private static final int CAPACITY = 30011;
    private static HashNode[] hashTable = new HashNode[CAPACITY];

    private static int hash(int n) {
        String str = String.valueOf(n);
        int hash = 5381;

        for (int i = 0; i < str.length(); i++) {
            int c = (int) str.charAt(i);
            hash = ((hash << 5) + hash) + c;
        }
        if (hash < 0)
            hash *= -1;
        return hash % CAPACITY;
    }

    private static void put(int key, int value) {
        int hash = hash(key);
        if (get(key) == -1) {
            hashTable[hash] = new HashNode(key, value, hashTable[hash]);
        } else {
            for (HashNode p = hashTable[hash]; p != null; p = p.next) {
                if (p.key == key) {
                    p.value = value;
                    return;
                }
            }
        }
    }

    private static int get(int key) {
        int hash = hash(key);
        for (HashNode p = hashTable[hash]; p != null; p = p.next) {
            if (p.key == key)
                return p.value;
        }
        return -1;
    }

    private static int getOrDefault(int key, int defaultValue) {
        if (!contains(key)) {
            put(key, defaultValue);
            return defaultValue;
        }
        return get(key);
    }

    private static boolean contains(int key) {
        return get(key) > 0;
    }

    private static int getKeySetSize() {
        int size = 0;
        for (int i = 0; i < CAPACITY; ++i) {
            for (HashNode p = hashTable[i]; p != null; p = p.next) {
                if (p.value > 0) {
                    ++size;
                }
            }
        }
        return size;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] A = new int[N + k + 1];
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i <= k; ++i) {
            A[N + i] = A[i];
        }

        int i = 0, j;
        for (j = 0; j < k; ++j) {
            put(A[j], getOrDefault(A[j], 0) + 1);
        }
        int answer = contains(c) ? getKeySetSize() : getKeySetSize() + 1;

        while (j < N + k) {
            put(A[i], get(A[i++]) - 1);
            put(A[j], getOrDefault(A[j++], 0) + 1);

            answer = Math.max(answer, contains(c) ? getKeySetSize() : getKeySetSize() + 1);
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static class HashNode {
        int key, value;
        HashNode next;

        public HashNode(int key, int value, HashNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
