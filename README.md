# ICS353-Assignment
* make new branch
* pull
* change 
* commit
* push
* merge to this repo to the master
# How to use
* compile Driver.java
* to generate test matrices run generateTestFiles script. it takes four arguments
    * -minSize: the smallest test matrix size
    * -macSize: the largest test matrix size
    * -step: the increment of the matrix sized
    * -dir: the destination folder where you want the resulting test matices be
* to start testing run testingScript. it takes two arguments:
    * -inputDir: the folder of the test matrices you generated in the previous step
    * -resultDire: the folder that is going to have the output of testing
* to get meaningful data run toCSV script so you can import the test result into Excel. it take one argument:
    * -resultDir: the folder contining the the results from the testing step