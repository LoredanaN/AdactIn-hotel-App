package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tc117Tc119Tc120 {
    WebDriver driver=new ChromeDriver();
    Date today=new Date();
    SimpleDateFormat my= new SimpleDateFormat("/MM/YYYY");
    Calendar cal= Calendar.getInstance();

    String orderIdValue;

    @Given("The website application is opened and I am logged in.")
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
    @When("I am on Search hotel page I select location and the hotel")
    public void selectHotelAndLocation(){
        Select newLocation=new Select(driver.findElement(By.id("location")));
        newLocation.selectByValue("Sydney");
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I choose room type and number of rooms")
    public void typeOfRoomAndNumberOfRooms(){
        Select roomType=new Select(driver.findElement(By.id("room_type")));
        roomType.selectByValue("Standard");

        Select numberRooms=new Select(driver.findElement(By.id("room_nos")));
        numberRooms.selectByValue("2");}
        @And("I set the date for check-in and check-out")
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
        @And("I choose number of adults and children and I click Search button")
        public void adultsAndChildrenNumber(){
            Select adults=new Select(driver.findElement(By.id("adult_room")));
            adults.selectByValue("1");

            Select children=new Select(driver.findElement(By.id("child_room")));
            children.getFirstSelectedOption();

                WebElement search=driver.findElement(By.id("Submit"));
                search.click();
        }
        @Then("The 'Select hotel' page is displayed")
         public void selectHotelPage(){
        WebElement selectHotel=driver.findElement(By.className("login_title"));
         Assert.assertEquals("Select Hotel",selectHotel.getText());
        }
        @When("I select the radio button besides the hotel entry and I click continue")
        public void selectHotelAndPressContinue(){
            WebElement hotelSelect=driver.findElement(By.id("radiobutton_0"));
            hotelSelect.click();

            WebElement clickContinue= driver.findElement(By.id("continue"));
            clickContinue.click();
        }
        @And("I enter the personal details on Book a hotel page and I click Book now button")
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
            bookNowButton.click();}
            @Then("The confirmation page is loaded")
            public void bookingConfirmationPage() throws InterruptedException{
                Thread.sleep(10000);
                WebElement confirmationPage=driver.findElement(By.className("login_title"));
                Assert.assertEquals("Booking Confirmation",confirmationPage.getText());
            }
            @When("I press My Itinerary button")
            public void pressItineraryButton(){
            WebElement itineraryButton=driver.findElement(By.id("my_itinerary"));
              itineraryButton.click();}
              @And("I am on Booked itinerary page")
               public void bookedItineraryPage(){
        WebElement itineraryPage=driver.findElement(By.className("login_title"));
        Assert.assertEquals("Booked Itinerary",itineraryPage.getText());
              }
              @And("I type on 'Search Order Id' field an order id")
              public void searchOrderIdField(){
        WebElement orderId=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"));
        WebElement searchOrderId = driver.findElement(By.id("order_id_text"));
        searchOrderId.sendKeys(orderId.getAttribute("value"));
        orderIdValue=orderId.getAttribute("value");
              }
              @And("I click 'Go' button")
              public void clickGoButton(){
        WebElement goButton=driver.findElement(By.id("search_hotel_id"));
        goButton.click();
              }
              @Then("The order id searched is displayed with the relevant details.")
             public void orderSearched(){
        WebElement order=driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"));
        Assert.assertTrue(order.getAttribute("value").equals(orderIdValue));
              }


//              TC119 I verify that the order gets cancelled after click on Cancel order number link
    @When("I click on Cancel <Order Number>")
    public void cancelOrderNumber(){
        WebElement cancelNumber= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/input"));
        cancelNumber.click();
    }
    @And("I click Yes on pop-up which asks where to cancel order or not")
    public void confirmCancelAction(){
        Alert confirmation=driver.switchTo().alert();
        confirmation.accept();
    }
    @Then("I verify that order number is cancelled and no longer exists in Booked Itinerary page")
    public void verifyCancelledOrder(){
        WebElement cancelledOrder= driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"));
        Assert.assertFalse(cancelledOrder.getAttribute("value").equals(orderIdValue));
        driver.close();
    }

}
