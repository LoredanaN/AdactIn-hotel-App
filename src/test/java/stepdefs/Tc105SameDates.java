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

public class Tc105SameDates {
    WebDriver driver= new ChromeDriver();

    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("I am to the hotel application page")
    public void goHomePage(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
    }
    @When("I enter my username")
    public void enterUsername(){
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");
    }
    @And("I enter my password")
    public void enterPassword(){
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");
    }
    @And("I click the login button")
            public void loginButtonClick(){
    WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @Then("I am now successfully logged in")
    public void loggedInSuccessfully(){
        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());
    }
    @When("I select location Sydney")
    public void selectLocation(){
        Select location=new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select hotel Hotel Creek")
    public void selectHotel(){
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I select room type Standard")
    public void selectRoom(){
        Select room=new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select number of-rooms 1")
    public void selectNoOfRooms(){
        Select numbersOfRooms= new Select(driver.findElement(By.id("room_nos")));
        numbersOfRooms.selectByValue("1");
    }
    @And("I select check-in date")
    public void checkInDate(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+ft.format(nowDay));

    }
    @And("I select check-out date")
    public void checkOutDate(){

        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+ft.format(nowDay));

    }
    @And("I select no-of-adults")
    public void noOfAdults(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
    }
    @And("I select no-of-children")
    public void noOfChildren(){
        Select children=new Select(driver.findElement(By.id("child_room")));
        children.getFirstSelectedOption();
    }
    @And("I click Search button")
    public void searchButtonClick(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @Then("The check-in date and check-out date in search hotel are the same as in select hotel")
    public void verifyDates(){
        WebElement checkInDate= driver.findElement(By.id("arr_date_0"));
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        String dat=s+ft.format(nowDay);
        Assert.assertTrue(checkInDate.getAttribute("value").equals(dat));

        WebElement checkOutDate=driver.findElement(By.id("dep_date_0"));
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        String data= nowPlusOne+ft.format(nowDay);
//        System.out.println(data);
        Assert.assertTrue(checkOutDate.getAttribute("value").equals(data));

        driver.close();
    }
}
