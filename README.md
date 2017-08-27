# SeleniumApp
## Description of the test
This test verifies an error message after trying to book a flight using a wrong card number during payment process.

## Requirements of the test
* jre 1.8
* Maven 3.3
* Git
* Google Chrome
* **Last minute requirement** - I've just noticed that the test has been implemented using a screen resolution of 1920x1080. The test may not work at lower resolutions.

## How to execute the test
### From command line
1. Clone GitHub repository `git clone https://github.com/WarioNeila86/SeleniumApp.git`
1. `mvn clean`
1. `mvn verify`
### From Eclipse
1. Install [Cucumber Eclipse Plugin](https://cucumber.io/cucumber-eclipse/)
1. Right click on `DeclinedPayment.feature` > Run As > Cucumber Feature

## Test results
* Generated HTML report can be found at `target\site\cucumber-reports\feature-overview.html`
* JSON report can be found at `target\cucumber.json`