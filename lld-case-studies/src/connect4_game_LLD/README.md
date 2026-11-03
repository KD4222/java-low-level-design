# Connect Four

Object-oriented Connect Four exercise with players, a board, and game-state handling.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\connect4"
$sources = Get-ChildItem ".\src\connect4_game_LLD" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build Main
```
