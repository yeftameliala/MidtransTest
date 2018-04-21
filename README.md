# MidtransTest
This testcase is build in Java, so it need a tool and some libraries to make it run properly.
A Java IDE need to put the source. I made it with Eclipse, but others should work fine.
Library that needed is from Selenium WebDriver. You can get it here "https://selenium-release.storage.googleapis.com/3.11/selenium-java-3.11.0.zip"
or if the link dead, please go to "https://www.seleniumhq.org/download/" to get WebDriver link.
and Selenium need a browser driver to run the test. You can download it here "https://github.com/mozilla/geckodriver/releases"

How to run this testcase:
1. Import the project from Github (https://github.com/yeftameliala/MidtransTest.git)
2. Put the required library to project.
   In Eclipse:
   - Right Click on Project > Properties > Java Build Path > Add External JARs
   - Add 2 JARs in extracted selenium zip file, and add all the JARs in libs folder.
3. Edit TestCase1.java, on browserDriver variable. Locate the directory of extracted browser driver, copy and replace to the variable.
4. Run the project