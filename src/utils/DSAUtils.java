package utils;

import java.math.BigInteger;
import java.util.Random;

import sun.security.util.BitArray;

public class DSAUtils {

	public static BigInteger getPrime(Integer i) {
		BigInteger res;
//		
//		BigInteger aux = primeCandidate();
//		boolean bol = primalityTest(aux);
//		while(!bol){
//			 aux = primeCandidate();
//			 bol = primalityTest(aux);
//		}
//		System.out.println(aux);	
//		res = new BigInteger(b.toByteArray());
		res = BigInteger.probablePrime(i, new Random());
		return res;

	}

	@SuppressWarnings("unused")
	private static boolean primalityTest(BigInteger i) {
		return MillerRabin.millerRabin(i, 50);

	}

	@SuppressWarnings("unused")
	private static BigInteger primeCandidate() {
		BitArray b = new BitArray(160);

		for (int i = 1; i < 160; i++) {
			if (i == 1 || i == 159) {
				b.set(i, true);
			} else {
				Random r = new Random();
				if (r.nextInt(10) <= 4) {
					b.set(i, true);
				}
			}
		}
		return new BigInteger(b.toByteArray());
	}
}
