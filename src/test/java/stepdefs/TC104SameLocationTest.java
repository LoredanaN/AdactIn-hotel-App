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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TC104SameLocationTest {
    WebDriver driver=new ChromeDriver();

    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("I am on the website hotel page logged in")
    public void loggedInHomepage(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");

        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");

        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();

        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());

    }
    @When("I choose the location")
    public void chooseLocation(){
        Select location=new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I choose the hotel")
    public void chooseHotel(){
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I choose the room type")
    public void chooseRoomType(){
        Select room=new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I choose the numbers of rooms")
    public void chooseNoRooms(){
        Select numbersOfRooms= new Select(driver.findElement(By.id("room_nos")));
        numbersOfRooms.selectByValue("1");
    }
    @And("I set the check-in date and check-out date")
    public void setCheckInAndCheckoutDate(){

        //Present Date
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+ft.format(nowDay));

        //Manipulate date
        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+ft.format(nowDay));

    }
    @And("I select number of adults")
    public void numberOfAdults(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
    }
    @And("I select the number of children")
    public void numberOfChildren(){
        Select children=new Select(driver.findElement(By.id("child_room")));
//        children.selectByValue("0");
//     to be implemented when the "id-ul bug-ului" is fixed
    }
    @And("I click on Search button")
    public void searchButton(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @Then("The location displayed in Select hotel is the same as the location in Search hotel")
    public void sameLocation(){
        WebElement verifyLocations=driver.findElement(By.id("location_0"));
        Assert.assertTrue(verifyLocations.getAttribute("value").equals("Sydney"));
        driver.close();
    }
}
