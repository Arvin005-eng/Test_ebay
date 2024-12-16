package org.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.directory.DirContext;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.github.dockerjava.api.model.Driver;

import io.cucumber.core.logging.Logger;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static WebDriver driver;


	public void textValue(WebElement e, String value) {
		e.clear();
		e.sendKeys(value);
	}

	public void clickAction(WebElement e) {
		e.click();
	}

	public WebDriver lauchChromeDriver() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		return driver;

	}

	public void screenshot() throws IOException {
		TakesScreenshot t= (TakesScreenshot) driver;
		File screenshotAs = t.getScreenshotAs(OutputType.FILE);
		File f= new File("C:\\Users");
		f.createNewFile();
		FileUtils.copyFile(screenshotAs, f);

	}

	public void switchWindowUsingId(int index) {
		try {
			List<String> windowHandles=new ArrayList<String>(driver.getWindowHandles());
			if (index>=0 && index< windowHandles.size()) {
				driver.switchTo().window(windowHandles.get(index));
			}else {
				System.out.println("Invalid index: "+index);
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public int getSelectedValueFromListOfDropDown(List<WebElement> e) {
		int Qty = 0;
		for (WebElement webElement : e) {
			Select dropdown= new Select(webElement);
			String text = dropdown.getFirstSelectedOption().getText();
			int intValue = Integer.parseInt(text);
			Qty=Qty+intValue;
		}
		return Qty;
	}

	public String getText(WebElement e) {		
		String text = e.getText();
		return text;
	}

	public void assertEquel(int actualValue, int expectedValue) {
		Assert.assertEquals(actualValue, expectedValue);
	}

	public void validatingTextValues(String actualValue, String expectedValue) {
		Assert.assertEquals("String are not equal !",actualValue, expectedValue);
	}

	public Response getApiResponse(String url) {
		RestAssured.baseURI=url;
		RequestSpecification RS = RestAssured.given();
		Response response = RS.request(Method.GET,"/currentprice.json");
		return response;
	}
	
	
}
