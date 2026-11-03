# LLD Case Studies

Java low-level design exercises originally collected in `LLD_practiceV2/LLDAttempts`.

## Projects

| Project | Description |
| --- | --- |
| [Connect Four](./src/connect4_game_LLD/README.md) | Board, players, and turn-based play |
| [Digital wallet](./src/digitalWalletService_LLD/README.md) | Accounts, payment methods, and currency conversion |
| [Interview exercises](./src/Interview/README.md) | Interval exercise and singleton pattern |
| [Library management](./src/libraryManagementSystemLLD/README.md) | Catalog, loans, holds, and item states |
| [Order management](./src/orderManagermentSystemLLD/README.md) | Inventory, reservations, and order expiry |
| [Parking lot](./src/parkingLot_LLD/README.md) | Parking lot domain model |
| [Vending machine](./src/stateMachine_LLD/README.md) | State-based vending machine |
| [UPI / Paytm wallet](./src/UPI_PayTM_LLD/README.md) | Wallet and account transfer exercises |

## Build a project

Compile one folder at a time; these exercises contain repeated class names and are not intended to be compiled as one application. From this directory in PowerShell:

```powershell
$project = "connect4_game_LLD"
$build = Join-Path $env:TEMP "lld-practice-build\$project"
$sources = Get-ChildItem ".\src\$project" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```

Each project keeps its original source and package layout. Fixed exchange rates in the wallet exercise are illustrative only.
