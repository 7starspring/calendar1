package calendar.schedule.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import calendar.schedule.vo.FullCalendar;

public class MyFileWriter {

	public void writeFile(FullCalendar fc){
		String data = ",{\"title\":\""+fc.getTitle()+"\",\"start\":\""+fc.getStart()+"\"}";
		
		try{
    		File file =new File("d:\\project\\13.15.nurse\\calendar\\src\\resource\\Calendar.txt");
 
    		if(!file.exists()){
    			file.createNewFile();
    		}
// #1 malfunction
//    		System.out.println(file.getName());
//    		FileWriter fileWriter = new FileWriter(file.getName(),true);
//    		BufferedWriter bufferdWriter = new BufferedWriter(fileWriter);
//    		bufferdWriter.write(data);
//    		
//    		fileWriter.close();
//    		bufferdWriter.close();
//	        System.out.println(data);
    		
    		// #2 malfunction
    		PrintWriter out = null;
    	    BufferedWriter bufWriter;

    	    try{
    	        bufWriter =
    	            Files.newBufferedWriter(
    	                Paths.get(file.getName()),
    	                Charset.forName("UTF8"),
    	                StandardOpenOption.WRITE, 
    	                StandardOpenOption.APPEND,
    	                StandardOpenOption.CREATE);
    	        out = new PrintWriter(bufWriter, true);
    	    }catch(IOException e){
    	        //Oh, no! Failed to create PrintWriter
    	    }

    	    //After successful creation of PrintWriter
    	    out.println(data);

    	    //After done writing, remember to close!
    	    out.close();
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
}
