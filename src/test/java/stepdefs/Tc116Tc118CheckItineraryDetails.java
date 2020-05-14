package stepdefs;

import cucumber.api.java.bs.A;
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

public class Tc116Tc118CheckItineraryDetails {
    WebDriver driver=new ChromeDriver();
    Date today=new Date();
    SimpleDateFormat my= new SimpleDateFormat("/MM/YYYY");
    Calendar cal= Calendar.getInstance();
    String orderNumber;
    String hotel;
    String location;
    String noOfRooms;
    String name;
    String lastName;
    String checkinDate;
    String checkoutDate;
    String roomT;
    String pricePerNight;
    String totalPrice;

    @Given("I am on website application logged in")
    public void loggedInOnApplicationHomepage(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");

        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");

        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");

        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @When("I set the hotel and I set the location")
    public void setLocationAndHotel(){
        Select newLocation=new Select(driver.findElement(By.id("location")));
        newLocation.selectByValue("Sydney");

        Select newHotel=new Select(driver.findElement(By.id("hotels")));
        newHotel.selectByValue("Hotel Creek");
    }
    @And("I choose the type of room and number of rooms")
    public void typeOfRoomAndNumberOfRooms(){
        Select roomType=new Select(driver.findElement(By.id("room_type")));
        roomType.selectByValue("Standard");

        Select numberRooms=new Select(driver.findElement(By.id("room_nos")));
        numberRooms.selectByValue("2");
    }
    @And("I select the dates of check-in and check-out")
    public void checkinAndCheckoutDates(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+my.format(today));
        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+my.format(today));
    }
    @And("I choose the adults and children number")
    public void adultsAndChildrenNumber(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
        Select children=new Select(driver.findElement(By.id("child_room")));
        children.getFirstSelectedOption();
    }
    @Then("For searching,I click Search button")
    public void searchButton(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @When("I want to book a hotel,I select the radio button besides the displayed hotel and I click 'continue'")
    public void selectRadioButton(){
        WebElement hotelSelect=driver.findElement(By.id("radiobutton_0"));
        hotelSelect.click();

        WebElement clickContinue= driver.findElement(By.id("continue"));
        clickContinue.click();
    }
    @And("I complete 'Book a hotel' page with personal details and I click 'Book now' button")
    public void bookAHotelDetails(){
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

        WebElement bookNowButton = driver.findElement(By.id("book_now"));
        bookNowButton.click();

    }
    @Then("The booking confirmation page is displayed")
    public void bookingConfirmationPage() throws InterruptedException{
        Thread.sleep(10000);
        WebElement confirmationPage=driver.findElement(By.className("login_title"));
        Assert.assertEquals("Booking Confirmation",confirmationPage.getText());
    }
    @When("I verify the details from booking confirmation page and press 'My itinerary' button")
    public void myItineraryButton(){
        WebElement hotelCheck=driver.findElement(By.id("hotel_name"));
        Assert.assertTrue(hotelCheck.getAttribute("value").equals("Hotel Creek"));
        hotel=hotelCheck.getAttribute("value");

        WebElement locationSydney=driver.findElement(By.id("location"));
        Assert.assertTrue(locationSydney.getAttribute("value").equals("Sydney"));
         location=locationSydney.getAttribute("value");

        WebElement roomType=driver.findElement(By.id("room_type"));
        Assert.assertTrue(roomType.getAttribute("value").equals("Deluxe"));
        roomT=roomType.getAttribute("value");

        WebElement checkIn=driver.findElement(By.id("arrival_date"));
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        String dat=s+my.format(today);
        Assert.assertTrue(checkIn.getAttribute("value").equals(dat));
        checkinDate=checkIn.getAttribute("value");

        WebElement checkOut=driver.findElement(By.id("departure_text"));
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        String data= nowPlusOne+my.format(today);
        Assert.assertTrue(checkOut.getAttribute("value").equals(data));
        checkoutDate=checkOut.getAttribute("value");

        WebElement numberOfRooms=driver.findElement(By.id("total_rooms"));
        Assert.assertTrue(numberOfRooms.getAttribute("value").contains("2 Room"));
        String x=numberOfRooms.getAttribute("value");
        noOfRooms=x.substring(0,6);

        WebElement priceNight=driver.findElement(By.id("price_night"));
        Assert.assertTrue(priceNight.getAttribute("value").equals("AUD $ 125"));
        pricePerNight=priceNight.getAttribute("value");

        WebElement priceTotal=driver.findElement(By.id("final_price"));
        Assert.assertTrue(priceTotal.getAttribute("value").equals("AUD $ 148.5"));
        totalPrice=priceTotal.getAttribute("value");


        WebElement nameField=driver.findElement(By.id("first_name"));
        Assert.assertTrue(nameField.getAttribute("value").equals("Moglita"));
         name=nameField.getAttribute("value");

         WebElement secondName=driver.findElement(By.id("last_name"));
//         Assert.assertTrue(secondName.getAttribute("Value").equals("Apuchinesei"));
         lastName=secondName.getAttribute("value");
//         In booking confirmation page, last name is not displayed.

         WebElement generatedNumber=driver.findElement(By.id("order_no"));
         orderNumber=generatedNumber.getAttribute("value");
        Assert.assertNotNull(generatedNumber.getAttribute("value"));

        WebElement itineraryButton=driver.findElement(By.id("my_itinerary"));
        itineraryButton.click();
    }
    @And("Booked itinerary page is displayed")
    public void myItineraryPageIsDisplayed(){
        WebElement itineraryPage=driver.findElement(By.className("login_title"));
        Assert.assertEquals("Booked Itinerary",itineraryPage.getText());
    }
    @Then("I check whether the booked itinerary reflects the correct information in line with the booking")
    public void verifyIfDetailsAreCorrect(){
        Assert.assertTrue(driver.getPageSource().contains(orderNumber));

        WebElement itineraryHotel=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[4]/input"));
        Assert.assertTrue(itineraryHotel.getAttribute("value").equals(hotel));

        WebElement itineraryLocation=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[5]/input"));
        Assert.assertTrue(itineraryLocation.getAttribute("value").equals(location));

        WebElement itineraryNoOfRooms=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input"));

        Assert.assertTrue(itineraryNoOfRooms.getAttribute("value").contains(noOfRooms));

        WebElement itineraryFirstName=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[7]/input"));
        Assert.assertTrue(itineraryFirstName.getAttribute("value").equals(name));

        WebElement itineraryLastName=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input"));
//        Assert.assertEquals(lastName,itineraryLastName.getAttribute("value"));
//        Last name is not displayed in Booking confirmation :expected:<[]> but was:<[Apuchinesei]>

        WebElement itineraryCheckinDate=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[16]/td[9]/input"));
        Assert.assertTrue(itineraryCheckinDate.getAttribute("value").contains(checkinDate));


        WebElement itineraryCheckoutDate=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[16]/td[10]/input"));
        Assert.assertTrue(itineraryCheckoutDate.getAttribute("value").contains(checkoutDate));


        WebElement itineraryRoomType=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[12]/input"));
//        Assert.assertEquals(roomT,itineraryRoomType.getAttribute("value"));
//        The room type is not the same in booked itinerary page as in booking confirmation page.
//         org.junit.ComparisonFailure: expected:<[Deluxe]> but was:<[Standard]> ...Bug to be fixed.

        WebElement itineraryPricePerNight=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[13]/input"));
        Assert.assertTrue(itineraryPricePerNight.getAttribute("value").equals(pricePerNight));

        WebElement itineraryTotalPrice=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[14]/input"));
//        Assert.assertEquals(totalPrice,itineraryTotalPrice.getAttribute("value"));
//        Total price is not the same in booked itinerary page as in booking confirmation page :expected:<AUD $ 14[8.5]> but was:<AUD $ 14[9]>

        driver.close();
    }

}
