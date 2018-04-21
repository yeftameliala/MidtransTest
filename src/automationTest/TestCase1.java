package automationTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String browserDriver = "C:\\Users\\Martin\\eclipse-workspace\\MidtransTest\\bin\\automationTest\\geckodriver.exe";
		
		System.setProperty("webdriver.gecko.driver",browserDriver);
		WebDriver driver = new FirefoxDriver();
		
		//Get Into Web
		driver.get("https://demo.midtrans.com");
		
		//Click button "BUY NOW"
		ImplicitWait(driver);
		WebElement button = driver.findElement(By.cssSelector("a[class='btn buy']"));
		System.out.println(button.getText());
		button.click();
		
		//Click button Check out
		ImplicitWait(driver);
		WebElement checkout = driver.findElement(By.className("cart-checkout"));
		checkout.click();
		
		//Wait for iFrame loading, click button Continue
		ImplicitWait(driver);
		
		boolean iFrameNotShown = true;
		while (iFrameNotShown) {
			try {
				iFrameNotShown = !driver.findElement(By.id("snap-midtrans")).isDisplayed();
				System.out.println("iFrame found");
			  }
			catch (org.openqa.selenium.NoSuchElementException e) {
			    System.out.println("Error message: "+e.getMessage());
			    ImplicitWait(driver);
			  }
		}
		
		driver.switchTo().frame(driver.findElement(By.id("snap-midtrans")));
		
		boolean buttonNotShown = true;
		while (buttonNotShown) {
			try {
				buttonNotShown = !driver.findElement(By.className("button-main-content")).isDisplayed();
				System.out.println("button shown");
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Error message: "+e.getMessage());
			    ImplicitWait(driver);
			}
		}
		
		WebElement continueBtn = driver.findElement(By.className("button-main-content"));
		continueBtn.click();
		
		//Select payment method - credit card
		List<WebElement> listPayment = driver.findElements(By.className("list"));
		WebElement paymentMethod = listPayment.get(0);
		paymentMethod.click();
		
		//input card detail
		driver.findElement(By.name("cardnumber")).sendKeys("4811111111111114");
		Wait1Second(driver);
		driver.findElement(By.cssSelector("div[class='input-group col-xs-7']")).findElement(By.tagName("input")).sendKeys("0123");
		Wait1Second(driver);
		driver.findElement(By.cssSelector("div[class='input-group col-xs-5']")).findElement(By.tagName("input")).sendKeys("123");
		Wait1Second(driver);
		continueBtn.click();
		
		//input password 3DSecure
		ImplicitWait(driver);
		driver.switchTo().frame(0);
		ImplicitWait(driver);
		
		boolean passwordBoxShown = false;
		while(!passwordBoxShown) 
		{
			try {
				passwordBoxShown = driver.findElement(By.id("PaRes")).isDisplayed();
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Error message: "+e.getMessage());
			    ImplicitWait(driver);
			}
			
		}
		
		driver.findElement(By.id("PaRes")).sendKeys("112233");
		Wait1Second(driver);
		driver.findElement(By.name("ok")).click();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("snap-midtrans")));
		
		//verify tran success
		boolean verifySuccess = false;
		while (!verifySuccess) {
			try {
				verifySuccess = driver.findElement(By.cssSelector("div[class='text-success text-bold']")).isDisplayed();
				WebElement tranSuccess = driver.findElement(By.cssSelector("div[class='text-success text-bold']"));
				if(tranSuccess.getText().trim().equals("Transaction successful")) {
					System.out.println("Message: 'Transaction Successful' verified..");
					break;
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Error message: "+e.getMessage());
			}
		}
		
		/*
		
		//Wait for web loading, click button Continue
		WebElement continueBtn = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='button-main show']")));
		continueBtn.click();
		
		
		System.out.println("Got it bro");
		
		Thread.sleep(50);
		
		*/
		
	}
	
	public static void ImplicitWait(WebDriver driver) 
	{
		System.out.println("Wait a moment...");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	public static void Wait1Second(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);		
	}
	

}
