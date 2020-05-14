package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tc101verifyLogin {
    WebDriver driver=new ChromeDriver();

    @Given("I go to the hotel application page")
    public void verifyLogin (){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
    }
    @When("I type valid username")
    public void validUsername (){
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");

    }
    @And("I type a valid password")
    public void validPassword(){
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");
    }
    @And("I click login button")
    public void clickLogin(){
        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @Then("I am successfully logged in")
    public void successfullyLoggedIn(){
        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());
             driver.close();
    }
}
