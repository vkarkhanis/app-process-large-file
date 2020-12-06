# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

To read a large file and solve following questions:

Write a program that will print out the total number of lines in the file.
Notice that the 8th column contains a person’s name. Write a program that loads in this data and creates an array with all name strings. Print out the 432nd and 43243rd names.
Notice that the 5th column contains a form of date. Count how many donations occurred in each month and print out the results.
Notice that the 8th column contains a person’s name. Create an array with each first name. Identify the most common first name in the data and how many times it occurs.

### How do I run the example? ###

1. Here we have not pushed the real 4GB file named  "itcont.txt" -- This file can be downloaded from ​https://www.fec.gov/files/bulk-downloads/2018/indiv18.zip
2. Instead we pust dataFile.txt which is like a sample file
3. To run the Project either run the test class ProcessFileTest or you can run Java main class ProcessFile

### Couple of useful things to checkout:

1. To test System.out.println try as follows:

private static final ByteArrayOutputStream outStream = new ByteArrayOutputStream()
private static final PrintStream originalStream = System.out;

@BeforeAll
public static void setUp() {
    System.setOut(new PrintStream(outStream));
}

@AfterAll
public static void cleanUp() {
    System.setOut(originalStream);
}

2. To mock final class-- add a file org.mockito.plugins.MockMaker under src/test/resources with an entry "mock-maker-inline". Then mock classes as usual
