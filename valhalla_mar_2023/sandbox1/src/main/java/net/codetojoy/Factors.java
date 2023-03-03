
package net.codetojoy;

import java.math.*;

primitive class Factors {
    static private final int MAX = 20;
    private final int[] factors = new int[MAX]; 

    int getExponent(int n, int i) {
        var result = 0;
        
        var chop = n;
        while (chop % i == 0) {
            chop = chop / i;
            result++;
        }

        return result;
    }

    Factors(int n) {
        var ceiling = (n/2) + 1;
        for (var i = 2; i < ceiling; i++) {
            if (isPrime(i)) {
                var f = n / i;
                if (f > 0) {
                    var exp = getExponent(n, i);
                    factors[i] = exp;
                }
            }
        }
    }

    boolean isPrime(int i) {
        return BigInteger.valueOf(i).isProbablePrime(1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        var total = 1;
        for (var i = 0; i < MAX; i++) {
            var f = factors[i];
            builder.append("f[" + i + "] = " + f + "\n");
            if (f > 0) {
                total *= Math.pow(i, f);
            }
        }
        builder.append("total: " + total + "\n");
        return builder.toString();
    } 
}
