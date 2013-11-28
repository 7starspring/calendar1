package calendar.schedule.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.annotation.Resource;

import calendar.schedule.util.MyStringManager;
import calendar.schedule.vo.Calendar;
import calendar.schedule.vo.FullCalendar;

import org.apache.naming.factory.BeanFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("myScheduleController")
@Controller("/calendar.do")
public class ScheduleController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model md){
		md.addAttribute("jsonList",getCalendarList());
		return "calendar/calendar";
	}

	@RequestMapping(params = "method=create")
	@ResponseBody
	public String createData(FullCalendar fc){
		String data = ",{\"title\":\""+fc.getTitle()+"\",\"start\":\""+fc.getStart()+"\"}";
		System.out.println("data >>> "+data);
		try{
    		File file =new File("d:\\project\\13.15.nurse\\calendar\\Calendar.txt");
 
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		FileWriter fileWriter = new FileWriter(file.getName(),true);
    		BufferedWriter bufferdWriter = new BufferedWriter(fileWriter);
    		bufferdWriter.write(data);
    		
    		bufferdWriter.close();
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		
		return getCalendarList();
	}
	
	private String getCalendarList(){
		MyStringManager myStringManager = new MyStringManager();
		String fileData=null;
		try {
			fileData=myStringManager.getTextOfFile(new File("d:\\project\\13.15.nurse\\calendar"), "Calendar.txt");
			fileData = "["+fileData+"]";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(fileData);
		return fileData;
	}
	
}
