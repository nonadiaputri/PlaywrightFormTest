package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;

import base.BaseTest;
import pages.RegistPage;
import testdata.StudentData;

public class RegistTest extends BaseTest {
	
	private RegistPage form;
	
	@BeforeMethod
	public void setupPage() {
	    page.navigate("https://demoqa.com/automation-practice-form");
	    form = new RegistPage(page);
	}
	
	
	
	@Test
	public void submitFormWithValidTest() {
		
		test.info("start fill form with valid data");
		form.submitForm(StudentData.FIRST_NAME, StudentData.LAST_NAME, StudentData.EMAIL, StudentData.GENDER, StudentData.MOBILE);
		Assert.assertTrue(page.locator("#example-modal-sizes-title-lg").isVisible());
		String popupText = page.locator("#example-modal-sizes-title-lg").textContent();
		Assert.assertEquals(popupText, "Thanks for submitting the form");
		String actualName = page.locator("tbody tr")
		        .filter(new Locator.FilterOptions().setHasText("Student Name"))
		        .locator("td")
		        .nth(1)
		        .textContent();

		String expectedName =
		        StudentData.FIRST_NAME + " " + StudentData.LAST_NAME;
		
		Assert.assertEquals(actualName, expectedName);
		String actualStudentEmail = page.locator("tbody tr")
		        .filter(new Locator.FilterOptions().setHasText("Student Email"))
		        .locator("td")
		        .nth(1)
		        .textContent();

		Assert.assertEquals(actualStudentEmail, StudentData.EMAIL);
		
		String actualGender = page.locator("tbody tr")
		        .filter(new Locator.FilterOptions().setHasText("Gender"))
		        .locator("td")
		        .nth(1)
		        .textContent();
		Assert.assertEquals(actualGender, StudentData.GENDER);
		String actualMobile = page.locator("tbody tr")
		        .filter(new Locator.FilterOptions().setHasText("Mobile"))
		        .locator("td")
		        .nth(1)
		        .textContent();
		Assert.assertEquals(actualMobile, StudentData.MOBILE);
		
		test.info("submission succesfull");
		
	}
	
	@Test
	public void submitFormWithInvalidEmailTest() {
		test.info("submiting with invalid email");
		form.submitForm(StudentData.FIRST_NAME, StudentData.LAST_NAME, StudentData.INVALID_EMAIL, StudentData.GENDER, StudentData.MOBILE);
		String msg = page.locator("#userEmail").evaluate("e => e.validationMessage").toString();
		//System.out.println(msg);
		Assert.assertEquals("Please match the requested format.", msg);
		test.info("error submit invalid email");	
	}
	
	@Test
	public void submitFormWithEmptyDataTest() {
		test.info("submiting emty form");
		form.submitEmptyForm();
		Assert.assertFalse(page.locator("#example-modal-sizes-title-lg").isVisible());
		String msgFirstname = page.locator("#firstName").evaluate("e => e.validationMessage").toString();
		String msglastname = page.locator("#lastName").evaluate("e => e.validationMessage").toString();
		String msgemail = page.locator("#userEmail").evaluate("e => e.validationMessage").toString();
		String msgmobile = page.locator("#userNumber").evaluate("e => e.validationMessage").toString();
		System.out.println(msgemail);
		Assert.assertEquals(msgFirstname,"Please fill out this field.");
		Assert.assertEquals(msglastname, "Please fill out this field.");
		//Assert.assertEquals(msgemail, "Please fill out this field.");
		Assert.assertEquals(msgmobile, "Please fill out this field.");
		test.info("error submit empty form");	
	}

}
