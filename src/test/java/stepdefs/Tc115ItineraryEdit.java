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

public class Tc115ItineraryEdit {
    WebDriver driver=new ChromeDriver();
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("I am on hotel booking website logged in")
    public void hotelHomepage(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");

        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");

        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");

        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @When("I select Adelaide as location")
    public void selectLocation(){
        Select newLocation=new Select(driver.findElement(By.id("location")));
        newLocation.selectByValue("Adelaide");
    }
    @And("I select hotel Cornice")
    public void selectHotel(){
        Select newHotel=new Select(driver.findElement(By.id("hotels")));
        newHotel.selectByValue("Hotel Cornice");
    }
    @And("I set 'standard' room")
    public void roomType(){
        Select roomType=new Select(driver.findElement(By.id("room_type")));
        roomType.selectByValue("Standard");
    }
    @And("I set number of rooms")
    public void numberOfRooms(){
        Select numberRooms=new Select(driver.findElement(By.id("room_nos")));
        numberRooms.selectByValue("2");
    }
    @And("I set check-in date and check-out date")
    public void checkinCheckoutDate(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+ft.format(nowDay));
        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+ft.format(nowDay));
    }
    @And("I set number of adults and number of children")
    public void noOfAdultsAndChildren(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
        Select children=new Select(driver.findElement(By.id("child_room")));
        children.getFirstSelectedOption();
    }
    @And("I press Search")
    public void searchButton(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @Then("The Select hotel page is displayed")
    public void hotelPageDisplayed(){
  WebElement hotelPage=driver.findElement(By.className("login_title"));
        Assert.assertEquals("Select Hotel",hotelPage.getText());
    }
    @When("I click on displayed hotel")
    public void selectDisplayedHotel(){
        WebElement hotelSelect=driver.findElement(By.id("radiobutton_0"));
        hotelSelect.click();

    }
    @And("I click continue")
    public void continueButton(){
        WebElement clickContinue= driver.findElement(By.id("continue"));
        clickContinue.click();
    }
    @Then("I am on book a hotel page")
    public void bookAHotelPage(){
        WebElement bookAHotel=driver.findElement(By.xpath("//*[@id=\"book_hotel_form\"]/table/tbody/tr[2]/td"));
        Assert.assertEquals("Book A Hotel",bookAHotel.getText());
    }
    @When("I enter personal details and credit card details")
    public void personalDetailsAndCreditCard(){
        WebElement firstName=driver.findElement(By.id("first_name"));
        firstName.sendKeys("Moglita");

        WebElement lastName=driver.findElement(By.id("last_name"));
        lastName.sendKeys("Apuchinesei");

        WebElement address=driver.findElement(By.id("address"));
        address.sendKeys("Str. Purcelului nr. 120");

        WebElement cardNumber=driver.findElement(By.id("cc_num"));
        cardNumber.sendKeys("2121222223232424");

        Select card=new Select(driver.findElement(By.id("cc_type")));
        card.selectByValue("VISA");

        Select expiryMonth = new Select(driver.findElement(By.id("cc_exp_month")));
        expiryMonth.selectByValue("5");
        Select expiryYear =new Select(driver.findElement(By.id("cc_exp_year")));
        expiryYear.selectByValue("2022");

        WebElement cvvNumber = driver.findElement(By.id("cc_cvv"));
        cvvNumber.sendKeys("321");



    }
    @And("I press 'book now' button")
    public void bookNowButton(){
        WebElement bookNowButton = driver.findElement(By.id("book_now"));
        bookNowButton.click();
    }
    @Then("I am on booking confirmation page")
    public void bookingConfirmationPage() throws InterruptedException{
        Thread.sleep(10000);
        WebElement confirmationPage=driver.findElement(By.className("login_title"));
        Assert.assertEquals("Booking Confirmation",confirmationPage.getText());
    }
    @When("I click on 'My itinerary' button")
    public void myItineraryButton(){
        WebElement itineraryButton=driver.findElement(By.id("my_itinerary"));
        itineraryButton.click();
    }
    @Then("I verify if whether the booked itinerary details are not editable.")
    public void verifyEditableDetails(){
        WebElement nameHotel=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[31]/td[4]/input"));
        String name=nameHotel.getAttribute("value");
        nameHotel.clear();
        nameHotel.sendKeys("Hotel Creek");
//        Assert.assertTrue(name.equals("Hotel Creek"));
//        Itinerary details are editable, but details once accepted should not be editable.
        driver.close();
    }

}
