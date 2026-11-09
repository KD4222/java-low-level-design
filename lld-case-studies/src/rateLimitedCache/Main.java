package rateLimitedCache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int workerCount = 8;
        int requestsPerWorker = 25;

        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        RateLimiter rateLimiter = new TokenBucketRateLimiter(10, 2.0);

        CountDownLatch ready = new CountDownLatch(workerCount);
        CountDownLatch start = new CountDownLatch(1);
        AtomicInteger acceptedRequests = new AtomicInteger();
        AtomicInteger rejectedRequests = new AtomicInteger();
        List<Future<?>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(workerCount);

        try {
            for (int workerId = 0; workerId < workerCount; workerId++) {
                final int id = workerId;
                futures.add(executor.submit(() -> {
                    ready.countDown();
                    start.await();

                    for (int request = 0; request < requestsPerWorker; request++) {
                        if (!rateLimiter.allowRequest("demo-user")) {
                            rejectedRequests.incrementAndGet();
                            continue;
                        }

                        acceptedRequests.incrementAndGet();
                        int key = (id * requestsPerWorker + request) % 10;

                        if (cache.get(key) == null) {
                            cache.put(key, key * 10);
                        }
                    }
                    return null;
                }));
            }

            ready.await();
            start.countDown();

            for (Future<?> future : futures) {
                future.get();
            }
        } finally {
            executor.shutdownNow();
        }

        int totalRequests = workerCount * requestsPerWorker;
        System.out.println("Total requests: " + totalRequests);
        System.out.println("Accepted (rate limiter allowed): " + acceptedRequests.get());
        System.out.println("Rejected (rate limited): " + rejectedRequests.get());
        System.out.println("Final cache size: " + cache.size());

        System.out.println("Cached values:");
        for (int key = 0; key < 10; key++) {
            Integer value = cache.get(key);
            if (value != null) {
                System.out.println("  " + key + " -> " + value);
            }
        }
    }
}
