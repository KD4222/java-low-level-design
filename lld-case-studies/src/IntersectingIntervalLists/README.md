# Interview Exercises

Contains an interval-intersection exercise (for intervals sorted by start) and a thread-safe singleton example.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\interview"
$sources = Get-ChildItem ".\src\Interview" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```
