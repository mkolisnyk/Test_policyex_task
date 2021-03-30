# Overview

This is sample technical implementation of a few scenarios populating forms on the page: https://insurance.policyexpert.co.uk/home.
Tests cover the following scenarios:
* Basic data population
* Positive scenarios which select "I disagree" in "Statements about you" and "Statements about your property" sections when all expanded items were selected as "I agree"
* Detailed population of "Statements about you" and "Statements about your property" sections specifying more details
* Negative scenario with empty fields entry and error messages verification
* Error messages verification for malformed input (e.g. invalid e-mail format, invalid phone number format, names etc)

## Tech Stack

* Programming language: Java (I used JDK 11)
* Core test engine: JUnit 4
* UI test engine: WebDriver (version 3.141.59)
* Build engine: Gradle (version 5.4.1, wrapper is included)

# Environment Setup

In order to run tests properly, the following settings should be made:
* Make sure you have Java JDK of proper version. Solution was written using JDK 11 but any latter versions should work
* Browser is installed
* WebDriver should be set up either as local runner or as remote server

## Setting up WebDriver

WebDriver can be executed either using local runner or using remote server. Key difference is just the way how it can be configured.

### Local runner

Local runs mainly require dedicated browser drivers being downloaded into **project root** folder.

Depending on browser you can download dedicated driver from the following resources:
* Chrome driver: https://chromedriver.chromium.org/downloads
* Gecko driver for Firefox: https://github.com/mozilla/geckodriver/releases
* MS Edge driver: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/

Please, choose the driver version which matches your browser version and operating system.
Current repository already contains a few downloaded binaries which can run for MacOS but if necessary, they can be overwritten. These files are placed in the **project root** folder.

Once it's done, the only thing left is to configure tests to proper browser (see [Configure Tests](#configure-tests) section)

### Remote server

The standalone Selenium server can be downloaded from this page: https://www.selenium.dev/downloads/.
Use download link in **Selenium Server (Grid)** section.
Here is the download link for the server which was used during development: https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar

This is just a single JAR file which can be placed in separate folder.

Before running it, we still need to download dedicated browser drivers. Use similar links as for [Local runner](#local-runner) but this time files should be copied into the folder where Selenium Server is located.

By default Selenium Server runs on port **4444**, so make sure it's not busy with anything else.

Once it's done, the Selenium Server can be started as the JAR file. From the command line navigate to the Selenium Server folder and run the following command:

```
java -jar selenium-server-standalone-3.141.59.jar

```

Of course, if server version is different, the properly named file should be selected. When server is up and running the console output should look like:
```
19:03:02.323 INFO [GridLauncherV3.parse] - Selenium server version: 3.141.59, revision: e82be7d358
19:03:02.406 INFO [GridLauncherV3.lambda$buildLaunchers$3] - Launching a standalone Selenium Server on port 4444
2021-03-30 19:03:02.472:INFO::main: Logging initialized @385ms to org.seleniumhq.jetty9.util.log.StdErrLog
19:03:02.776 INFO [WebDriverServlet.<init>] - Initialising WebDriverServlet
19:03:02.872 INFO [SeleniumServer.boot] - Selenium Server is up and running on port 4444
```

Once the above steps are done, it's time to prepare tests configuration.

# Configure Tests

Tests configuration defines major parameters needed for the entire test run. It includes:
* Target browser related information
* Application related information
* Test runs related information (like timeouts etc)

The configuration in current solution is defined in the **config.properties** file.
Major configuration entries:
* **url** - target application URL to open. This is major URL of web application under test;
* **browser** - the name of local browser to run tests against. Supported values are:
  * chrome
  * firefox
  * ie
  * safari
  * opera
  * remote - this value should be set if we run Selenium Server
* **remote_url** - (used only when **browser** configuration option is set to **remote**) the Selenium Server endpoint to connected to 
* **remote_browser** - (used only when **browser** configuration option is set to **remote**) defines which browser should be run on Selenium Server. Supported values are similar to the **browser** configuration option except the **remote**

Here is sample configuration for running tests using local Chrome WebDriver:

```properties
url=https://insurance.policyexpert.co.uk/home
browser=chrome
timeout=30
```

Here is an example of configuration running tests against remote Selenium Server instance running on localhost. The Chrome is used as the browser.

```properties
url=https://insurance.policyexpert.co.uk/home
browser=remote
timeout=30
remote_url=http://127.0.0.1:4444/wd/hub
remote_browser=chrome
```

# Running Tests

Entire test suite run can be executed via Gradle command line. Since solution uses Graddle Wrapper, all necessary binaries are in place. So, from the console we should run the following command:

```
gradlew clean test
```

For MacOS or Linux, tests can also be executed via this command:

```
./gradlew clean test
```

All necessary dependencies and missing resources should be picked up.

# Core Framework design approach

Testing solution is based on the following specifics:
1. Major page has dynamic structure and major binding point is the label text near dedicated field
2. The set of actions to be done on elements is pretty much restricted and there are a lot of similarities where the only difference is the label text
3. Each scenario is actually a sequence of answers to the questions when some questions may appear after some answer. It means the sequence is important
4. Controls interaction can be customized for specific fields

Since most of elements have similar shape and the major difference for them is the label, entire interaction is made in functional style when we just define field label and the way it should be interacted with depending on element type (e.g. text field, select list, check button). This covers different action types on those elements which includes data entry, data verification.
So, entire set of actions is represented as set of predicates.

## Key predicate types

All predicates represent some certain function which can be applied to some object on screen. For this framework there are 3 major sets of predicates:
1. Populate - responsible for data entry
2. Verify - responsible for verifying value in some certain field
3. Get - (optional) retrieves the WebElement for specific field. It's mainly needed for copy/paste minimization as populate and verify predicates usually need to retrieve elements first and usually such operation is similar for both predicate types

### Populate

Populate predicate is responsible for data entry. From technical point of view it should be object (or lambda function) implementing the following interface:
```java
package com.policyexpert.sample.lib.actions;

import org.openqa.selenium.WebDriver;

public interface IPopulate {
    void set(WebDriver driver, String field, Object value);
}
```
Here:
* **driver** - the reference to WebDriver object to interact with application UI;
* **field** - the label which can be used to anchor actual control to interact with;
* **value** - the object containing value to set. It can be either basic type like string as well as it can be an array or any other complex object. The predicate implementing actual operation should be able to handle it.

The following is an example of populate predicate definition:

```java
public class Populate {
    public static IPopulate asText = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//input", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElements(locator);
        element.sendKeys(value.toString());
    };
}
```

The above example implements predicate function which finds the text field with label passed as **field** parameter and enters text passed as **value** parameter.
Once we have it, we can use it in the test code in the following way:

```java
Populate.asText.set(driver, "First name", "John");
```

The **Populate** class in the **com.policyexpert.sample.lib.actions** package contains several pre-defined actions entering the data depending on the element type.
In all tests this class is staticly imported to use predicate names directly without defining class name.

### Verify

Verify predicate is responsible for actions which perform various assertions. For individual fields it's still about getting actual element based on label and calling various Assert methods.
Similar to IPopulate, there is deficated verification interface:

```java
package com.policyexpert.sample.lib.actions;

import org.openqa.selenium.WebDriver;

public interface IVerify {
    void check(WebDriver driver, String field, Object value);
}
```
The set of parameters is the same as for IPopulate interface method. The only difference is that the **value** parameter is used as expected value.
Here is an example of code verifying text field content:
```java
public class Verify {
    public static IVerify asTextValue = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//input", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElements(locator);
 
        Assert.assertEquals(element.getAttribute("value"), value);
    };
}
```
It then can be used in the test code like:
```java
Verify.asTextValue.set(driver, "First name", "John");

```

### Get

As it was mentioned before the Get predicates are optional and mainly needed for copy/paste reduction. If you look again for the above samples for Populate.asText and Verify.asTextValue predicates, you'll see that the code retrieving element on the page is identical for both predicates.
It means that some repetitive functionality can be moved to some common place. That's why after implementing the following functionality:

```java
public class Get {
    public static IGet text = (WebDriver driver, String field, Object value) -> {
        By locator = By.xpath(String.format("//*[contains(text(), '%s')]/../..//input", field));
        new WebDriverWait(driver, Configuration.timeout()).until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElements(locator);
    };
}
```
our populate and verify predicates can be simplified to:

```java
public class Populate {
    public static IPopulate asText = (WebDriver driver, String field, Object value) -> {
        text.get(driver, field, value).get(0).sendKeys(value.toString());
    };
}
```
and
```java
public class Verify {
    public static IVerify asTextValue = (WebDriver driver, String field, Object value) -> {
        Assert.assertEquals(text.get(driver, field, value).get(0).getAttribute("value"), value);
    };
}
```
Get predicate return array of elements because in general case it's not just a single element we need to interact with. E.g. for the date fields we need reference to 3 select items.

## Batch operations

Since we are operating with big form and big set of data it is convenient to have some compact presentation of input and expected data.
In most of scenarios we just need to concentrate on some small sub-set of data assuming that everything else is correct and consistent.
We need to make some association between:
* Field we interact with
* Predicate for data population
* Predicate for data verification
* Actual value we enter/verify

For this purpose there can be map used where field name is the key and the value is the structure storing population/verification predicates as well as values.
This structure is implemented in **EntryGroup** class and the entire map is initialized like this:

```java
        Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>();
        testData.put("Statements about you", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IDisagree));
        testData.put(StatementAboutYouQuestions.isNotForBusinessPurposes, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneDeclaredBankrupcy, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
```

This brings all tests to the following structure:
``` 
Data initialization block
           |
           V
Batch data population
           |
           V
Batch data verification
```
Any thin tuning of input data is just about relevant map manipulation.

**NOTE:** form population requires specific sequence of actions. As the result, the set of map entries should be filled in specific sequence. That's why it is recommended to use **LinkedHashMap** as it keeps elements in the same sequence they were added.

### Populating the entire form

Since the form is too big, it's not really convenient to specify each field population as dedicated command. For better presentation, it's better to provide all input data as some big structure, like map and use it as the input value for another populate predicate.
That's why there was additional function added to Populate class:

```java
public class Populate {
    public static IPopulate asForm = (WebDriver driver, String field, Object values) -> {
        HashMap<String, EntryGroup> valuesMap = (LinkedHashMap<String, EntryGroup>) values;
        valuesMap.forEach((key, value) -> {
            value.getPopulateFunc().set(driver, key, value.getValues());
        });
    };
}
```
The map object passed as **value** parameter contains association between field label and relevant populate predicate.
It makes possible to write test populating the entire form in the following way:

```java
Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>() {
    {
        put(AboutYouQuestions.Occupation, new EntryGroup(asText, asTextValue, "Something unknown"));
        put(AboutYouQuestions.EMail, new EntryGroup(asText, asTextValue, "1_3.mail"));
    }
};

asForm.set(driver, null, testData);
``` 

It fills just 2 fields. 

Also, for testing purpose we may need to have some basic data structure to populate forms with consistent values and we can change only a few fields which are necessary for test.
That's why the **defaultTestData** constant was reserved in **com.policyexpert.sample.lib.data.TestData** class.

In this case, when we need to concentrate on just some small group of fields assuming we enetered everything else properly, we can do something like this:

```java
   @Test
    public void testStatementsAboutYouDetailedFillAllAgreed() {
        Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>() {
            {
                putAll(TestData.defaultTestData);
            }
        };
        testData.put("Statements about you", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IDisagree));

        testData.put(StatementAboutYouQuestions.isNotForBusinessPurposes, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneDeclaredBankrupcy, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneInsuranceDeclined, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneInsuranceCancelled, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneServedCountryCourtJudgement, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneConvictedWithAnyOffence, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));

        asForm.set(driver, null, TestData.defaultTestData);
        ...
    }
``` 

The actual predicate which populates dedicated field is defined as first parameter to **EntryGroup** object;

### Verifying the entire form

Similar to batch population, we can verify the data we entered in the same fashion since we keep entered values in centralized structure.
For this purpose the following predicate was added to Verify class:

```java
    public static IVerify asFormValues = (WebDriver driver, String field, Object values) -> {
        HashMap<String, EntryGroup> valuesMap = (LinkedHashMap<String, EntryGroup>) values;
        valuesMap.forEach((key, value) -> {
            value.getVerifyFunc().check(driver, key, value.getValues());
        });
    };
```

We go through every field, call relevant verify predicate expecting values we entered or specified.
It still uses **EntryGroup** structure but this time it retrieves verification predicate. Thus our tests are shaped in the following way:
```java
   @Test
    public void testStatementsAboutYouDetailedFillAllAgreed() {
        Map<String, EntryGroup> testData = new LinkedHashMap<String, EntryGroup>() {
            {
                putAll(TestData.defaultTestData);
            }
        };
        testData.put("Statements about you", new EntryGroup(asSectionButton, asSectionButtonState, BooleanChoiceValue.IDisagree));

        testData.put(StatementAboutYouQuestions.isNotForBusinessPurposes, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneDeclaredBankrupcy, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneInsuranceDeclined, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneInsuranceCancelled, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneServedCountryCourtJudgement, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));
        testData.put(StatementAboutYouQuestions.isNoneConvictedWithAnyOffence, new EntryGroup(asButton, asButtonState, BooleanChoiceValue.IAgree));

        asForm.set(driver, null, TestData.defaultTestData);

        testData.remove("Statements about you");
        asFormValues.check(driver, null, TestData.defaultTestData);
    }
```