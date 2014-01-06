package my.selenium.expectresult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotExpectResult implements ExpectResult {
	private WebDriver driver;
	private String platform;
	private String modulename;
	
	public ScreenshotExpectResult(WebDriver driver, String platform, String modulename){
		this.driver = driver;
		this.platform = platform;
		this.modulename = modulename;
	}
	
	@Override
	public void checkExpectResult() {
		// TODO Auto-generated method stub
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String time = DateFormat.getDateInstance().format(new Date()).replace(",","").trim();
		try {  
        	scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("Screenshot\\"+time.toUpperCase()+"\\"+platform.toUpperCase()+"\\"+modulename.toUpperCase()+".jpg"));
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }
	}
}
