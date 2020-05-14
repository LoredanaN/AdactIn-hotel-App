package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en_scouse.An;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Tc103DateInThePast {
    WebDriver driver= new ChromeDriver();
@Given("I am on the website page and I am logged in")
    public void loggedInOnWebsite(){
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
@When("I select the location")
    public void locationSelect(){
   Select location= new Select(driver.findElement(By.id("location")));
    location.selectByValue("Sydney");
}
@And("I select the hotel")
    public void hotelSelect(){
    Select hotel=new Select(driver.findElement(By.id("hotels")));
    hotel.selectByValue("Hotel Creek");
}
@And("I select the room type")
    public void roomType(){
    Select room=new Select(driver.findElement(By.id("room_type")));
    room.selectByValue("Standard");
}
@And("I select the numbers of rooms")
    public void numbersOfRooms (){
    Select numbers=new Select(driver.findElement(By.id("room_nos")));
    numbers.selectByValue("1");
}
@And("I type check-out date and check-in date from the past")
    public void dateFromPast(){
    WebElement checkInDate=driver.findElement(By.id("datepick_in"));
    checkInDate.clear();
    checkInDate.sendKeys("17/04/2020");

    WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
    checkOutDate.clear();
    checkOutDate.sendKeys("19/04/2020");

    WebElement searchButton=driver.findElement(By.id("Submit"));
    searchButton.click();
}
@Then("An error message is displayed")
    public void verifyErrorMessage() {
//to be implemented when the bug ID is fixed
    driver.close();
}
}

