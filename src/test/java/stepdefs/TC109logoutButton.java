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

public class TC109logoutButton {
    WebDriver driver= new ChromeDriver();


    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("The hotel booking website is open")
    public void homepageApplication(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
    }
    @When("I complete username field")
    public void enterUsername(){
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");
    }
    @And("I complete password field")
    public void enterPassword(){
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");

    }
    @And("I press 'Login' button")
    public void loginButtonClick(){
        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @Then("My credentials are verified and I am logged in")
    public void loggedInSuccessfully(){
        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());
    }
    @When("I set 'Sydney' location")
    public void selectLocation(){
        Select location=new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I set 'Hotel Creek' hotel")
    public void selectHotel(){
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I set the first room type")
    public void selectRoom(){
        Select room=new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I set '2' as number of-rooms")
    public void selectNoOfRooms(){
        Select numbersOfRooms= new Select(driver.findElement(By.id("room_nos")));
        numbersOfRooms.selectByValue("2");
    }
    @And("I set today as check-in date")
    public void checkInDate(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+ft.format(nowDay));

    }
    @And("I set tomorrow as check-out date")
    public void checkOutDate(){
        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+ft.format(nowDay));

    }
    @And("I set '1' as number-of-adults")
    public void numberOfAdults(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
    }
    @And("I set '0' as number-of-children")
    public void numberOfChildren(){
        Select children=new Select(driver.findElement(By.id("child_room")));
        children.getFirstSelectedOption();
    }
    @And("I press the 'Search' button")
    public void searchButtonClick(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @And("I select displayed hotel")
    public void selectHotelCreek(){
        WebElement hotelCreekOption=driver.findElement(By.id("radiobutton_0"));
        hotelCreekOption.click();
    }
    @And("I press continue button")
    public void continueButton(){
        WebElement clickContinue= driver.findElement(By.id("continue"));
        clickContinue.click();
    }
    @Then("I am redirected to booking page")
    public void bookAHotelPage(){
        WebElement bookAHotel=driver.findElement(By.xpath("//*[@id=\"book_hotel_form\"]/table/tbody/tr[2]/td"));
        Assert.assertEquals("Book A Hotel",bookAHotel.getText());
    }
    @When("I enter first name")
    public void firstNameField(){
        WebElement firstName=driver.findElement(By.id("first_name"));
        firstName.sendKeys("Moglita");
    }
    @And("I enter second name")
    public void lastNameField(){
        WebElement lastName=driver.findElement(By.id("last_name"));
        lastName.sendKeys("Apuchinesei");
    }
    @And("I enter billing address")
    public void billingAddressField(){
       WebElement address=driver.findElement(By.id("address"));
       address.sendKeys("Str. Purcelului nr. 120");
    }
    @And("I enter credit card No.")
    public void creditCardNumber(){
        WebElement cardNumber=driver.findElement(By.id("cc_num"));
        cardNumber.sendKeys("2121222223232424");
    }
    @And("I select credit card type")
    public void creditCardType(){
        Select card=new Select(driver.findElement(By.id("cc_type")));
        card.selectByValue("VISA");
    }
    @And("I select expiry date")
    public void expiryCardDate () {
        Select expiryMonth = new Select(driver.findElement(By.id("cc_exp_month")));
        expiryMonth.selectByValue("5");
        Select expiryYear =new Select(driver.findElement(By.id("cc_exp_year")));
        expiryYear.selectByValue("2022");
    }
    @And("I enter CVV number")
    public void cvvCardNumber() {
        WebElement cvvNumber = driver.findElement(By.id("cc_cvv"));
        cvvNumber.sendKeys("321");
    }
    @And("I press book now")
    public void pressBookNow(){
        WebElement bookNowButton = driver.findElement(By.id("book_now"));
        bookNowButton.click();
    }
    @Then("I am redirected to booking confirmation page")
    public void confirmationBookingPage() throws InterruptedException {
        Thread.sleep(10000);
        WebElement confirmationPage=driver.findElement(By.className("login_title"));
        Assert.assertEquals("Booking Confirmation",confirmationPage.getText());
    }
    @When("I click logout button")
    public void logoutButton(){
        WebElement logout=driver.findElement(By.id("logout"));
        logout.click();
    }
    @Then("I am logout from the application")
    public void logoutFromTheApplication() throws InterruptedException {
        Thread.sleep(10000);
        WebElement logoutApplication=driver.findElement(By.className("reg_success"));
        Assert.assertEquals("You have successfully logged out. Click here to login again",logoutApplication.getText());

        driver.close();
    }
}
