package authentication;

import internal.GlobalVariable;
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint;
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase;
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData;
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject;
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject;

import com.kms.katalon.core.annotation.Keyword;
import com.kms.katalon.core.checkpoint.Checkpoint;
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords;
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords;
import com.kms.katalon.core.model.FailureHandling;
import com.kms.katalon.core.testcase.TestCase;
import com.kms.katalon.core.testdata.TestData;
import com.kms.katalon.core.testobject.TestObject;
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords;
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import cucumber.api.java.en.Given
import cucumber.api.java.en.And
import cucumber.api.java.en.When
import cucumber.api.java.en.Then


public class authentication {

	@Given ("the user launches the application")
	def the_user_launches_the_application() {
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl("https://app.myunisoft.fr/", FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForPageLoad(5)
		
	}
	
	@When ("He enters his (.*) and (.*)")
	def He_enters_his_email_and_password(String email, String password) {
		def champEmail =findTestObject('Object Repository/authentication/email')
		WebUI.mouseOver(champEmail)
		WebUI.click(champEmail)
		WebUI.setText(champEmail, email)
		WebUI.click(findTestObject('Object Repository/authentication/password'))
		WebUI.setText(findTestObject('Object Repository/authentication/password'), password)
		
		
	}
	
}