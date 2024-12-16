package org.steprunner;

import java.util.List;

import org.base.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.pom.Ebay;
import org.pom.POMManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StepAction extends BaseClass {
	private Scenario scenario;
	private POMManager pom = POMManager.getPomManager();
	
	public static String productName;
	public Response apiResponse;
	
	
	@Before
	public void setScenario(Scenario scenario) {
		this.scenario = scenario; // Initialize the Scenario object before each scenario
		lauchChromeDriver();
	}

	public void captureAndAttachScreenshot( String screenshotName, Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		this.scenario = scenario; 
		scenario.attach(screenshot, "image/png", screenshotName);
	}
	
	@Then("get screenshot")
	public void getScreenshot() {
		captureAndAttachScreenshot( "screenshots",scenario);
	}
	
	@After
	public void afterClass() {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "final screenshot");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@Given("Open the browser and navigate to url")
	public void open_the_browser_and_navigate_to_url() {
		driver.get("https://www.ebay.com/");
	}

	@When("Searches for {string} on eBay")
	public void searches_for_on_e_bay(String value) {
		Ebay ebay = pom.geteBay();
		textValue(ebay.getSearchBox(), value);
		clickAction(ebay.getSearchButton());
	}

	@When("Select first search result")
	public void select_first_search_result() {
		Ebay ebay = pom.geteBay();
		productName = getText(ebay.getFirstProductName());
		clickAction(ebay.getFirstProduct());
	}
	
	@When("Switch to window using index {int}")
	public void switch_to_window_using_index(int index) {
	    switchWindowUsingId(index);
	}

	@When("Add iteam to cart")
	public void add_iteam_to_cart() {
		Ebay ebay = pom.geteBay();
		clickAction(ebay.getAddToCart());
	}

	@Then("Verify the cart has been updated")
	public void verify_the_cart_has_been_updated() throws InterruptedException {
		Ebay ebay = pom.geteBay();
		int cartCount = getSelectedValueFromListOfDropDown(ebay.getQtyDropdown());
		String value = getText(ebay.getProductCount());
		int productCount = Integer.parseInt(value);
		assertEquel(cartCount, productCount);	
		String cartProductname = getText(ebay.getCartProductName());
		validatingTextValues(productName, cartProductname);
	}

	@Given("Get Api response using BaseURL {string}")
	public void get_api_response_using_base_url(String url) {
		apiResponse = getApiResponse(url);
		int statusCode = apiResponse.getStatusCode();
		System.out.println(statusCode);
	}

	@Given("Verify the response contains {string}")
	public void verify_the_response_contains(String BPI) {
		Assert.assertTrue("USD not found in response", apiResponse.jsonPath().get("bpi").toString().contains(BPI));
	}

	@Given("Verify the GBP description equals {string}")
	public void verify_the_gbp_description_equals(String Description) {
		 String gbpDescription = apiResponse.jsonPath().get("bpi.GBP.description").toString();
	        Assert.assertEquals("GBP description is incorrect", Description, gbpDescription);
	}


}
