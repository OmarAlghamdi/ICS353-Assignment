param([int32]$minSize, [int32]$maxSize, [int32]$step, [string]$dir)
function rand
{
    param([int32]$n)
    $row = ""
    for ($i = 0; $i -lt $n; $i++) {
        $row = $row + (Get-Random -Minimum -100 -Maximum 100) + " "
    }
    return $row
}

function generate
{
    param([int32]$size, [string]$name)
    New-Item -Path $PSScriptRoot/$dir -Name "$name.txt" -ItemType "file" 
    for ($i = 0; $i -lt $size*2; $i++) {
        $r = rand $size
        Add-Content -Path $PSScriptRoot/$dir/"$name.txt" -Value $r
    }
}

New-Item -Name $dir -ItemType "directory"
for ($i = $minSize; $i -le $maxSize; $i += $step) {
    generate $i "$i-$i-matrices"
}

