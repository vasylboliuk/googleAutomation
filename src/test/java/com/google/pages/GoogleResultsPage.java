package com.google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleResultsPage {
	protected WebDriver driver;

	@FindBy(xpath = "//div[@class='rc']")
	WebElement googleAllPageResults;

	public GoogleResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnResultLinkInPage() {
		driver.findElement(By.xpath("//div[@class='g'][1]//ancestor::h3//a")).click();
	}

	/**
	 * Navigate to Google Translate page
	 */
	public GoogleTranslatePage navigateToGoogleTranslate() {
		clickOnResultLinkInPage();
		return new GoogleTranslatePage(driver);
	}

	/**
	 * Navigate to Google Result page with the given page number.
	 */
	public GoogleResultsPage navigateToGoogleResultPage(int pageNumber) {
		driver.findElement(By.xpath("//a[@class='fl' and contains(text(),'" + pageNumber + "')]")).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//td[@class='cur' and contains(text(),'" + pageNumber + "')]")));
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rc']")));
		return new GoogleResultsPage(driver);

	}

	/**
	 * Get the number of results on page.
	 * 
	 * @return this number of results.
	 */
	public int getNumberOfResults() {
		return driver.findElements(By.xpath("//div[@class='rc']")).size();
	}

}
