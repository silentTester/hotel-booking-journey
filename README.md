# hotel-booking tests using a Journey style pattern
An automation test project for a fictitious hotel booking system.

This project contains automated tests for this website only: http://hotel-test.equalexperts.io

**DEV TECH STACK:**

- Selenium
- JUnit
- Chrome driver
- Java 1.8

I did not use any Acceptance Criteria test framework(e.g Cucumber, Yatspec).
Tests are written using JUnit in a BDD style.
A DDD style package structure is created to make tests easier to maintain and read.
The tests are set to run against Chrome in headless mode for speed.
I have attempted to implement the Journey pattern for the tests.

**OBSERVATIONS:**

- I have noted at times, the hotel web page contains other person's test data, a shared test environment.
- Browser window layout is bit dodgy when resizing.
- Line breaks affected when astronomical values populated.

**NOTES:**

- No cross & mobile-browser testing was done.
- Tests have been developed and tested on Mac OSX.  It has not been tested on Linux or Windows.
- All Test data created (Happy & Bug paths) via the automated tests are  *deleted before next test package is run*, i.e. `BeforeClass`

# Bugs Path Tests
These are the list of bugs found.  I have written them as automated tests, once fixed can be re-run again and even refactored into Sad Path Tests, i.e "Should not allow".

- Allows incorrect names - No regex validation for text fields, e.g allows numbers for names.
- Allows check-in/out dates to be an integer directly, makes the app treat that integer as a timestamp.
- Allows check-out date to be before check-in date.
- Allows invalid check-in date e.g. 31-02-2019 which is converted to 03-03-2019.
- Allows check-in/out dates from the past e.g. before current month or previous day.
- Allows invalid/astronomical figures in price (e.g 23421342233232342).
- Allows invalid/astronomical names.
- Allows negative price e.g. -500.

# Exploratory Tests
Test is ignored, since this is a applicable business scenario

- Allow to make duplicate bookings.

# Sad Path Tests:
- Should not allow to book if both dates are missing. 
- Should not allow to book if Check-in date is missing.
- Should not allow to book if Check-out date is missing.
- Should not allow to book if both names are missing.
- Should not allow to book if First name is missing.
- Should not allow to book if Last name is missing.
- Should not allow to book if price is missing.
- Should not allow to book if invalid price is used.

# Happy Path Tests
- Should allow to cancel/delete a booking.
- Should allow booking by clicking on the days on the JS calender.
- Should allow booking by clicking on the next month on the JS calender.
- Should allow booking by typing in dates using YYYY-MM-DD format.
- Should allow multiple (duplicate & unique) bookings for the same person.

# Prerequisites:
- You will need Chome driver installed in the default path with the appropriate permissions to run.
`private static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";`
- Tests are run in headless mode.

# HOWTO install Chromedriver (Mac OSX):
- From Mac Terminal, with the appropriate user permissions:

`brew tap homebrew/cask && brew cask install chromedriver`

- Verify it is installed in

`/usr/local/bin/chromedriver`

# HOWTO run the tests:
- From Mac Terminal, with the appropriate user permissions:

`./gradlew clean build`

# HOWTO view Test Result Output:
- Gradle will output the Test summary HTML report via the project folder:

`/hotel-booking/build/reports/tests/test/index.html`