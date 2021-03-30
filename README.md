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