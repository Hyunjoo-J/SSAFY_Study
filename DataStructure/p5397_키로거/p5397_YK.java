package DataStructure.p5397_키로거;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class p5397_YK {
    static Node head;
    static class Node {
        char c;
        Node pre;
        Node next;

        public Node(char c, Node pre, Node next) {
            this.c = c;
            this.pre = pre;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; ++tc) {
            char[] input = br.readLine().toCharArray();
            int i = 0;
            while (input[i] == '<' || input[i] == '>' || input[i] == '-') {
                ++i;
            }

            head = new Node('h', null, null);
            head.next = new Node(input[i], head, null);
            Node now = head.next;

            for (++i; i < input.length; ++i) {
                switch (input[i]) {
                    case '<': // 커서 왼쪽 이동
                        if (now == head) break;
                        now = now.pre;
                        break;
                    case '>': // 커서 오른쪽 이동
                        if (now.next == null) break;
                        now = now.next;
                        break;
                    case '-': // 지우기
                        if (now == head) break;
                        now.pre.next = now.next;
                        if (now.next != null) now.next.pre = now.pre; // 마지막 글자가 아니라면
                        now = now.pre;
                        break;
                    default:
                        if (now == head) {
                            now = new Node(input[i], head, head.next);
                            if (head.next != null) head.next.pre = now;
                            head.next = now;
                            break;
                        }
                        Node node = new Node(input[i], now, now.next);
                        if (now.next != null) now.next.pre = node;
                        now.next = node;
                        now = now.next;
                        break;
                }
            }

            for (Node tmp = head.next; tmp != null; tmp = tmp.next) {
                bw.write(String.valueOf(tmp.c));
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
