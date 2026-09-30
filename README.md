# DemoQA UI Test Automation

Training project developed during the QA Automation Engineer program at AIT Technology School. It demonstrates UI test automation for [DemoQA](https://demoqa.com/) with Java, Selenium WebDriver, JUnit 5 and the Page Object Model.

## Covered scenarios

- text boxes, buttons and file upload
- broken-link and broken-image checks via HTTP status codes
- JavaScript alerts, frames, nested frames and browser windows
- menus, sliders, select controls and tooltips
- parameterized and CSV-driven tests
- screenshots and logging on WebDriver errors

## Technology stack

- Java 21
- Selenium WebDriver 4
- JUnit 5
- Maven
- AssertJ soft assertions
- WebDriverManager
- SLF4J and Logback
- Page Object Model

## Project structure

- `src/main/java/com/demoqa/pages` — page objects grouped by application area
- `src/main/java/com/demoqa/core` — browser setup and shared page actions
- `src/main/java/com/demoqa/utils` — listener and test-data utilities
- `src/test/java/com/demoqa/tests` — JUnit test classes
- `src/test/resources` — CSV data, logging configuration and upload fixture

## Run the tests

Prerequisites: JDK 21, Maven and a supported browser installed locally.

```bash
mvn test
```

The default browser is Chrome. Firefox, Edge and Safari are supported by the browser factory in `ApplicationManager`.

## Notes

This is a training project, not a production or client application. The automated tests depend on the availability and current markup of the public DemoQA website.
