package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	public static String md5(String str){
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bi = new BigInteger(1, md.digest());
			
			result = bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getPreview(String s, int count) {
		String[] st = s.split("\\s");
		String result = "";
		int i = 0;
		for (String w:st) {
			if (!"".equals(w)) {
				result += (w + " ");
				i++;
			}
			if (i == count)
				break;
		}
		result += "...";
		return result;
	}
	
}
