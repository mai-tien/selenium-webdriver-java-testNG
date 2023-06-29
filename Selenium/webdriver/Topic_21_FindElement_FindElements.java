package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_FindElement_FindElements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		expliciWait = new WebDriverWait(driver, 10);
		
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
	}

	@Test
	public void TC_01_FindElement() {
		//Tìm thấy duy nhất 1 element/node
		//Tìm thấy và khao tác lên element đó
		//Vì nó tìm thấy nên không cần chờ hết timeout
		driver.findElement(By.cssSelector("input#email"));
		
		//Tìm thấy nhiều hơn 1 element/node	
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("maihoa@gmail.com");
		
		//Không tìm thấy 1 element nào	
		// Có cơ chế tìm lại, 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thì thỏa dk pass
		// Nếu hết tg mà vẫn k tìm thấy element thì
		// + Đánh fail testcase này tại step này
		// + Throw ra 1 ngoại lệ NoSuchElementExeption

	}
	@Test
	public void TC_02_FindElements() {
		//Tìm thấy duy nhất 1 element/node
		//Tìm thấy và lưu nó vào 1 list =  1 element 
		//Vì nó tìm thấy nên không cần chờ hết timeout	
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println(" list element number =" + elements.size());
	
		//Tìm thấy nhiều hơn 1 element/node	
		//Tìm thấy và lưu nó vào 1 list = element tương ứng
		elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println(" list element number =" + elements.size());
			
		//Không tìm thấy 1 element nào	
		// Có cơ chế tìm lại, 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thì thỏa dk pass
		// Nếu hết tg mà vẫn k tìm thấy element thì
		// + Không Đánh fail testcase + mà vẫn chạy step tiếp theo
		// + Trả về 1 list rỗng (empty) = 0	

	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1000ms = 1s
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}