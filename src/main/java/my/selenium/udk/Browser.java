package my.selenium.udk;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Browser {
		//Create a WebDriver reference
		private WebDriver driver;
		//Define the browser
		private final String BROWSESR;
		private final String URL = "http://udk.corp.hp.com/commonCss/index.html";
		
		public Browser(String browser){
			BROWSESR = browser;
			switch(BROWSESR){
			//The following code is for setting up the Firefox driver
			case "firefox":
				driver = new FirefoxDriver();
				break;
			
			//The following code is for setting up the Chrome driver
			case "chrome":
				//Set the chrome directory
				System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
				
			//The following code is for setting up the IE driver		
			case "ie":
				//Set the IE Driver Server
				System.setProperty("webdriver.ie.driver","src\\main\\resources\\IEDriverServer.exe");
				//Create the IE capabilities
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				//Set the IE capabilities
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				//Create the IE driver instance
				driver = new InternetExplorerDriver(ieCapabilities);
				break;
			}
		}
		
		public void setWindowSize(int width, int height){
			driver.manage().window().setSize(new Dimension(width, height));
		}
		
		public void maxWindow(){
			driver.manage().window().maximize();
		}
		
		public void openUDK(){
			//Open the URL
			driver.get(URL);
		}
		
		public void goToAPI() throws InterruptedException{
			openUDK();
			WebElement element = driver.findElement(By.xpath("//a[@href=\"http://g5t1281g.atlanta.hp.com:8180/udk/index.html\"]"));
			element.click();
			element = driver.findElement(By.xpath("//a[text()=\"UI Element and Widget Knowledgebase\"]"));
			element.click();
			element = driver.findElement(By.xpath("//a[text()=\"UI Element and Widget\"]"));
			Thread.sleep(100);
			element.click();
		}
		
		public WebDriver getDriver() {
			return driver;
		}
		
		public static void waitElement(WebDriver driver, final By xpath){
			new WebDriverWait(driver, 10).until (new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					Boolean result = false;
					try {
						driver.findElement(xpath);
						result = true;
					} catch(Exception e){		
					}
					return result;
				}
			});
		}
		
		public static void screenShot(WebDriver driver, String PLATFORM, String name){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String time = DateFormat.getDateInstance().format(new Date()).replace(",","").trim();
			try {  
	        	scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(scrFile, new File("Screenshot\\"+time.toUpperCase()+"\\"+PLATFORM.toUpperCase()+"\\"+name.toUpperCase()+".jpg"));
	        } catch (IOException e1) {  
	            e1.printStackTrace();  
	        }  
		}
}
