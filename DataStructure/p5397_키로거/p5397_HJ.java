package DataStructure.p5397_키로거;

import java.io.*;
public class p5397_HJ {
    static class Node{
        char c;
        Node pre, next;
    }
    static class LinkedList{
        Node head;
        Node tail;
        Node now;
        Node[] key;
        int idx;

        LinkedList(int cur){
            head = new Node();
            tail = new Node();
            key = new Node[cur];
            for(int i = 0; i < cur; ++i){
                key[i] = new Node();
            }
            clear();
        }
        void clear(){
            head.next = tail;
            tail.pre = head;
            now = head;
            idx = 0;
        }

        void left(){
            if(now != head){
                now = now.pre;
            }
        }

        void right(){
            if(now.next != tail)
                now = now.next;
        }

        void back(){
            Node tmp = now;
            if(tmp == head)
                return;
            //중간 노드 삭제
            tmp.pre.next = tmp.next;
            tmp.next.pre = tmp.pre;
            now = tmp.pre;
        }
        void insert(char c){
            Node tmp = key[idx++];
            tmp.c = c;
            tmp.pre = now;
            tmp.next = now.next;
            now.next.pre = tmp;
            now.next = tmp;
            now = tmp;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(Node tmp = head.next; tmp != tail; tmp = tmp.next)
                sb.append(tmp.c);
            return sb.toString();
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        LinkedList list = new LinkedList(1000001);
        for(int i = 0; i < N; ++i){
            char[] ch = br.readLine().toCharArray();
            for(char c : ch){
                switch (c){
                    case '<':
                        list.left();
                        break;
                    case '>' :
                        list.right();
                        break;
                    case '-':
                        list.back();
                        break;
                    default:
                        list.insert(c);
                        break;
                }
            }
            sb.append(list.toString()+"\n");
            list.clear();
        }
        System.out.print(sb.toString());
    }
}