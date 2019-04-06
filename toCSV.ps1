param([string]$resultsDir)

$files = Get-ChildItem -Path $PSScriptRoot/$resultsDir 
$result = New-Item -Path . -Name "result.csv" -ItemType "file"
foreach ($file in $files)
{
    $str = [string]$file
    # algorithm
    $start = 0
    $ind = $str.IndexOf("-")
    $val = [string]$str.Substring($start, $ind) + "," 
    # base
    $start = $ind + 1
    $ind = $str.IndexOf([char]"-", [int32]$start)
    $val = $val + [string]$str.Substring($start, $ind-$start) + ","
    # size
    $start = $ind + 1
    $ind = $str.IndexOf([char]"-", [int32]$start)
    $val = $val + [string]$str.Substring($start, $ind-$start) + ","
    # time
    $path = $resultsDir + "/" + $str
    $time = Get-Content -Path $PSScriptRoot/$path -TotalCount 1
    $val = $val + [string]$time
    # $time = Get-Content $file -ReadCount 1

    # write
    Add-Content $result -Value $val

    
    
}