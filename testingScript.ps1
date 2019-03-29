param([string]$inputDir, [string]$resultDir)

function iterative
{
    param([string]$fileName, [int32]$n)
    & java.exe Driver 0 $n 0 $inputDir/$fileName $resultDir/"iterative-$fileName"
}
function Strassen
{
    param([string]$fileName, [int32]$base, [int32]$n)
    & java.exe Driver 1 $n $base $inputDir/$fileName $resultDir/"Strassen-$base-$fileName"
}

New-Item -Path . -Name $resultDir -ItemType "directory" 
$testCases = Get-ChildItem -Path $PSScriptRoot/$inputDir
foreach($testCase in $testCases)
{
    $name = [string]$testCase
    $upto = $name.IndexOf("-")
    $n = $name.Substring(0,$upto)
    iterative $testCase $n
    # check that $i is not greater than n to avoid redundent multipications
    for ($i = 1; ($i -lt $n) -and ($i -lt 10) ; $i++) {
        Strassen $testCase $i $n
    }
    for ($i = 10; ($i -lt $n) -and ($i -lt 50) ; $i += 5) {
        Strassen $testCase $i $n
    }
    for ($i = 50; ($i -lt $n) -and ($i -lt 200) ; $i += 10) {
        Strassen $testCase $i $n
    }
    for ($i = 200; ($i -lt $n) -and ($i -lt 1000) ; $i += 25) {
        Strassen $testCase $i $n
    }
    for ($i = 1000; ($i -lt $n) -and ($i -le 4000) ; $i += 100) {
        Strassen $testCase $i $n
    }
}