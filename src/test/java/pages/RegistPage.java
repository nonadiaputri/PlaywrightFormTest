package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegistPage {
	
	private Page page;
	private final Locator fnameinput;
	private final Locator lnameinput;
	private final Locator emailinput;
	//private final Locator genderinput;
	private final Locator mobileinput;
	private final Locator submitBtn;
	
	public RegistPage(Page page) {
		this.page = page;
		fnameinput = page.locator("#firstName");
		lnameinput = page.locator("#lastName");
		emailinput = page.locator("#userEmail");
		mobileinput = page.locator("#userNumber");
		submitBtn = page.locator("#submit");
	}
	
	public void submitForm(String fname, String lname, String email, String gender, String mobile) {
		
		fnameinput.fill(fname);
		lnameinput.fill(lname);
		emailinput.fill(email);
		page.getByText(gender).click();
		mobileinput.fill(mobile);
		submitBtn.click();
		
	}
	
	public void submitEmptyForm() {
		submitBtn.click();
	}
	
	
	
	
	
	
	
	

}
