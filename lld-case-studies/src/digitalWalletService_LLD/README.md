# Digital Wallet

Exercise covering users, accounts, payment methods, transfers, and currency conversion. Exchange rates are fixed examples for practicing the design and are not live financial data.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\digital-wallet"
$sources = Get-ChildItem ".\src\digitalWalletService_LLD" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```
