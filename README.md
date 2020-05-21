# ICS353-Assignment
Classwork for Design and Analysis of Algorithms [ICS353](http://www.kfupm.edu.sa/departments/ics/Pages/en/ICS-353-Design-Analysis-Algorithms.aspx)

## Purpose
This assignment focuses on comparing the performance of simple 2D matrix multiplication (O(n^3)) vs. Strassen Algorithm (O(n^2.8))

## Testing Environment 
The testing was done using a VM in Azure Cloud with a machine of 8 Cores CPU, 32GB RAM & Windows Server 2016

## Result
The performance of the iterative 2D matrix multipication VS Strassen Algorithm with 4 different bases

![graph](https://github.com/OmarAlghamdi/ICS353-Assignment/blob/master/image.png)

The iterative method outperform Strassen Algorithm with matrix size 1200 or less


# How to use
* compile matrixMultiply.java
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
