package DataStructure.p1406_에디터;

import java.io.*;
import java.util.*;

public class p1406_HC {

    static class ListNode {
        char c;
        ListNode prev;
        ListNode next;

        public ListNode(char c) {
            this.c = c;
            this.prev = null;
            this.next = null;
        }
    }

    private static ListNode cursor;
    private static ListNode head;
    private static ListNode tail;

    private static void initialize(String str) {
        head = new ListNode('\u0000');
        tail = new ListNode('\u0000');
        head.next = tail;
        tail.prev = head;
        cursor = head;
        for (char c: str.toCharArray()) {
            cursor = insert(cursor, c);
        }
    }

    private static ListNode insert(ListNode node, char c) {
        ListNode newNode = new ListNode(c);
        newNode.next = node.next;
        newNode.prev = node;
        node.next.prev = newNode;
        node.next = newNode;
        return newNode;
    }

    private static void cursorLeft() {
        if (cursor != head) {
            cursor = cursor.prev;
        }
    }

    private static void cursorRight() {
        if (cursor != tail.prev) {
            cursor = cursor.next;
        }
    }

    private static ListNode delete(ListNode removeNode) {
        if (removeNode == head)
            return head;
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        return removeNode.prev;
    }

    private static String getString() {
        StringBuilder sb = new StringBuilder();
        for (ListNode p = head.next; p != tail; p = p.next) {
            sb.append(p.c);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str = br.readLine();
        int N = str.length();
        int M = Integer.parseInt(br.readLine());
        char cmd, c;
        initialize(str);
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken().charAt(0);
            switch (cmd) {
                case 'L':
                    cursorLeft(); break;
                case 'D':
                    cursorRight(); break;
                case 'B':
                    cursor = delete(cursor); break;
                case 'P':
                    c = st.nextToken().charAt(0);
                    cursor = insert(cursor, c);
                    break;
            }
        }
        bw.write(getString());

        bw.flush();
        bw.close();
        br.close();
    }
}