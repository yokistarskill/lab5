package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        BigInteger num = in.nextBigInteger();
        in.close();
        System.out.print("We are starting encryption\n");
        keys key;
        key = generateKey(num);
        encryption(num, key);
        decryption(key);
        proverkaencryption(key);
        proverkadecryption(key);
    }

    private static keys encryption(BigInteger num, keys key) {
        BigInteger res = BigInteger.valueOf(num.intValue()).pow(key.getE().intValue()).mod(key.getN());
        System.out.print("Encrypted message:  " + res);
        key.setM(res);
        return key;
    }

    private static keys decryption (keys key){
        BigInteger res = (key.getM()).pow(key.getD().intValue()).mod(key.getN());
        System.out.print("Decrypted message:  " + res);
        key.setM(res);
        return key;
    }

    private static keys proverkaencryption(keys key) {
        BigInteger res = BigInteger.valueOf(key.getM().intValue()).pow(key.getD().intValue()).mod(key.getN());
        System.out.print("Signature:  " + res);
        key.setS(res);
        return key;
    }

    private static keys proverkadecryption(keys key) {
        BigInteger res = key.getS().pow(key.getE().intValue()).mod(key.getN());
        System.out.println("correct signature:  " + res);
        key.setM(res);
        return key;
    }

    private static keys generateKey(BigInteger num){
        BigInteger e, d, k;
        int p,q, n;
        do {
            p = rand(1234);
            do {
                q = rand(1234);
            } while (p == q);
        }while (BigInteger.valueOf(p * q).compareTo(num) == -1);
        k = BigInteger.valueOf((long) (p - 1) * (q-1));
        n = (p * q);
        do {
            e = BigInteger.valueOf(rand(n));
        }while (evklid(e.intValue(), k.intValue()) != 1);
        do {
            d = BigInteger.valueOf( (int) (Math.random() * (n) + 1));
        }while(!((e.multiply(d)).mod(k)).equals(1) || (e.equals(d)));
        keys key = new keys();
        key.setThree(e, BigInteger.valueOf(n), d);
        return key;
    }

    private static int evklid(int tmp, int m){
        long n;
        if (m < tmp) {
            n = m;
            m = tmp;
        }
        else {
            n = tmp;
        }

        long[] l1 = {m, 1, 0};
        long[] l2 = {n, 0, 1};
        long[] l3 = new long[3];

        while (l1[0] - l2[0] * ( l1[0] / l2[0] ) > 0) {
            for (int j = 0 ; j < 3 ; j++) l3[j] = l2[j];
            long q = l1[0] / l2[0];
            for (int i = 0; i < 3; i++) {
                l2[i] = (l1[i] - l2[i] * q);
            }

            for (int k = 0; k < 3; k++) l1[k] = l3[k];
        }

        int res = (int) l2[0];//gcd
        return res;
    }

    private static int rand(int n){
        int e;
        boolean Prime;
        do {
            Prime = true;
            e = (int) ((Math.random() * ( n - 1)) + 2);
            for (int i = 2; i <= e/2; i++) {
                if (e % i == 0) {
                    Prime = false;
                }
            }
        }while (!Prime);
        return e;
    }
}

class keys {
    BigInteger e, d;
    BigInteger m, n, s;

    public BigInteger getE() {
        return e;
    }
    public BigInteger getM() {
        return m;
    }
    public BigInteger getN() {
        return n;
    }
    public BigInteger getD() {
        return d;
    }
    public BigInteger getS() {
        return s;
    }
    public void setS(BigInteger s) {
        this.s = s;
    }
    public void setThree(BigInteger e, BigInteger n, BigInteger d) {
        this.e = e;
        this.n = n;
        this.d = d;
    }
    public void setM(BigInteger m) {
        this.m = m;
    }
}