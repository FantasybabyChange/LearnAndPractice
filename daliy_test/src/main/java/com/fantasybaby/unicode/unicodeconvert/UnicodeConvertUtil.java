package com.fantasybaby.unicode.unicodeconvert;

import java.io.UnsupportedEncodingException;

import com.fantasybaby.unicode.gytest.enumGY.UniCodeEnum;

public class UnicodeConvertUtil {
	/**
	 * ���ַ���ת��unicode
	 * @param str ��ת�ַ���
	 * @return unicode�ַ���
	 */
	public static String convert(String str)
	{
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++)
		{
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>>8); //ȡ����8λ
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); //ȡ����8λ
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
	}

	/**
	 * ��unicode �ַ���
	 * @param str ��ת�ַ���
	 * @return ��ͨ�ַ���
	 */
	public static String revert(String str)
	{
		str = (str == null ? "" : str);
		if (str.indexOf("\\u") == -1)//�������unicode����ԭ������
			return str;

		StringBuffer sb = new StringBuffer(1000);

		for (int i = 0; i < str.length() - 6;)
		{
			String strTemp = str.substring(i, i + 6);
			String value = strTemp.substring(2);
			int c = 0;
			for (int j = 0; j < value.length(); j++)
			{
				char tempChar = value.charAt(j);
				int t = 0;
				switch (tempChar)
				{
					case 'a':
						t = 10;
						break;
					case 'b':
						t = 11;
						break;
					case 'c':
						t = 12;
						break;
					case 'd':
						t = 13;
						break;
					case 'e':
						t = 14;
						break;
					case 'f':
						t = 15;
						break;
					default:
						t = tempChar - 48;
						break;
				}

				c += t * ((int) Math.pow(16, (value.length() - j - 1)));
			}
			sb.append((char) c);
			i = i + 6;
		}
		return sb.toString();
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		String convert = new UnicodeConvertUtil().convert("|");
		char a1 = '��';
		char convert1 = '\u25a1';
//		byte[] bytes = convert1.getBytes();
		String str1 = "\u25a1";
		String hehe = convert1 + str1;
		System.out.println(hehe.length());
		byte[] bytes = str1.getBytes();
		System.out.println(new String(bytes,"UTF-8"));
		System.out.println("--------------");
		char a = '��';
		char b = '\u25a1';
		System.out.println(b);
		System.out.println(a);
		System.out.println(a == b);
		System.out.println(convert);
		byte startHex = 0x02;
		String startStr = new String(new byte[]{startHex},"UTF-8");
		String startUnicode = "\u0002";

		System.out.println(startStr.equals(startUnicode));
		System.out.println(startUnicode.length());
		byte endHex = 0x03;
//		System.out.println(startHex.length());
		UniCodeEnum[] values = UniCodeEnum.values();
		UnicodeConvertUtil unicodeConvertUtil = new UnicodeConvertUtil();
		System.out.println("-- start convert");
		for (UniCodeEnum uniCodeEnum : values) {
			String revert = unicodeConvertUtil.revert(uniCodeEnum.getUnicode());
			String convert2 = unicodeConvertUtil.convert(uniCodeEnum.getUnicodeLike());
			System.out.println(revert +"-" + convert2);
		}
	}
}
