package utils;

import java.math.BigInteger;
import java.util.Random;

import sun.security.util.BitArray;

public class DSAUtils {

	/**
	 * @param Integer
	 *            i, bits of the prime
	 * @return BigInteger prime that pass the primality test
	 */
	public static BigInteger getPrime(Integer i) {
		BigInteger res;

		BigInteger aux = primeCandidate(i);
		boolean bol = primalityTest(aux);
		Integer count = 0;
		while (!bol) {
			aux = primeCandidate(i);
			bol = primalityTest(aux);
			count++;
			if (count == 200) {
				res = BigInteger.probablePrime(i, new Random());
				return res;
				// Just in case that it can't find a prime
			}
		}
		res = new BigInteger(aux.toByteArray());
		// res = BigInteger.probablePrime(i, new Random());
		return res;

	}

	/**
	 * It launch the MillerRabin test with the BigInteger i
	 * 
	 * @param BigInteger
	 *            i
	 * @return true if pass the primality test
	 */
	private static boolean primalityTest(BigInteger i) {
		return MillerRabin.millerRabin(i, 13);

	}

	/**
	 * It creates a BigInteger with a '1' at the first and the last bit This
	 * creates a probability of 2 / (i* ln 2)
	 * 
	 * @param Integer
	 *            i, number of bits
	 * @return BigInteger prime candidate
	 */
	private static BigInteger primeCandidate(Integer i) {
		BitArray b = new BitArray(i);

		for (int j = 1; j < i; j++) {
			if (j == 1 || j == (i - 1)) {
				b.set(j, true);
			} else {
				Random r = new Random();
				if (r.nextInt(10) <= 4) {
					b.set(j, true);
				}
			}
		}
		return new BigInteger(b.toByteArray());
	}

	/**
	 * Method to pow a BigInteger with a BigInteger exponent
	 * 
	 * @param BigInteger
	 *            base
	 * @param BigInteger
	 *            exponent
	 * @return BigInteger result
	 */
//	public static BigInteger pow(BigInteger base, BigInteger exponent) {
//		BigInteger result = BigInteger.ONE;
//		for (BigInteger i = new BigInteger("0"); !i.equals(exponent); i = i.add(BigInteger.ONE)) {
//			result = result.multiply(base);
//		}
//		return result;
//	}
//	public static BigInteger pow(BigInteger base, BigInteger exponent) {
//		BigInteger result = BigInteger.ONE;
//		while (exponent.signum() > 0) {
//			if (exponent.testBit(0))
//				result = result.multiply(base);
//			base = base.multiply(base);
//			exponent = exponent.shiftRight(1);
////			System.out.println(result);
//		}
//		return result;
//	}

	public static BigInteger pow(BigInteger base, BigInteger exponent) {  
		BigInteger s = BigInteger.ONE;
		BigInteger t = base;
		BigInteger q = exponent;
		
		while(q.compareTo(BigInteger.ZERO) == 1){
			if(q.getLowestSetBit() != 0){
				s= s.multiply(t);
			}
			q= q.shiftRight(1);
			t = t.pow(2);
		}
		return s;
	}
	
	public static BigInteger montgomeryPow(BigInteger base, BigInteger exponent) {
//		x1=x; x2=x2
//		for i=k-2 to 0 do
//		  If ni=0 then
//		    x2=x1*x2; x1=x12
//		  else
//		    x1=x1*x2; x2=x22
//		return x1
		
		BigInteger x1 = base:
		BigInteger x2 = base.pow(2);
		for (BigInteger i = exponent.subtract(BigInteger.valueOf(2)); i.equals(BigInteger.ZERO); i = i.subtract(BigInteger.ONE)) {
			
		}
	
	}

	
	
	
	/**
	 * Likelihood of false prime is less than 1/2^ERR_VAL
	 * 
	 * @param BigInteger
	 *            start
	 * @return
	 */
	public static BigInteger nextPrime(BigInteger start) {
		BigInteger two = BigInteger.valueOf(2);
		int err = 100;
		if (start.mod(two).equals(BigInteger.ZERO))
			start = start.add(BigInteger.ONE);
		else
			start = start.add(two);
		if (start.isProbablePrime(err))
			return (start);
		else
			return (nextPrime(start));
	}
}
