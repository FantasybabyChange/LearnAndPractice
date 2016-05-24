package com.fantasybaby.lockfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LocalFileJava {
	//will show different info in different system
	public void showFileSeparator(){
		System.out.println(File.separator+" "+File.separatorChar+" "+File.pathSeparator+" "+File.pathSeparatorChar);
	}
	public static void main(String[] args) throws URISyntaxException {
		File file = new File(LocalFileJava.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		String parent = file.getParent();
		System.out.println(file.getParent());
		System.out.println(file.getParentFile().getPath());
		parent = parent.concat(File.separator).concat("fantasybaby.lock");
		try {
			RandomAccessFile  randomFille = new RandomAccessFile(parent, "rw");
			FileChannel channel = randomFille.getChannel();
//			FileLock tryLock = channel.tryLock();
			randomFille.seek(randomFille.length());
			Thread.sleep(10000);
			randomFille.writeChars("hello world\r\n");
			randomFille.writeChars("hello world\r\n");
//			tryLock.release();
			channel.close();
			randomFille.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
