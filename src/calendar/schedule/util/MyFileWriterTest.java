package calendar.schedule.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import calendar.schedule.vo.FullCalendar;

public class MyFileWriterTest {
	MyFileWriter mfw = new MyFileWriter();
	MyFileReader mfr = new MyFileReader();
	
	@Test
	public void testWriteFile() {
		mfw.writeFile(new FullCalendar());
		mfr.setFile(new File("d:\\project\\13.15.nurse\\calendar\\src\\resource\\Calendar.json"));
		System.out.println("result >>> "+mfr.readFile());
	}

}
