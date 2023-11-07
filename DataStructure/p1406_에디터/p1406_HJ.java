package DataStructure.p1406_에디터;
import java.util.*;
import java.io.*;
public class p1406_HJ {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        String line = br.readLine();
        int len = line.length();
        for(int i = 0; i < len; ++i){
            stack1.push(line.charAt(i));
        }

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; ++i){
            line = br.readLine();
            switch (line.charAt(0)){
                case 'L':
                    if(!stack1.isEmpty())
                        stack2.push(stack1.pop());
                    break;
                case 'D':
                    if(!stack2.isEmpty())
                        stack1.push(stack2.pop());
                    break;
                case 'B' :
                    if(!stack1.isEmpty())
                        stack1.pop();
                    break;
                case 'P':
                    stack1.push(line.charAt(2));
                    break;
                default:
                    break;
            }

        }
        while(!stack1.isEmpty())
            stack2.push(stack1.pop());

        StringBuilder sb = new StringBuilder();
        while(!stack2.isEmpty())
            sb.append(stack2.pop());
        System.out.println(sb);
    }
}