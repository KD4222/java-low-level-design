# UPI / Paytm Wallet

Exercise covering users, wallets, bank accounts, transaction records, and lock-protected transfers.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\upi-wallet"
$sources = Get-ChildItem ".\src\UPI_PayTM_LLD" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```
