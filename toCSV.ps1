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
    # size
    # time
    # $time = Get-Content $file -ReadCount 1

    # write
    Add-Content $result -Value $val

    
    
}