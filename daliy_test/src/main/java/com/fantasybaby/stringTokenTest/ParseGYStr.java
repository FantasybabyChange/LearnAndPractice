package com.fantasybaby.stringTokenTest;

import java.util.StringTokenizer;

import com.fantasybaby.unicodeconvert.UnicodeConvertUtil;
/**
 * jdk not recommoned this API we can use split to done the same function
 * @author FantasyBaby
 *
 */
public class ParseGYStr {
	String parseStr = "62687|SH9|CTDKC|201609290064||GY0012666419|2OA010101|041|2|414490140◇◇00◇A16205◇WS3000067◇◇◇◇◇429◇48◆414477959◇◇00◇M002901◇WS3000411◇◇◇◇◇429◇20|";
	int messageTypeIndex = 2;
	String UNICODE25A1 = "□";
	String UNICODE25A0 = "■";
	String UNICODE25C7 = "◇";
	String UNICODE25C6 = "◆";
	String verticalLine = "\\|";
	
	public void parseStr(){
		StringTokenizer st =new StringTokenizer(parseStr,"|");
		while (st.hasMoreElements()) {
			String nextStr = st.nextToken();
			System.out.println(nextStr);
			StringTokenizer st1 = new StringTokenizer(nextStr, UNICODE25C7,false);
			while (st1.hasMoreElements()) {
				System.out.println(st1.nextToken()+"---");
			}
			
		}
	}
	public void splitStr(){
		String[] split = parseStr.split(verticalLine);
		String messageType = split[messageTypeIndex];
		System.out.println("messageTypeL:" + messageType);
		String stringSecond = split[9];
		System.out.println(stringSecond);
		String[] split3s = stringSecond.split(UNICODE25C7);
		for (String string2 : split3s) {
			System.out.println(string2+"----");
		}
//		for (String string : split) {
//			System.out.println(string);
//			String[] split2 = string.split(UNICODE25C7);
//			for (String string2 : split2) {
//				System.out.println(string2+"----");
//				String[] split3s = string2.split(UNICODE25A1);
//				for (String str3 : split3s) {
//					System.out.println(str3+"++++");
//				}
//			}
//		}
	}
	public static void main(String[] args) {
//		new ParseGYStr().parseStr();
		new ParseGYStr().splitStr();
		/*int a = 124;
		char e = (char)a;
		System.out.println(e);*/
	}
}
