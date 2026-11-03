# Vending Machine State Machine

Small exercise modeling vending-machine behavior through explicit states.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\vending-machine"
$sources = Get-ChildItem ".\src\stateMachine_LLD" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```
