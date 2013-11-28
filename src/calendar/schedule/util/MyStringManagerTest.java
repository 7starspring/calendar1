package calendar.schedule.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyStringManagerTest {
	@Autowired(required=false)
	MyStringManager myStringManager = new MyStringManager();
	
	@Test
	public void testNullToString() {
		assertSame("song", myStringManager.nullToString("song"));
		assertSame(" ", myStringManager.nullToString(null));
	}

	@Test
	public void testGetTextOfFile() throws IOException {
		System.out.println(myStringManager.getTextOfFile(new File(new File(".").getCanonicalPath()+"\\src\\resource"), "Calendar.json"));
	}

}
