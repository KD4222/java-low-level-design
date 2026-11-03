# Order Management System

Exercise covering inventory, reservations, order confirmation and expiry, including concurrent operations.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\orders"
$sources = Get-ChildItem ".\src\orderManagermentSystemLLD" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```

The demo includes timed expiry and concurrency scenarios.
