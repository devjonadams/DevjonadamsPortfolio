# Luma Automated Testing Project
Author: Jonathan Adams

Initial Creation Date: 31.03.25

License: APACHE 2.0

## Overview

This project automates the testing of a simple test site at [softwaretestingboard](https://magento.softwaretestingboard.com/).

This project is based on the Serenity-BDD JUnit Starter. The original template for this project is located [here](https://github.com/serenity-bdd/serenity-junit-starter/)

## Getting Started

This project was built to be used as a Maven project. 

Once opened, tests can be run from the Intellij IDE or from the Maven runner.

### Web Driver

By default, this project utilizes Chrome Driver. 

Serenity-BDD has a built-in Driver Manager, and will download the appropriate Chrome Driver alongside Chrome Browser.

Alternative browsers have not been tested due to technical limitations. 

### Running the tests under Maven

The template project comes with both Maven and Gradle build scripts. To run the tests with Maven, open a command window and run:

  ./mvnw clean verify

Gradle Builds are not validated with this project implementation. Please do not use Gradle to build the project.


### Viewing the reports

The maven command provided above will produce a Serenity test report in the `target/site/serenity` directory. This report is auto-generated by Serenity-BDD.

Once these are generated (using the maven -> verify command), you can open the 'index.html' webpage in 'target/site/serenity' to view the reporting webpage.

## Code Structure

The tests in this project are built around the Actor-pattern implementation in Serenity-BDD (i.e. the Screenplay Pattern).

This pattern provides a general code structure:
* test.java.<>.testsuite.<>: These are a series of classes that define the various test cases. Within the test case class, the Actor is defined.
* test.java.<>.behaviors.<>: These classes provide "actions" and activity the Actor can perform. In the common Selenium pattern, these would be equivalent to steps.
* test.java.<>.pages.<>: These classes provide a reference library of data to elements in the page/ui. These are page objects in the common Selenium pattern.
* test.java.<>.engine.<>: These classes are utility libraries and configuration data used by the engine. 

Please see each test case class to have an overview of test scenarios performed.

## Test Execution

From the IDE, tests can be executed at the package, class, or test-case level.

* For the package-level, right click on test.java.<>.testsuite, or a descendent package from it. Then, select "Run 'Tests' in 'testsuite'". This will execute all tests in the 'testsuite' -- or child -- package marked with the "@Test" attribute.
* Similarly, for the class-level execution, right-click on the desired class under test.java.<>.testsuite.<>. Then, select "Run 'Tests' in '<classnamehere>'". This will execute all tests marked with the "@Test" attribute in the class.
* Finally, to run individual tests at the test-case-level, right-click a desired test method that is marked with "@Test" in a class nested in the testsuite package defined above. Then select "Run 'somenameoftestfunctionhere()...'". This will execute the single test case that you've selected.

## Commentary & Developer's Notes

The assignment asked for 3 scenarios in the web-app for:
* Advanced Search:
  * Defined in classes under testsuite.pages.advancedsearch.
  * Tests all Advanced Form Input fields can submit and lead to a results page.
  * Tests results of Advanced Search can be sorted in A-Z/Z-A and Price for High-to-Low/Low-to-High.
* Search and Sort
  * Defined in classes under testsuite.pages.simplesearch.
  * Tests the Simple Search defined in the top-right bar of the page.
  * Tests results of Simple Search can be sorted in A-Z/Z-A and Price for High-to-Low/Low-to-High.
* Item Comparison
  * Defined in classes under testsuite.pages.itemcomparison.
  * Tests that result items can be added to the Item Comparison List Widget (on the Search Results page).
  * Tests that the Item Comparison Widget supports provided interactions (Compare button goes to Compare Page, Clear All clears the list, etc.)
* Additionally, x3 candidate-created scenarios of choice.
  * There are over 15 + X test cases defined in the total suite.

I also wanted to provide commentary on the code structure, and how test cases are derived in my approach. 
With this Screenplay/Actor pattern, developers treat the test engine as a form of MVC and a Command-Executor pattern.

In this framework, one expands the suite of test cases as behaviors are expanded. 
Tests are iterated by adding on new behaviors to the behavior set, and page-&-target definitions in the pages set. 
Once these are added to the engine structure, new cases can be added. 

This process described can be observed in the development flow of:
1. TestUsingSimpleSearch: 
   * Developed page and behavior for the Simple Search Bar from the Home Page. 
   * This was iterated until Simple Search Results pages and behavior were generated.
2. TestUsingAdvancedSearch: 
   * Developed page and behavior for the Advanced Search Page by altering code from TestUsingSimpleSearch.
3. TestSimpleSearchAndSort: 
   * Developed page and behavior handling for the sorting widget. 
   * Afterward, TestUsingSimpleSearch was used as the template for creating the SearchAndSort tests.
4. TestAdvancedSearchAndSort: 
   * SimpleSearchAndSort was iterated until the A-Z/Z-A and Price High-to-Low/Low-to-High tests were developed. 
   * These were then used as the template for AdvancedSearchAndSort.
5. TestUsingItemComparisonWidget: 
   * SimpleSearchAndSort was iterated at the Search Results, and behavior added to handled getting data from product item widgets and adding items to the Compare List. 
   * This was used to expand coverage to the Compare List widget.
6. TestItemComparison: 
   * Flow for SimpleSearch to Comparison Widget was iterated. 
   * Coverage was then expanded to the Compare Items page.

With this pattern, test cases are added in a TDD/BDD method in parallel to functionality added during sprint iterations.


## FAQ

### Can I change the serenity.conf?

Yes, just run the maven commands for clean and package to regenerate the configuration files.

### Can I try a different browser? 

You are welcome to, but this has only been tested with Chrome. 

### Can I change the log-level?

Yes. Under the test.java.lumaautomation.testsuite.BaseTestCase class, the Logging Level is set.
This can be adjusted to any log level by setting the value for defaultLogLevel on line 18.