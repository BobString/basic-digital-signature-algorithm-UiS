package utils;

import java.math.BigInteger;



public class MillerRabin {
	
	 public static final BigInteger ZERO = BigInteger.ZERO;  // declaring constants
     public static final BigInteger ONE  = BigInteger.ONE;
     public static final BigInteger TWO = BigInteger.valueOf(2);
     public static final int[] aValues = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67};  // values for bases

     private static boolean testPr (BigInteger n, BigInteger a, int s, BigInteger d){
             for (int i=0; i<s; i++){
                     BigInteger exp = TWO.pow(i);
                     exp = exp.multiply(d);
                     BigInteger res = a.modPow(exp, n);
                     if (res.equals(n.subtract(ONE)) || res.equals(ONE)){
                             return true;
                     }
             }
             return false;
     }

     public static boolean millerRabin (BigInteger n, int numValues){ // n = number to test
            if(numValues == 0){
            	numValues = aValues.length;            	
            }
    	 	BigInteger d = n.subtract(ONE);                          // numValues = # of bases to test
             int s = 0;
             while (d.mod(TWO).equals(ZERO)){
                     s++;
                     d = d.divide(TWO);
             }
            // System.out.print("Base ");
             for (int i=0; i<numValues; i++){     // loops through the bases, terminating early if 
                     BigInteger a = BigInteger.valueOf(aValues[i]);  // composite
                     boolean r = testPr(n, a, s, d);
                     //System.out.print(aValues[i] + " ");
                     if (!r){
                             return false;
                     }
             }
             return true;
     }

}
