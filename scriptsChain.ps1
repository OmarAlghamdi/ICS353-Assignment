.\generateTestFiles.ps1 -minSize 30 -maxSize 50 -step 3 -dir testcases
.\testingScript.ps1 -inputDir testcases -resultDir results
.\toCSV.ps1 -resultsDir results