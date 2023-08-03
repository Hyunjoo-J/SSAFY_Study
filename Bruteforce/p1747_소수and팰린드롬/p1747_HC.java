package Bruteforce.p1747_소수and팰린드롬;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p1747_HC {

    private static boolean isPrime(int num) {
        if (num == 2)
            return true;
        if ((num & 1) == 0)
            return false;

        int bound = (int) Math.sqrt(num) + 1;
        for (int i = 3; i <= bound; i += 2) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    private static boolean isPalindrome(int num) {
        String str = String.valueOf(num);
        int length = str.length();
        int length2 = length / 2;
        for (int i = 0; i < length2; ++i) {
            if (str.charAt(i) != str.charAt(length - i - 1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 1)
            N <<= 1;

        while (true) {
            if (isPalindrome(N) && isPrime(N)) {
                System.out.println(N);
                break;
            }
            ++N;
        }
        br.close();

    }

}