package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Tc102CheckOutFieldTest {
    WebDriver driver=new ChromeDriver();

    @Given("I navigate to the application homepage")
    public void loginButton (){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
    }
    @When("I enter the username")
    public void validUsername (){
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");

    }
    @And("I enter the password")
    public void validPassword(){
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");
    }
    @And("I click login")
    public void clickLogin(){
        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @Then("I am logged in")
    public void successfullyLoggedIn(){
        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());
    }
    @When("I select a location")
    public void selectLocation(){
        Select location=new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select a hotel")
    public void selectHotel(){
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I select a room type")
    public void selectRoomType(){
        Select room=new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select no-of-rooms")
    public void selectNumbersOfRooms(){
        Select numbersOfRooms= new Select(driver.findElement(By.id("room_nos")));
        numbersOfRooms.selectByValue("1");

    }
    @And("I enter check-in date later than the check-out date")
    public void checkOutDateLater(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        checkIndate.sendKeys("29/04/2020");

        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        checkOutDate.sendKeys("27/04/2020");

        WebElement clickSearchButton=driver.findElement(By.id("Submit"));
        clickSearchButton.click();
    }
    @Then("Verify that system gives an error")
    public void verifySystemErrorMessage(){
        WebElement errorMessage=driver.findElement(By.id("checkin_span"));
        Assert.assertEquals("Check-In Date shall be before than Check-Out Date",errorMessage.getText());
        driver.close();
    }
}

