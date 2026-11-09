# Rate-Limited Cache

Thread-safe cache and rate-limiting exercise. The generic LRU cache uses a hash map and a doubly linked list to keep lookups efficient and evict the least recently used entry when full. The token-bucket rate limiter tracks request capacity and refill rate independently for each user.

The demo runs concurrent requests for one user, applies the rate limiter, and stores accepted request values in a bounded cache. Counts and cached values can vary with thread scheduling.

## Run

From `lld-case-studies` in PowerShell:

```powershell
$build = Join-Path $env:TEMP "lld-practice-build\rate-limited-cache"
$sources = Get-ChildItem ".\src\rateLimitedCache" -Recurse -Filter *.java |
    ForEach-Object FullName
javac -d $build $sources
java -cp $build rateLimitedCache.Main
```

The demo uses a cache capacity of 5 and a per-user token bucket with a capacity of 10 tokens and a refill rate of 2 tokens per second.
