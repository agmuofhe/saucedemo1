package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;


public class addToCartStepDef {
    WebDriver driver;

    @Before
    public void setup(){
        ChromeOptions options= new ChromeOptions();
        String osName=System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")){
            System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/windows/chromedriver.exe");
            System.out.println("windows");
        }else if (osName.contains("mac")){
            System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/macOS/chromedriver");
            System.out.println("mac");
        } else if (osName.contains("linux")) {
                        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/windows/chromedriver");
//             System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/linux/chromedriver");
            options.setPlatformName("Linux");
            System.out.println("Linux");
        }

        ChromeDriverService service= new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(System.getProperty("webdriver.chrome.driver")))
                .usingAnyFreePort()
                .build();
        
//         options.addArguments("--disable-blink-features");
//         options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
//         options.addArguments("--disable-extensions");
//         options.addArguments("--incognito");
//         options.addArguments("--disable-plugins-discovery");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(service,options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }

    @After
    public void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }
    @Given("I've logged in using my credentials")
    public void iVeLoggedInUsingMyCredentials() {

        //Enter Username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //Enter Password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //Click Login button
        driver.findElement(By.id("login-button")).click();

        //Verify "Your Cart" page
        if(!(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText().equalsIgnoreCase("Products"))){
            Assert.fail("Unable to Login");
        }

    }

    @When("I click on Add to cart button for the Sauce Labs Backpack")
    public void iClickOnAddToCartButtonForTheSauceLabsBackpack() {

        //select add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("Add to cart button should change to remove Backpack")
    public void addToCartButtonShouldChangeToRemoveBackpack() {
        //verify if remove button is displayed
        if(!(driver.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed())){
            Assert.fail("Remove Button for Backpack not Displayed");
        }

    }

    @When("I click on Add to cart button for the Sauce Labs Onesie")
    public void iClickOnAddToCartButtonForTheSauceLabsOnesie() {
        //select add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();

    }

    @Then("Add to cart button should change to remove Onesie")
    public void addToCartButtonShouldChangeToRemoveOnesie() {
        //verify if remove button is displayed
        if(!(driver.findElement(By.id("remove-sauce-labs-onesie")).isDisplayed())){
            Assert.fail("Remove Button for Onesie not Displayed");
        }

    }

    @When("I click on the Shopping cart Icon")
    public void iClickOnTheShoppingCartIcon() {
        //select cart to open
        driver.findElement(By.id("shopping_cart_container")).click();

    }

    @Then("I should be directed to cart page")
    public void iShouldBeDirectedToCartPage() {

        //Verify "Your Cart" page
        if(!(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText().equalsIgnoreCase("Your Cart"))){
            Assert.fail("Unable to view Cart");
        }

    }

    @Then("The product on the cart should be Sauce Labs Backpack and Sauce Labs Onesie")
    public void theProductOnTheCartShouldBeSauceLabsBackpackAndSauceLabsOnesie() {
        //verify backpack
        if(!(driver.findElement(By.id("cart_contents_container")).getText().contains("Sauce Labs Backpack"))){
            Assert.fail("Backpack not added on Cart");
        }
        //verify onesie
        if(!(driver.findElement(By.id("cart_contents_container")).getText().contains("Sauce Labs Onesie"))){
            Assert.fail("Onesie not added on Cart");
        }
    }

    @When("I click on Checkout button")
    public void iClickOnCheckoutButton() {
        //select checkout button
        driver.findElement(By.id("checkout")).click();
    }

    @Then("I should be directed to contact information page")
    public void iShouldBeDirectedToContactInformationPage() {
        //Verify "Checkout: Your Information" page
        if(!(driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText().equalsIgnoreCase("Checkout: Your Information"))){
            Assert.fail("Unable to view Checkout Information Page");
        }

    }

    @When("I Enter the the {string} {string} and {string}")
    public void iEnterTheTheAnd(String firstname, String lastName, String zipcode) {
        //enter firstname
        driver.findElement(By.id("first-name")).sendKeys(firstname);

        //enter lastName
        driver.findElement(By.id("last-name")).sendKeys(lastName);

        //enter zipcode
        driver.findElement(By.id("postal-code")).sendKeys(zipcode);

    }

    @And("I click continue button")
    public void iClickContinueButton() {
        //select continue
        driver.findElement(By.id("continue")).click();
    }

    @Then("I should be directed to Checkout: Overview")
    public void iShouldBeDirectedToCheckoutOverview() {

        //verify checkout : overview
        if(!(driver.findElement(By.xpath("//*[@id='header_container']/div[2]")).getText().equalsIgnoreCase("Checkout: Overview"))){
            Assert.fail("Unable to navigate to  Checkout Overview");
        }
    }

    @And("The Total should be {string}")
    public void theTotalShouldBe(String amount) {
        WebElement total= driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[8]"));
        //verify Total amount
        if(!(total.getText().equalsIgnoreCase("Total: "+amount))){
            Assert.fail("Incorrect Total Amount: "+total.getText());
        }
    }

    @When("I click Finish")
    public void iClickFinish() {
        driver.findElement(By.id("finish")).click();

    }

    @Then("I should be directed to Checkout:Complete screen")
    public void iShouldBeDirectedToCheckoutCompleteScreen() {
        //verify checkout : Complete
        if(!(driver.findElement(By.xpath("//*[@id='header_container']/div[2]")).getText().contains("Checkout: Complete!"))){
            Assert.fail("Unable to Complete Checkout");
        }

    }
}
