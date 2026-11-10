# Java Task Scheduler

In-memory, multithreaded task scheduler exercise covering delayed execution, task cancellation, concurrency, and lifecycle management.

## Features

- Schedules tasks by delay using a priority queue ordered by execution time.
- Dispatches due tasks to a fixed pool of three worker threads.
- Supports cancellation of scheduled and dispatched tasks, and best-effort interruption of running tasks.
- Uses synchronization and `wait`/`notify` to coordinate the scheduler thread.
- Gracefully stops scheduling and lets already-running work finish during shutdown.

## Design

- `Task` wraps the task's `Runnable` action.
- `ScheduledTask` stores task metadata, execution time, status, and its worker `Future`.
- `TaskScheduler` manages scheduling, dispatch, cancellation, and shutdown.
- `TaskStatus` represents the task lifecycle.

The demo schedules tasks with different delays, cancels one before it runs, and runs a long task alongside another task to show concurrent execution.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\task-scheduler"
$sources = Get-ChildItem ".\src\taskScheduler" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build taskScheduler.Main
```

This project is scoped to a single JVM and keeps scheduled tasks in memory; persistence and distributed scheduling are outside its scope.
