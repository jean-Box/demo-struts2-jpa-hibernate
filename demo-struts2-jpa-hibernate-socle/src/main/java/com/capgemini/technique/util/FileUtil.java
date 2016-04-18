package com.capgemini.technique.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	public static InputStream getResourceAsStream(String path){
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);		
		return inputStream;
	}
	
	public static void copyResource(String srcPath, String destPath) throws IOException{
		InputStream srcInputStream = getResourceAsStream(srcPath);
		
		File destFile = new File(destPath);
		if(!destFile.exists()) {
			destFile.createNewFile();
		}
		OutputStream destOutputStream = new FileOutputStream(destFile);		
		
		byte[] buffer = new byte[1024];
		int nbByteRead = srcInputStream.read(buffer);
		while(nbByteRead != -1) {
			destOutputStream.write(buffer, 0, nbByteRead);
			nbByteRead = srcInputStream.read(buffer);
		}
		
		srcInputStream.close();
		destOutputStream.close();		
	}
}
