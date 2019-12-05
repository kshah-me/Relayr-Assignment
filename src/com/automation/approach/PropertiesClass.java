package com.automation.approach;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class PropertiesClass {
	
	static Properties readprop = new Properties();
	static WebDriver driver;
	
	public static WebDriver readProperties() throws IOException {
		InputStream input = new FileInputStream(System.getProperty("user.dir" )+"/config.properties"); 
		readprop.load(input); 
		String bn= readprop.getProperty("BrosweName");
		if(bn.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir" )+"/drivers/chromedriver.exe" );
			driver = new ChromeDriver(); 
			driver.manage().window().maximize();
		}
		else if(bn.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir" )+"/drivers/geckodriver.exe" );
			driver = new FirefoxDriver(); 
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} 
		else if(bn.equals("InternetExplorer"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir" )+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(); 
			driver.manage().window().maximize();
		}
		else 
		{
			System.out.println("Incorrect Browser name");
		}
		return driver;
	}

}
