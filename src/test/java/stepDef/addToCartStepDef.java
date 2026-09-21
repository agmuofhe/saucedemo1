package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


public class addToCartStepDef {
    WebDriver driver;
    public LocalDateTime dateNow = LocalDateTime.now();
    public static int iterationCount = 0;

    public void settingUp(){

        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/macOS/chromedriver");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(System.getProperty("webdriver.chrome.driver")))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-plugin-discovery");

        WebDriver drivers= new ChromeDriver(service,options);
        drivers.get("endpoint");
        drivers.manage().window().maximize();

    }
    @Before
    public void setup(){
        ChromeOptions options= new ChromeOptions();

        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-plugins-discovery");
        options.addArguments("--remote-allow-origins=*");
         options.addArguments("--disable-plugins-discovery");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        //increment iteration count
        setIteration(getIterationCount()+1);

    }

    @After
    public void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }
    @Given("I've logged in using my credentials")
    public void iVeLoggedInUsingMyCredentials() throws IOException {

        //Enter Username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //Enter Password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        screenshot("login");

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("finish"))));
        new Select(driver.findElement(By.id("finish"))).selectByVisibleText("Hello");
        Alert alert = driver.switchTo().alert();
        alert.accept();
        alert.dismiss();
        alert.sendKeys("HEllo");
    }

    @Then("I should be directed to Checkout:Complete screen")
    public void iShouldBeDirectedToCheckoutCompleteScreen() throws IOException {
        //verify checkout : Complete
        if(!(driver.findElement(By.xpath("//*[@id='header_container']/div[2]")).getText().contains("Checkout: Complete!"))){
            Assert.fail("Unable to Complete Checkout");
        }
        screenshot("Checkout Complete");
    }

    public void screenshot(String fileName) throws IOException {

        TakesScreenshot screenshot = ((TakesScreenshot) driver);

        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        File destFile=new File("target/screenshots/"+dateNow+"/"+fileName+getIterationCount()+".png");

        FileUtils.copyFile(sourceFile,destFile);
    }

    public static int getIterationCount() {
        return iterationCount;
    }

    public static void setIteration(int iterationCount) {
        addToCartStepDef.iterationCount = iterationCount;
    }
}
