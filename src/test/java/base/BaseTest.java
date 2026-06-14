package base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.ExtentManager;
import utils.ScreenshotUtils;

public class BaseTest {
	
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	protected ExtentReports extent;
	protected ExtentTest test;
	
	@BeforeMethod
	public void setUp(Method method) {
		//reporting
		extent = ExtentManager.getInstance();
		test = extent.createTest(method.getName());
		//setup
		test.info("Launching browser");
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		page = browser.newPage();	
		page.navigate("https://demoqa.com/automation-practice-form");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()== ITestResult.FAILURE) {
			test.fail(result.getThrowable());
			
			//capture and attach screenshot
			String screenshotpath = ScreenshotUtils.takeScreenshot(page, result.getName());
			
			String projectPath = System.getProperty("user.dir");
			String absolutessPath = projectPath+"/"+screenshotpath;
		
			
			
			//test.addScreenCaptureFromBase64String(screenshotpath);
			test.addScreenCaptureFromPath(absolutessPath, "");
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test passed");
		}else {
			test.skip("Test skipped");
		}
		extent.flush();
		test.info("Closing browser");
		if(browser!= null) browser.close();
		if(playwright!=null) playwright.close();
		
	}


}
