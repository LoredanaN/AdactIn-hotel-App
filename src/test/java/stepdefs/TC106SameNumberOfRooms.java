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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TC106SameNumberOfRooms {
    WebDriver driver= new ChromeDriver();

    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("I go to the hotel page")
    public void homepageApplication(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
    }
    @When("I type my username")
    public void enterUsername(){
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");
    }
    @And("I type my password")
    public void enterPassword(){
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");
    }
    @And("I click log in button")
    public void loginButtonClick(){
        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @Then("I am now logged in")
    public void loggedInSuccessfully(){
        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());
    }
    @When("I select location 'Sydney'")
    public void selectLocation(){
        Select location=new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select hotel 'Hotel Creek'")
    public void selectHotel(){
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I select room type 'Standard'")
    public void selectRoom(){
        Select room=new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select number of-rooms '3'")
    public void selectNoOfRooms(){
        Select numbersOfRooms= new Select(driver.findElement(By.id("room_nos")));
        numbersOfRooms.selectByValue("3");
    }
    @And("I select checkin date")
    public void checkInDate(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+ft.format(nowDay));
    }
    @And("I select checkout date")
    public void checkOutDate(){
        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+ft.format(nowDay));

    }
    @And("I select number-of-adults")
    public void numberOfAdults(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
    }
    @And("I select number-of-children")
    public void numberOfChildren(){
        Select children=new Select(driver.findElement(By.id("child_room")));
        children.getFirstSelectedOption();
    }
    @And("I click the Search button")
    public void searchButtonClick(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @Then("The number of rooms in search hotel is the same as in select hotel")
    public void verifyNumberOfRooms(){
        WebElement numberOfRooms= driver.findElement(By.id("rooms_0"));
        Assert.assertTrue(numberOfRooms.getAttribute("value").contains("3"));

        driver.close();
    }
}
