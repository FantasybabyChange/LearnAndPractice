package com.fantasybaby.gytest;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import com.fantasybaby.gytest.enumGY.UniCodeEnum;

public class GYMessageTest {
	private static final int message_type_index = 2;
	public void simpleSpelateString(byte[] byteArrays){
		try {
			if(byteArrays != null && byteArrays.length > 0){
				if(byteArrays[0] == UniCodeEnum.STX.getByteCode() && byteArrays[byteArrays.length - 1] == UniCodeEnum.EXT.getByteCode()){
					byte[] byteArra = new byte[byteArrays.length - 2];
					for (int i = 0; i < byteArrays.length; i++) {
						if(i > 0 && i < byteArrays.length -1){
							byteArra[i-1] = byteArrays[i];
						}
					}
					String parseEnd = new String(byteArra,"UTF-8");
					System.out.println(parseEnd);
					String[] split = parseEnd.split("\\"+UniCodeEnum.U007C.getUnicode());
					String messageType = split[message_type_index];
					System.out.println(messageType);
				}else{
					throw new Exception("格式不正确 缺少开头或者结尾的标志位");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		String parseStr= UniCodeEnum.STX.getUnicode()+"62687|SH9|CTDKC|201609290064||GY0012666419|2OA010101|041|2|414490140◇◇00◇A16205◇WS3000067◇◇◇◇◇429◇48◆414477959◇◇00◇M002901◇WS3000411◇◇◇◇◇429◇20|"+UniCodeEnum.EXT.getUnicode();
		byte[] bytes = UniCodeEnum.STX.getUnicode().getBytes("UTF-8");
		int sdksdj = 0x25a1;
		
		
		System.out.println(sdksdj);
		byte[] byte2= new byte[]{-127};
		String string = new BigInteger(1, byte2).toString(10);
		System.out.println(string +";;;;;;;;;;;");
		System.out.println(new String(byte2));
		String s = "\u00Ef";
		int bb = 0x007c;
		System.out.println(bb);
		String bbstr = "\u008E";
		System.out.println(bbstr.getBytes().length);
		for (byte b : bbstr.getBytes()) {
			System.out.println(b+"---");
		}
		System.out.println(new String(bbstr.getBytes()));
		System.out.println(s.getBytes().length);
		byte str = 0x7f;
		byte a123 = (byte) (111100000);
		System.out.println(a123+"--aaaa");
		System.out.println(str);
//		System.out.println(bytes2.length);
//		System.out.println(bytes.length);
		new GYMessageTest().simpleSpelateString(parseStr.getBytes("UTF-8"));
	}
}
