package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import utils.DSAUtils;

import com.sun.tools.javac.util.Pair;

public class DSA {

	/*
	 * Static methods -> (M,r,s) sign(M, claves) Boolean verify(M, r, s)
	 */

	public Pair<String, Pair<BigInteger, BigInteger>> sign(String message,
			BigInteger publcG, BigInteger publicP, BigInteger publicQ,
			BigInteger privateX) {
		// K
		BigInteger k = DSAUtils.getPrime(publicQ.bitLength() - 1);

		// R
		BigInteger r = publcG.modPow(k, publicP).mod(publicQ);

		// S
		MessageDigest mda = null;
		try {
			mda = MessageDigest.getInstance("SHA-512");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] digesta = mda.digest(message.getBytes());
		BigInteger sha = new BigInteger(digesta);

		BigInteger s = sha.divide(k).add(privateX.multiply(r)).mod(publicQ);

		Pair<String, Pair<BigInteger, BigInteger>> result = Pair.of(message,
				Pair.of(r, s));
		return result;
	}
	
	public Boolean verify(String message, BigInteger r, BigInteger s, BigInteger publcG, BigInteger publicP, BigInteger publicQ, BigInteger privateY){
		
		//W
		BigInteger w = s.modPow(BigInteger.valueOf(-1), publicQ);
		
		//U1
		MessageDigest mda = null;
		try {
			mda = MessageDigest.getInstance("SHA-512");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] digesta = mda.digest(message.getBytes());
		BigInteger sha = new BigInteger(digesta);
		
		BigInteger u1 = sha.multiply(w).mod(publicQ);
		
		//U2
		BigInteger u2 = r.multiply(w).mod(publicQ);
		
		//v
		BigInteger v = DSAUtils.pow(publcG,u1).multiply(DSAUtils.pow(privateY, u2)).mod(publicQ);
		
		if(v.equals(r)){
			return true;
		}else{
			return false;
		}
	}

}
