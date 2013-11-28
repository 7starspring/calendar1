package calendar.schedule.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;
import org.springframework.stereotype.Service;

@Service("myStringManager")
public class MyStringManager {

	public String nullToString(String in){
		String out = " ";
		if(in == null || in.trim().equals("")){
			out = " ";
		}else{
			out = in;
		}
		return out;
	}
	
	public String encodeToUtf8(String in){
		String out = "";
		try {
			out = URLEncoder.encode(in, "UTF-8");
			out = out.replaceAll("\\+", " ").replaceAll("%3F", "?");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return out;
	}
	
	public String getTextOfFile(File folder, String strFileName){
		StringBuilder sb = new StringBuilder();
		String line;
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(folder.toString() + "/" + strFileName)));
			try {
				while ((line = br.readLine()) != null) {
					sb.append( line );
				}
			} catch (IOException e) {
			}
			
		} catch (FileNotFoundException e) {
		} finally {
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
			}
		}
		return sb.toString();
	}
}
