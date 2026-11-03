# Design Patterns and Exercises

Java examples and smaller exercises originally collected in `FirstLLDProject`.

## Topics

- Design patterns: abstract factory, factory, decorator, observer (push and pull), and strategy
- Other exercises: deciding schedule, Flipkart Minutes, parking lot, Snake and Ladder, Splitwise, and Tic-tac-toe

Sources are grouped under `src/` by their existing exercise folders. The original source and package names are retained.

The standalone `src/Main.java` is an empty placeholder; run an exercise's own `Main` class instead.

## Build and run

Compile one exercise at a time because several exercises use the same class names. From this directory in PowerShell:

```powershell
$project = "factoryPattern"
$build = Join-Path $env:TEMP "lld-practice-build\$project"
$sources = Get-ChildItem ".\src\$project" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```

Some smaller exercises do not have a runnable `Main` class.
