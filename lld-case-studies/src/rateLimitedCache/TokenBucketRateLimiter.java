package rateLimitedCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {

    private final int capacity;
    private final double refillRate;

    private final Map<String,Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.nanoTime();
        Bucket bucket= buckets.computeIfAbsent(userId, id -> new Bucket(capacity, now));

        return bucket.tryConsume(capacity,refillRate);
    }

}
