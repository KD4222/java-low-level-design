# Low-Level Design Practice

A collection of Java exercises for practicing object-oriented design, design patterns, and low-level design interview problems. The examples are organized into independent projects so each can be explored and compiled on its own.

## Repository layout

| Section | Contents |
| --- | --- |
| [Design patterns and exercises](./design-patterns-and-exercises/README.md) | Pattern examples and smaller practice problems |
| [LLD case studies](./lld-case-studies/README.md) | Larger interview-style design exercises |

## LLD case studies

| Project | Overview |
| --- | --- |
| [Connect Four](./lld-case-studies/src/connect4_game_LLD/README.md) | Turn-based board game |
| [Digital wallet](./lld-case-studies/src/digitalWalletService_LLD/README.md) | Accounts, payments, and currency conversion |
| [Interview exercises](./lld-case-studies/src/Interview/README.md) | Interval exercise and singleton pattern |
| [Library management](./lld-case-studies/src/libraryManagementSystemLLD/README.md) | Catalog, loans, holds, and item states |
| [Order management](./lld-case-studies/src/orderManagermentSystemLLD/README.md) | Inventory, reservations, and order expiry |
| [Parking lot](./lld-case-studies/src/parkingLot_LLD/README.md) | Parking domain model and services |
| [Vending machine](./lld-case-studies/src/stateMachine_LLD/README.md) | State-based vending machine |
| [UPI / Paytm wallet](./lld-case-studies/src/UPI_PayTM_LLD/README.md) | Wallets and account transfers |

## Build and run

There is no single application entry point or shared build system. Several exercises use the same Java class names (such as `Main`) or default-package classes, so compile **one project at a time**. The project READMEs include descriptions and commands. For example, from the repository root in PowerShell:

```powershell
$project = "connect4_game_LLD"
$build = Join-Path $env:TEMP "lld-practice-build\$project"
$sources = Get-ChildItem ".\lld-case-studies\src\$project" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```

Examples and exchange rates are for learning, not production financial use. Build output and editor-specific files are excluded from version control.
