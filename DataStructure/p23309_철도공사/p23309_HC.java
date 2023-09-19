package DataStructure.p23309_철도공사;

import java.io.*;
import java.util.*;

public class p23309_HC {

    private static class ListNode {
        int i;
        ListNode prev, next;

        public ListNode(int i) {
            this.i = i;
            this.prev = null;
            this.next = null;
        }
    }

    private static ListNode head;
    private static ListNode tail;
    private static ListNode[] station = new ListNode[1000001];

    private static void initialize(int[] arr, int N) {
        head = new ListNode(arr[0]);
        tail = head;
        head.prev = tail;
        head.next = tail;
        station[arr[0]] = head;

        for (int i = 1; i < N; ++i) {
            insert(tail.prev, arr[i]);
        }
    }

    private static int BN(int i, int j) {
        ListNode node = station[i];
        int ret = node.next.i;
        insert(node, j);
        return ret;
    }

    private static int BP(int i, int j) {
        ListNode node = station[i];
        int ret = node.prev.i;
        insert(node.prev, j);
        return ret;
    }

    private static int CN(int i) {
        ListNode node = station[i];
        int ret = node.next.i;
        delete(node.next);
        return ret;
    }

    private static int CP(int i) {
        ListNode node = station[i];
        int ret = node.prev.i;
        delete(node.prev);
        return ret;
    }

    private static void insert(ListNode node, int j) {
        ListNode newNode = new ListNode(j);
        newNode.prev = node;
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
        station[j] = newNode;
    }

    private static void delete(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        station[node.i] = null;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        initialize(nums, N);

        String cmd;
        int i, num = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            i = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case "BN":
                    num = BN(i, Integer.parseInt(st.nextToken())); break;
                case "BP":
                    num = BP(i, Integer.parseInt(st.nextToken())); break;
                case "CN":
                    num = CN(i); break;
                case "CP":
                    num = CP(i); break;
                default:
                    throw new Exception();
            }
            bw.write(String.valueOf(num));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}