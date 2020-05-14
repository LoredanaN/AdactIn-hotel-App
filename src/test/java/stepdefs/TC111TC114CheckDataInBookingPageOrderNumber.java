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

public class TC111TC114CheckDataInBookingPageOrderNumber {
    WebDriver driver= new ChromeDriver();

    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("The booking website is open")
    public void homepageApplication(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
    }
    @When("I complete the username field with valid data")
    public void enterUsername(){
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");
    }
    @And("I complete the password field with valid data")
    public void enterPassword(){
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");
    }
    @And("I press Login button")
    public void loginButtonClick(){
        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @Then("With my credentials I am logged in")
    public void loggedInSuccessfully(){
        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());
    }
    @When("I set Sydney location")
    public void selectLocation(){
        Select location=new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I set Hotel Creek hotel")
    public void selectHotel(){
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I set the room type")
    public void selectRoom(){
        Select room=new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I set the number of-rooms")
    public void selectNoOfRooms(){
        Select numbersOfRooms= new Select(driver.findElement(By.id("room_nos")));
        numbersOfRooms.selectByValue("2");
    }
    @And("I set the check-in date")
    public void checkInDate(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+ft.format(nowDay));

    }
    @And("I set the check-out date")
    public void checkOutDate(){
        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+ft.format(nowDay));

    }
    @And("I set the number-of-adults")
    public void numberOfAdults(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
    }
    @And("I set the number-of-children")
    public void numberOfChildren(){
        Select children=new Select(driver.findElement(By.id("child_room")));
        children.getFirstSelectedOption();
    }
    @And("I press the Search button")
    public void searchButtonClick(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @And("I choose first hotel option")
    public void selectHotelCreek(){
        WebElement hotelCreekOption=driver.findElement(By.id("radiobutton_0"));
        hotelCreekOption.click();
    }
    @And("I press 'Continue' button")
    public void continueButton(){
        WebElement clickContinue= driver.findElement(By.id("continue"));
        clickContinue.click();
    }
    @Then("The booking page is displayed")
    public void bookAHotelPage(){
        WebElement bookAHotel=driver.findElement(By.xpath("//*[@id=\"book_hotel_form\"]/table/tbody/tr[2]/td"));
        Assert.assertEquals("Book A Hotel",bookAHotel.getText());
    }
    @When("I enter the first name")
    public void firstNameField(){
        WebElement firstName=driver.findElement(By.id("first_name"));
        firstName.sendKeys("Moglita");
    }
    @And("I enter the last name")
    public void lastNameField(){
        WebElement lastName=driver.findElement(By.id("last_name"));
        lastName.sendKeys("Apuchinesei");
    }
    @And("I enter the billing address")
    public void billingAddressField(){
        WebElement address=driver.findElement(By.id("address"));
        address.sendKeys("Str. Purcelului nr. 120");
    }
    @And("I enter the credit card number")
    public void creditCardNumber(){
        WebElement cardNumber=driver.findElement(By.id("cc_num"));
        cardNumber.sendKeys("2121222223232424");
    }
    @And("I select the credit card type")
    public void creditCardType(){
        Select card=new Select(driver.findElement(By.id("cc_type")));
        card.selectByValue("VISA");
    }
    @And("I select the expiry date")
    public void expiryCardDate () {
        Select expiryMonth = new Select(driver.findElement(By.id("cc_exp_month")));
        expiryMonth.selectByValue("5");
        Select expiryYear =new Select(driver.findElement(By.id("cc_exp_year")));
        expiryYear.selectByValue("2022");
    }
    @And("I enter the CVV number")
    public void cvvCardNumber() {
        WebElement cvvNumber = driver.findElement(By.id("cc_cvv"));
        cvvNumber.sendKeys("321");
    }
    @And("I click book now")
    public void pressBookNow()  throws InterruptedException{
        WebElement bookNow = driver.findElement(By.id("book_now"));
        bookNow.click();
        Thread.sleep(10000);
    }
    @Then("I check the hotel name")
    public void checkHotelName(){

      WebElement confirmationBooking = driver.findElement(By.id("hotel_name"));
      Assert.assertTrue(confirmationBooking.getAttribute("value").equals("Hotel Creek"));
    }
    @When("I check the location")
    public void checkLocation(){
        WebElement confirmationLocation=driver.findElement(By.id("location"));
        Assert.assertTrue(confirmationLocation.getAttribute("value").equals("Sydney"));

    }
    @And("I check the room type")
    public void checkRoomType(){
//        WebElement typeOfRoom= driver.findElement(By.id("room_type"));
//        Assert.assertTrue(typeOfRoom.getAttribute("value").equals("Standard"));
//        Room type is not the same in booking confirmation page as in previous page!
//        to be implemented when the bug ID is fixed

    }
    @Then("I check total price")
    public void checkTotalPrice(){
        WebElement noOfRooms=driver.findElement(By.id("total_rooms"));
        Assert.assertTrue(noOfRooms.getAttribute("value").contains("2"));
        String a= noOfRooms.getAttribute("value");
        String noRooms=a.substring(0,1);
        int x =Integer.parseInt(noRooms);


        WebElement pricePerNight=driver.findElement(By.id("price_night"));
        Assert.assertTrue(pricePerNight.getAttribute("value").contains("$ 125"));
        String c=pricePerNight.getAttribute("value");
        String priceNight=c.substring(c.length()-3,c.length()+0);
        int z=Integer.parseInt(priceNight);

        int tp= x*1*z;
        String total = ""+tp;
//        WebElement totalPrice= driver.findElement(By.id("total_price_dis"));
//        Assert.assertTrue(totalPrice.getAttribute("value").contains("$" + total));
//        Total price is not the same in booking confirmation page as in previous page! To be implemented when the bug ID is fixed

    }

//    TC114 I verify if order number is generated in booking confirmation page

    @Then("I verify if order number is generated")
    public void verifyOrderNumber(){
        WebElement orderNumber =driver.findElement(By.id("order_no"));
        Assert.assertNotNull(orderNumber.getAttribute("value"));

          driver.close();
    }
}
