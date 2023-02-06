package script;

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

public class script {

	@Given ("the user launches the application")
	def the_user_launches_the_application() {
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl("https://app.myunisoft.fr/", FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForPageLoad(10)
	}

	@And ("he enters his (.*) and (.*)")
	def he_enters_his_email_and_password(String email, String password) {
		def emailField =findTestObject('Object Repository/email')
		WebUI.click(emailField)
		WebUI.setText(emailField, email)

		def passwordField = findTestObject('Object Repository/password')
		WebUI.click(passwordField)
		WebUI.setText(passwordField, password)
	}

	@And ("he accepts the conditions")
	def he_accepts_the_conditions() {
		def cgu =findTestObject('Object Repository/generalConditions')
		WebUI.click(cgu)
	}

	@And ("he accepts the use of his data")
	def he_accepts_the_use_of_his_data() {
		def allowData =findTestObject('Object Repository/allowData')
		WebUI.click(allowData)
	}

	@When ("he clicks on connect")
	def he_clicks_on_connect() {
		def login =findTestObject('Object Repository/login')
		WebUI.click(login)
	}


	@When ("changing (.*) with (.*) using api")
	def changing_oldPassword_with_password_using_api(String oldPassword, String password) {

		//Creating a basic PUT Request
		HttpPut httpPut = new HttpPut("https://app.myunisoft.fr/api/user/password");

		//Adding headers to PUT HTTP Request
		httpPut.setHeader("Accept", "application/json");
		httpPut.setHeader("Content-type", "application/json");

		//Using an authorization token to access an API
		//If the code does not work because of the token, you must replace the current token with a new "myu-access-token" token (you can find it when you inspect the element and then go to the "network")
		httpPut.setHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJfZ3JvdXBfaWQiOjUzLCJwZXJzX3BoeXNpcXVlX2lkIjo0OSwicHJvZmlsVHlwZSI6IkNhYmluZXQiLCJwcm9maWxDb2RlIjoiY2FiaV9jb2xsYWJfY29tcHRhIiwicHJvZmlsSWQiOiI1IiwicHJvZmlsTmFtZSI6IkNvbGxhYm9yYXRldXIgY29tcHRhIiwiaXNQbGF0Zm9ybUFkbWluaXN0cmF0b3IiOmZhbHNlLCJpYXQiOjE2NzU2NzQ0MTQsImV4cCI6MTY3NTcxNzYxNH0.fX-wyzTIwsKO4q5dL_lYMtZ-ZvdH7cgsrKlsPO3SY1o");

		//Adding JSON Data to PUT request
		String json = "{\r\n" +
				" \"old_password\": \""+oldPassword+"\" ,\r\n"  +
				" \"new_password\": \""+password+"\" \r\n" +
				"}";

		System.out.println(json) ;

		//Sending JSON as request body in the PUT request
		StringEntity stringEntity = new StringEntity(json);
		httpPut.setEntity(stringEntity);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		//Sending a  PUT request via execute() Method
		HttpResponse response =  httpclient.execute(httpPut);
		System.out.println(response);

		//Checking that the status code equals 204
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
		WebUI.closeBrowser()
	}


	@And ("he replaces his (.*) with his (.*)")
	def he_replaces_his_newPassword_with_his_oldPassword(String password, String oldPassword) {
		def avatar =findTestObject('Object Repository/avatar')
		WebUI.click(avatar)

		def btnNewPassword =findTestObject('Object Repository/btn_newPassword')
		WebUI.click(btnNewPassword)

		def currentPassword =findTestObject('Object Repository/CurrentPassword')
		WebUI.click(currentPassword)
		WebUI.setText(currentPassword, password)

		def nouveauPassword =findTestObject('Object Repository/newPassword')
		WebUI.click(nouveauPassword)
		WebUI.setText(nouveauPassword, oldPassword)

		def confirmNewPassword =findTestObject('Object Repository/confirmNewPassword')
		WebUI.click(confirmNewPassword)
		WebUI.setText(confirmNewPassword, oldPassword)

		def tovalidate =findTestObject('Object Repository/toValidate')
		WebUI.click(tovalidate)
	}


	@Then("Password reset successfully")
	def Password_reset_successfully() {
		def checkChange =findTestObject('Object Repository/checkChange')
		WebUI.verifyElementVisible(checkChange)

		WebUI.closeBrowser()

	}

}
