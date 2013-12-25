package my.selenium.udk;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * @author guka
 *
 */
public class TestAccordion {
	
	WebDriver driver;
	WebElement element;
	List<WebElement> elements;
	final String PLATFORM = "chrome";
	String actualstr;
	String expectedstr;
	@BeforeTest
	public void beReady() throws InterruptedException{
		Browser browser = new Browser(PLATFORM);
		browser.setWindowSize(800, 600);
		browser.goToAPI();
		driver = browser.getDriver();
	}
	
	@Test
	public void ExpColAll() throws InterruptedException{
		element = driver.findElement(By.xpath("//li/a[text()=\"Accordion/Progressive Disclosure\"]"));
		element.click();		
		Browser.waitElement(driver, By.xpath("//div[@class=\"control-accordion\"]/span[position()=1]"));
		element = driver.findElement(By.xpath("//div[@class=\"control-accordion\"]/span[position()=1]"));
		element.click();
		Thread.sleep(1000);
        Browser.screenShot(driver, PLATFORM,"Accordion Collapse All");
		actualstr = driver.findElement(By.xpath("//div[@class=\"control-accordion\"]/span[position()=2]")).getText();
		expectedstr = "Collapse All";
		Assert.assertEquals(actualstr,expectedstr);
		element = driver.findElement(By.xpath("//div[@class=\"control-accordion\"]/span[position()=1]"));
		element.click();
		Thread.sleep(1000);
        Browser.screenShot(driver, PLATFORM,"Accordion Expand All");		
		actualstr = driver.findElement(By.xpath("//div[@class=\"control-accordion\"]/span[position()=2]")).getText();
		expectedstr = "Expand All";
		Assert.assertEquals(actualstr,expectedstr);
	}
	
	@Test
	public void ExpColSingle() throws InterruptedException{
		String classplus = "accordion-icon icon-plus";
		String classmin = "accordion-icon icon-minus";
		String allbtn = actualstr = driver.findElement(By.xpath("//div[@class=\"control-accordion\"]/span[position()=2]")).getText();
		elements = driver.findElements(By.xpath("//section/h2//span"));
		for (WebElement e:elements){
			if(e.getAttribute("class").equals(classplus)){
				e.click();
				Assert.assertEquals(e.getAttribute("class"),classmin);
			}else{
				e.click();
				Assert.assertEquals(e.getAttribute("class"),classplus);
			}
			if (e.equals(elements.get(elements.size()-1))){
				if (allbtn.equals("Expand All")){
					actualstr = driver.findElement(By.xpath("//div[@class=\"control-accordion\"]/span[position()=2]")).getText();
					expectedstr = "Collapse All";
					Assert.assertEquals(actualstr,expectedstr);
				}else if (allbtn.equals("Collapse All")){
					actualstr = driver.findElement(By.xpath("//div[@class=\"control-accordion\"]/span[position()=2]")).getText();
					expectedstr = "Expand All";
					Assert.assertEquals(actualstr,expectedstr);
				}
			}
		}
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
		
}
