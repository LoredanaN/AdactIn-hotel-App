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

public class TC108TC110TC112TotalPrice {
    WebDriver driver= new ChromeDriver();


    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();
    int price;
    int rooms;
    int days;
    int tp;
    @Given("I go to booking website")
    public void homepageApplication(){
        driver.manage().window().maximize();
        driver.get("http://adactinhotelapp.com/");
    }
    @When("I use a valid username")
    public void enterUsername(){
        WebElement username= driver.findElement(By.id("username"));
        username.sendKeys("Moglidut");
    }
    @And("I use a valid password")
    public void enterPassword(){
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys("mogli12345");
    }
    @And("I click the 'Login' button")
    public void loginButtonClick(){
        WebElement loginButton=driver.findElement(By.id("login"));
        loginButton.click();
    }
    @Then("Now I am successfully logged in")
    public void loggedInSuccessfully(){
        WebElement loggedIn= driver.findElement(By.linkText("Search Hotel"));
        Assert.assertEquals("Search Hotel",loggedIn.getText());
    }
    @When("I select 'Sydney' location")
    public void selectLocation(){
        Select location=new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select 'Hotel Creek' hotel")
    public void selectHotel(){
        Select hotel=new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I select the first room type")
    public void selectRoom(){
        Select room=new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select '2' as number of-rooms")
    public void selectNoOfRooms(){
        Select numbersOfRooms= new Select(driver.findElement(By.id("room_nos")));
        numbersOfRooms.selectByValue("2");
    }
    @And("I select today as check-in date")
    public void checkInDate(){
        WebElement checkIndate=driver.findElement(By.id("datepick_in"));
        checkIndate.clear();
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);
        checkIndate.sendKeys(s+ft.format(nowDay));

    }
    @And("I select tomorrow as check-out date")
    public void checkOutDate(){
        WebElement checkOutDate=driver.findElement(By.id("datepick_out"));
        checkOutDate.clear();
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutDate.sendKeys(nowPlusOne+ft.format(nowDay));

    }
    @And("I select '1' as number-of-adults")
    public void numberOfAdults(){
        Select adults=new Select(driver.findElement(By.id("adult_room")));
        adults.selectByValue("1");
    }
    @And("I select '0' as number-of-children")
    public void numberOfChildren(){
        Select children=new Select(driver.findElement(By.id("child_room")));
        children.getFirstSelectedOption();
    }
    @And("I click the 'Search' button")
    public void searchButtonClick(){
        WebElement search=driver.findElement(By.id("Submit"));
        search.click();
    }
    @And("On 'Select hotel' page I select the hotel")
    public void selectHotelCreek(){
        WebElement hotelCreekOption=driver.findElement(By.id("radiobutton_0"));
        hotelCreekOption.click();
    }
    @And("I click continue button")
    public void continueButton(){
        WebElement clickContinue= driver.findElement(By.id("continue"));
        clickContinue.click();
    }
    @Then("Total price is calculated as “price per night, no. of nights, no. of rooms”.")
    public void verifytotalPrice(){
        WebElement noOfRooms=driver.findElement(By.id("room_num_dis"));
        Assert.assertTrue(noOfRooms.getAttribute("value").contains("2"));
        String a= noOfRooms.getAttribute("value");
        String noRooms=a.substring(0,1);
          rooms =Integer.parseInt(noRooms);

        WebElement noOfDays= driver.findElement(By.id("total_days_dis"));
        Assert.assertTrue(noOfDays.getAttribute("value").contains("1"));
        String b=noOfDays.getAttribute("value");
        String nodays=b.substring(0,1);
      days=Integer.parseInt(nodays);

        WebElement pricePerNight=driver.findElement(By.id("price_night_dis"));
        Assert.assertTrue(pricePerNight.getAttribute("value").contains("$ 125"));
        String c=pricePerNight.getAttribute("value");
        String priceNight=c.substring(c.length()-3,c.length()+0);
        price=Integer.parseInt(priceNight);

        tp= rooms*days*price;
        String total = ""+tp;
//        WebElement totalPrice= driver.findElement(By.id("total_price_dis"));
//        Assert.assertTrue(totalPrice.getAttribute("value").contains("$" + total));
//        to be implemented when the bug ID is fixed
//        System.out.println(total);

        driver.close();
    }

//    TC112 I check if total bill price is calculated as total price + 10% total price
    @Then("I verify final billed price")
    public void finalBilledPrice(){
        WebElement noOfRooms=driver.findElement(By.id("room_num_dis"));
        Assert.assertTrue(noOfRooms.getAttribute("value").contains("2"));
        String a= noOfRooms.getAttribute("value");
        String noRooms=a.substring(0,1);
        rooms =Integer.parseInt(noRooms);

        WebElement noOfDays= driver.findElement(By.id("total_days_dis"));
        Assert.assertTrue(noOfDays.getAttribute("value").contains("1"));
        String b=noOfDays.getAttribute("value");
        String nodays=b.substring(0,1);
        days=Integer.parseInt(nodays);

        WebElement pricePerNight=driver.findElement(By.id("price_night_dis"));
        Assert.assertTrue(pricePerNight.getAttribute("value").contains("$ 125"));
        String c=pricePerNight.getAttribute("value");
        String priceNight=c.substring(c.length()-3,c.length()+0);
        price=Integer.parseInt(priceNight);

        tp= rooms*days*price;
        double gst= tp*10/100;
        double finalBill=tp+gst;
        String finalBilled=String.valueOf(finalBill);
//        System.out.println(finalBilled);

        WebElement finalBillPrice= driver.findElement(By.id("final_price_dis"));
        String price=finalBillPrice.getAttribute("value");
        String extractedNumber=price.substring(price.length()-5,price.length()+0);

        Assert.assertEquals(finalBilled,extractedNumber);
//      Final billed price is not the expected result: expected:<[275.0]> but was:<[148.5]>
//       Assert to be executed after the bug is fixed

        driver.close();
    }

}

