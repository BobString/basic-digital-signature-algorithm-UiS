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
		return res;

	}

	/**
	 * It launch the MillerRabin test with the BigInteger i
	 * 
	 * @param BigInteger
	 *            i
	 * @return true if pass the primality test
	 */
	public static boolean primalityTest(BigInteger i) {
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

	
	
}
