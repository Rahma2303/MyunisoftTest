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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import cucumber.api.java.en.Given
import cucumber.api.java.en.And
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import cucumber.api.java.en.And




import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.io.EOFException;
import java.io.InvalidObjectException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.kms.katalon.core.testobject.RequestObject;
import com.kms.katalon.core.testobject.ResponseObject;
import com.kms.katalon.core.testobject.ConditionType;
import com.kms.katalon.core.testobject.TestObjectProperty;
import com.kms.katalon.core.util.KeywordUtil;

public class authentication {

	@Given ("the user launches the application")
	def the_user_launches_the_application() {
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl("https://app.myunisoft.fr/", FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForPageLoad(10)
	}

	@When ("he enters his (.*) and (.*)")
	def he_enters_his_email_and_password(String email, String password) {
		def emailField =findTestObject('Object Repository/email')
		WebUI.click(emailField)
		WebUI.setText(emailField, email)
		def passwordField = findTestObject('Object Repository/password')
		WebUI.click(passwordField)
		WebUI.setText(passwordField, password)
	}

	@When ("he accepts the conditions")
	def he_accepts_the_conditions() {
		def cgu =findTestObject('Object Repository/generalConditions')
		WebUI.click(cgu)
	}

	@When ("he accepts the use of his data")
	def he_accepts_the_use_of_his_data() {
		def cgu =findTestObject('Object Repository/allowData')
		WebUI.click(cgu)
	}

	@When ("he clicks on connect")
	def he_clicks_on_connect() {
		def login =findTestObject('Object Repository/login')
		WebUI.click(login)
	}

	
	@When ("he change (.*) with (.*)")
	def he_change_oldPassword_par_newPassword(String oldPassword, String newPassword) {

		HttpPut httpPut = new HttpPut("https://app.myunisoft.fr/api/user/password");

		httpPut.setHeader("Accept", "application/json");
		httpPut.setHeader("Content-type", "application/json");
		httpPut.setHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJfZ3JvdXBfaWQiOjUzLCJwZXJzX3BoeXNpcXVlX2lkIjo0OSwicHJvZmlsVHlwZSI6IkNhYmluZXQiLCJwcm9maWxDb2RlIjoiY2FiaV9jb2xsYWJfY29tcHRhIiwicHJvZmlsSWQiOiI1IiwicHJvZmlsTmFtZSI6IkNvbGxhYm9yYXRldXIgY29tcHRhIiwiaXNQbGF0Zm9ybUFkbWluaXN0cmF0b3IiOmZhbHNlLCJpYXQiOjE2NzU1MDY4MzgsImV4cCI6MTY3NTU1MDAzOH0.sw-ZHRJ-MKeBx07Cqhk8bEWJRWSduOWWGR_72Ac5Ebc");
		String json = "{\r\n" +
				" \"old_password\": \""+oldPassword+"\" ,\r\n"  +
				" \"new_password\": \""+newPassword+"\" \r\n" +
				"}";

		System.out.println(json) ;

		StringEntity stringEntity = new StringEntity(json);
		httpPut.setEntity(stringEntity);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpResponse response =  httpclient.execute(httpPut);
		System.out.println(response);
		
		if(response.getStatusLine().getStatusCode() != 204) {
			throw new Exception ('error status code')
			
		}
		
	}

	@Then ("he is logged into his account")
	def he_is_logged_into_his_account() {
		def logoAccount =findTestObject('Object Repository/avatar')
		WebUI.verifyElementVisible(logoAccount)

		def homePage =findTestObject('Object Repository/homePage')
		WebUI.verifyElementVisible(homePage)
	}
	
	@Then ("he logs in with (.*) and (.*)")
	def he_logs_in_with(String email, String newPassword) {
		def emailField =findTestObject('Object Repository/email')
		WebUI.click(emailField)
		WebUI.setText(emailField, email)
		def passwordField = findTestObject('Object Repository/password')
		WebUI.click(passwordField)
		WebUI.setText(passwordField, newPassword)
		def cgu =findTestObject('Object Repository/generalConditions')
		WebUI.click(cgu)
		def allowdata =findTestObject('Object Repository/allowData')
		WebUI.click(allowdata)
		def login =findTestObject('Object Repository/login')
		WebUI.click(login)
	}
	
	@And ("he replaces his (.*) with his (.*)")
	def he_replaces_his_newPassword_with_his_oldPassword(String newPassword, String oldPassword) {
		def avatar =findTestObject('Object Repository/avatar')
		WebUI.click(avatar)
		def btnNewPassword =findTestObject('Object Repository/btn_newPassword')
		WebUI.click(btnNewPassword)
		def currentPassword =findTestObject('Object Repository/CurrentPassword')
		WebUI.click(currentPassword)
		WebUI.setText(currentPassword, newPassword)
		def nouveauPassword =findTestObject('Object Repository/newPassword')
		WebUI.click(nouveauPassword)
		WebUI.setText(nouveauPassword, oldPassword)
		def confirmNewPassword =findTestObject('Object Repository/confirmNewPassword')
		WebUI.click(confirmNewPassword)
		WebUI.setText(confirmNewPassword, oldPassword)
		def tovalidate =findTestObject('Object Repository/toValidate')
		WebUI.click(tovalidate)
	}
	
	
	@Then("the change is verified")
	def the_change_is_verified() {
		def checkChange =findTestObject('Object Repository/checkChange')
		WebUI.verifyElementVisible(checkChange)
		WebUI.closeBrowser()
	
	}

}
