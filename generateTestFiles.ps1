param([int32]$size, [string]$name)
function rand
{
    param([int32]$n)
    $row = ""
    for ($i = 0; $i -lt $n; $i++) {
        $row = $row + (Get-Random -Minimum -100 -Maximum 100) + " "
    }
    return $row
}


New-Item -Path . -Name "$name.txt" -ItemType "file" 
for ($i = 0; $i -lt $size*2; $i++) {
    $r = rand $size
    Add-Content -Path $PSScriptRoot/"$name.txt" -Value $r
}

