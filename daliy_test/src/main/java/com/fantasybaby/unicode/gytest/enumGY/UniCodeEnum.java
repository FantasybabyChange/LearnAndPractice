package com.fantasybaby.unicode.gytest.enumGY;

public enum UniCodeEnum {
	STX("\u0002","stx",0x02),
	EXT("\u0003","ext",0x03),
	U25A1("\u25a1","□",0x25a1),
	U25A0("\u25a0","■",0x25a0),
	U25C7("\u25c7","◇",0x25c7),
	U25C6("\u25c6","◆",0x25c6),
	U007C("\\\u007c","|",0x007c)
	;
	private String unicode;
	private String unicodeLike;
	private int byteCode;
	UniCodeEnum(String unicode,String unicodeLike,int byteCode){
		this.unicode = unicode;
		this.unicodeLike = unicodeLike;
		this.byteCode = byteCode;
	}
	public String getUnicode() {
		return unicode;
	}
	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
	public String getUnicodeLike() {
		return unicodeLike;
	}
	public void setUnicodeLike(String unicodeLike) {
		this.unicodeLike = unicodeLike;
	}
	public int getByteCode() {
		return byteCode;
	}
	public void setByteCode(int byteCode) {
		this.byteCode = byteCode;
	}
}
