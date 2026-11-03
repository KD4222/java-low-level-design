# Library Management System

Exercise modeling a library catalog, members, loans, holds, and item availability states.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\library"
$sources = Get-ChildItem ".\src\libraryManagementSystemLLD" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```
