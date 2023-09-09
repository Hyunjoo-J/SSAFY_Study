package DataStructure.p5397_키로거;

import java.io.*;

public class p5397_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList ll = new LinkedList(1000001);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            char[] chars = br.readLine().toCharArray();
            for (char c: chars) {
                switch (c) {
                    case '<':
                        ll.back(); break;
                    case '>':
                        ll.advance(); break;
                    case '-':
                        ll.delete(); break;
                    default:
                        ll.insert(c); break;
                }
            }
            sb.append(ll.toString()).append("\n");
            ll.clear();
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static class LinkedList {
        ListNode head;
        ListNode tail;
        ListNode cursor;
        ListNode[] pool;
        int pIdx;

        LinkedList(int capacity) {
            head = new ListNode();
            tail = new ListNode();
            pool = new ListNode[capacity];
            for (int i = 0; i < capacity; ++i)
                pool[i] = new ListNode();
            clear();
        }

        void insert(char c) {
            ListNode node = pool[pIdx++];
            node.c = c;
            node.prev = cursor;
            node.next = cursor.next;
            cursor.next.prev = node;
            cursor.next = node;
            cursor = node;
        }

        void delete() {
            ListNode deleted = cursor;
            if (deleted == head)
                return;
            deleted.prev.next = deleted.next;
            deleted.next.prev = deleted.prev;
            cursor = cursor.prev;
        }

        void advance() {
            if (cursor != tail.prev)
                cursor = cursor.next;
        }

        void back() {
            if (cursor != head)
                cursor = cursor.prev;
        }

        void clear() {
            head.next = tail;
            tail.prev = head;
            cursor = head;
            pIdx = 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (ListNode p = head.next; p != tail; p = p.next)
                sb.append(p.c);
            return sb.toString();
        }
    }

    private static class ListNode {
        char c;
        ListNode prev, next;
    }
}
