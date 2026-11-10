# Java Low-Level Design Case Studies

A collection of standalone Java exercises for practicing object-oriented design, common design patterns, data structures, and concurrency. Each project focuses on a small problem and includes a runnable `Main` class where a demonstration is available.

These are learning exercises rather than production-ready services. Most examples keep their state in memory, and some demonstrations use fixed or illustrative data.

## Projects

| Project | What you'll find |
| --- | --- |
| [Connect Four](./src/connect4_game_LLD/README.md) | A two-player board game with players, colored discs, turn handling, and game-state checks. |
| [Digital Wallet](./src/digitalWalletService_LLD/README.md) | Users, accounts, payment methods, transfers, transaction records, and example currency conversion. |
| [Interval Intersection and Singleton](./src/IntersectingIntervalLists/README.md) | An algorithm for intersecting two sorted interval lists, plus a thread-safe singleton example. |
| [Library Management System](./src/libraryManagementSystemLLD/README.md) | A catalog of books and magazines, members, copies, loans, holds, and item availability states. |
| [Order Management System](./src/orderManagermentSystemLLD/README.md) | Product inventory, reservations, order confirmation and expiry, repositories, and concurrent operations. |
| [Parking Lot](./src/parkingLot_LLD/README.md) | Vehicles, floors, slots, gates, tickets, vehicle creation, and fixed or hourly fee strategies. Its `Main` is currently an empty placeholder. |
| [Rate-Limited Cache](./src/rateLimitedCache/README.md) | A thread-safe generic LRU cache and a per-user token-bucket rate limiter, with a concurrent demo. |
| [Task Scheduler](./src/taskScheduler/README.md) | Delayed task scheduling, priority-based dispatch, worker threads, cancellation, and graceful shutdown. |
| [Vending Machine State Machine](./src/stateMachine_LLD/README.md) | A vending machine modeled with explicit states for coin insertion, product selection, and dispensing. |
| [UPI / Paytm Wallet](./src/UPI_PayTM_LLD/README.md) | Wallet types, bank accounts, transaction processing, and balance-aware transfers. |

The project folders use their original names and, in some cases, their own package or model/repository/service structure. Compile one project at a time: multiple exercises define a class named `Main`, and the projects are not intended to be combined into one application.

## Concepts Covered

- Object-oriented modeling, encapsulation, and domain services
- State, Strategy, Factory, Observer, and Singleton patterns
- Collections and data structures, including priority queues, hash maps, and linked lists
- Scheduling, thread pools, synchronization, cancellation, and lifecycle management
- Inventory, transactions, reservations, and state transitions
- Interval processing and other small algorithm exercises

## Requirements

- A JDK with `javac` and `java` available on `PATH`
- PowerShell for the commands below (or adapt them to your shell)

No root-level Maven or Gradle build is configured. Each project README contains project-specific run instructions.

## Compile and Run a Project

From this directory in PowerShell, set `$project` to a folder under `src`, compile that folder's Java sources, then run its demonstration:

```powershell
$project = "rateLimitedCache"
$build = Join-Path $env:TEMP "lld-practice-build\$project"
$sources = Get-ChildItem ".\src\$project" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build rateLimitedCache.Main
```

For most exercises, run the default-package entry point as `Main` instead of `rateLimitedCache.Main`. The `rateLimitedCache` and `taskScheduler` projects use named packages, so their entry points are `rateLimitedCache.Main` and `taskScheduler.Main`, respectively. The interval exercise also uses `Main`. Consult the linked project README for details; the parking lot exercise currently has no runnable scenario.

## Repository Layout

- `src/` contains the standalone exercises and `App.java`, a basic Hello World starter.
- Each exercise's source files and README are kept together in its project directory.
