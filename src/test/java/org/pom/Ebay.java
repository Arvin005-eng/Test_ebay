package org.pom;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Ebay extends BaseClass{

	public Ebay() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[contains(text(),'Enter your search keyword')]//following-sibling::input[@aria-label='Search for anything']")
	private WebElement searchBox;
	
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//ul//li[@data-view='mi:1686|iid:1' and @data-viewport]//div[@class='s-item__info clearfix']/a")
	private WebElement firstProduct;
	
	@FindBy(id = "atcBtn_btn_1")
	private WebElement addToCart;

	@FindBy(xpath = "//div[@class='grid-item-quantity']//select[@data-test-id='qty-dropdown']")
	private List<WebElement> qtyDropdown;
	
	@FindBy(xpath = "//li[@id='gh-minicart-hover']//i")
	private WebElement productCount;
	
	@FindBy(xpath = "//ul//li[@data-view='mi:1686|iid:1' and @data-viewport]//span[@role='heading']")
	private WebElement firstProductName;
	
	@FindBy(xpath = "//h3[@class='item-title text-truncate-multiline black-link lines-2']/a")
	private WebElement cartProductName;
	

	public WebElement getCartProductName() {
		return cartProductName;
	}

	public WebElement getFirstProductName() {
		return firstProductName;
	}

	public WebElement getProductCount() {
		return productCount;
	}

	public List<WebElement> getQtyDropdown() {
		return qtyDropdown;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getFirstProduct() {
		return firstProduct;
	}

	public WebElement getAddToCart() {
		return addToCart;
	}

	
}
