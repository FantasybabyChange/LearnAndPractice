package com.fantasybaby.file.writefile;

import org.apache.commons.io.FileSystemUtils;

import java.io.IOException;

public class FileWriteHelper {
	public static void main(String[] args) throws IOException {/*
		InputStream in = new URL( "http://commons.apache.org" ).openStream();
		 try {
		   InputStreamReader inR = new InputStreamReader( in );
		   BufferedReader buf = new BufferedReader( inR );
		   String line;
		   while ( ( line = buf.readLine() ) != null ) {
		     System.out.println( line );
		   }
		 } finally {
		   in.close();
		 }
	*/
//	 FileUtils.writeStringToFile(new File("my.haa"), "hehe\naaa","UTF-8",false);
	 System.out.println(FileSystemUtils.freeSpaceKb()/1024/1024 );
//		System.out.println(System.getProperty("os.name"));;
	}
}
