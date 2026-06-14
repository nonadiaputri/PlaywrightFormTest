# Playwright Java Automation Testing

## Overview

This project contains automated test scripts for the DemoQA Student Registration Form using Playwright Java, TestNG, Maven, and Extent Reports.

### Application Under Test

https://demoqa.com/automation-practice-form

## Tech Stack

* Java 21
* Playwright Java
* TestNG
* Maven
* Extent Reports

## Project Structure

```text
src/test/java
├── base
│   └── BaseTest.java
├── pages
│   └── RegistPage.java
├── testdata
│   └── StudentData.java
├── tests
│   └── RegistTest.java
└── utils
    ├── ExtentManager.java
    └── ScreenshotUtils.java
```

## Test Scenarios

### Positive Test

* Submit form with valid data
* Verify success popup is displayed
* Verify submitted data matches input values

### Negative Tests

* Submit form with invalid email format
* Verify browser validation message is displayed
* Submit empty form
* Verify required field validation is displayed

## How to Run

### Clone the Repository

```bash
git clone https://github.com/nonadiaputri/PlaywrightFormTest.git
```

### Navigate to Project Directory

```bash
cd PlaywrightFormTest
```

### Run All Tests

```bash
mvn test
```

## Test Report

After test execution, the Extent Report will be generated automatically.

Report location:

```text
test-output/
```

Open the generated HTML report in your browser to view:

* Test results
* Execution logs
* Screenshots (if configured)

## Author

**Nadia Kusuma Putri**

QA Automation Engineer | Playwright Java | TestNG
