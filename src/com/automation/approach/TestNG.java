package com.automation.approach;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG {
 WebDriver driver;

@BeforeMethod
	public void launchBrowser() throws IOException { 
	driver = PropertiesClass.readProperties();
    }
	
    @Test (priority=1)
    public void breakImage() {
	driver.get("https://the-internet.herokuapp.com/broken_images"); 		
	List<WebElement> listimageidentification = driver.findElements(By.tagName("img"));         
      for(WebElement brokedimageidentification: listimageidentification)
      {
    	try {
			HttpURLConnection conn = (HttpURLConnection) new URL(brokedimageidentification.getAttribute("src")).openConnection();
		    conn.setRequestMethod("GET");
		    int rescode = conn.getResponseCode();
		    if(rescode != 200)
            {
          	System.out.println("Broken Image is:->  " + brokedimageidentification.getAttribute("src") );
            }
    	    }
    	  catch (Exception e) {e.printStackTrace();} 
      }
}
    @Test (priority=2)	
	public void authenticationBrowser(){
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");			
		String successmessage = driver.findElement(By.cssSelector("p")).getText();	
	    Assert.assertEquals(successmessage, "Congratulations! You must have the proper credentials.");    
        }

    @Test (priority=3)   
    public void SliderMinMaxValues(){   
	driver.get("https://the-internet.herokuapp.com/horizontal_slider");
	WebElement slider = driver.findElement(By.xpath("//*[@id='content']/div/div/input"));
	WebElement output = driver.findElement(By.xpath("//*[@id='range']"));
	Actions action = new Actions(driver);
	action.dragAndDropBy(slider, 100, 0).perform();
	Assert.assertEquals(5, Integer.parseInt(output.getText()), "This is Max Number");
	action.dragAndDropBy(slider, -100, 0).perform();
	Assert.assertEquals(0, Integer.parseInt(output.getText()), "This is Min Number");
    }    
       
    @Test (priority=4)
	public void imagehover() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/hovers");
        List<WebElement> hoverParent = driver.findElements(By.xpath("//*[@id='content']/div/div"));
        List<String> userList = new ArrayList<String>();
        userList.add("name: user1");
        userList.add("name: user2");
        userList.add("name: user3");
        int i = 0;
        Actions action = new Actions(driver);
        for (WebElement image: hoverParent)
        {
        action.moveToElement(image).perform();
		WebElement username = image.findElement(By.tagName("h5"));
		Thread.sleep(3000);
		Assert.assertEquals(username.getText(),userList.get(i++));
		}
     	}
     
	@AfterMethod
	public void closebrowser()
	{
	 driver.close();	
	}
}