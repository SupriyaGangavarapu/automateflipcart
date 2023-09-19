package com.example;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Testing {
	public static WebDriver driver;
	@BeforeTest
	public void beforeMethod() {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nagarjuna\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	}
	@Test
	public void pageloadtime() {
	 long startTime = System.currentTimeMillis();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		long endTime = System.currentTimeMillis();
		 long pageLoadTime = endTime - startTime;
	        System.out.println("Page Load Time: " + pageLoadTime + " milliseconds");
		 driver.findElement(By.cssSelector("body > div._2Sn47c > div > div > button")).click();
	}
	@Test(priority=1)
	public void searchItem() {
		driver.findElement(By.name("q")).sendKeys("iphone13");;
		driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/button")).click();
	}
	
	@Test(priority=2)
	public void Scrollbarcheck() throws Exception {
		String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
				JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
				Boolean test = (Boolean) (scrollBarPresent.executeScript(execScript));
				if (test == true) {
					System.out.print("Scrollbar is present.\n");
				} else if (test == false){
					System.out.print("Scrollbar is not present.\n");
				}
			}
	@Test(priority = 3)
    public void scrollToBottom() throws IOException {
        // Scroll to the bottom of the page using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
 }
	@Test(priority = 4)
		public void CheckPageHasRefreshed() {
		String initialPageState = driver.getPageSource();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    String updatedPageState = driver.getPageSource();
    if (initialPageState.equals(updatedPageState)) {
        System.out.println("Content is not refreshed");
    } else {
        System.out.println("Content is refreshed");
    }
}
	@Test(priority=5)
	public void imageload() {
		WebElement img=driver.findElement(By.cssSelector("[alt='APPLE iPhone 13 (Midnight, 128 GB)']"));
		if(img.isDisplayed()) {
			System.out.println("image is loaded");
		}else {
			System.out.println("image is not loaded");
		}
	}
	@AfterTest
	 public void tearDown() {
	 if (driver != null) {
	 driver.quit();
	 System.out.println("Test Ended Successfully.");
	 }
	}
}
