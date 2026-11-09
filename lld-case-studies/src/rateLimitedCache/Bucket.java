package rateLimitedCache;

public class Bucket {
    private double tokens;
    private long lastRefillTime;

    public Bucket(double tokens,long lastRefillTime){
        this.tokens= tokens;
        this.lastRefillTime= lastRefillTime;
    }

    public double getTokens() {
        return tokens;
    }

    public long getLastRefillTime() {
        return lastRefillTime;
    }

    public void setTokens(double tokens){
        this.tokens= tokens;
    }

    public void setLastRefillTime(long lastRefillTime) {
        this.lastRefillTime = lastRefillTime;
    }

    public synchronized boolean tryConsume(int capacity, double refillRate){
        //calculate elapsed time
        //calculate newTokens, if newTokens > 0 , then bucket tokens = min(capacity,tokens+newTokens)
        //update lastRefillTime
        //if bucket has tokens, consume one and return true
        //else return false

        long now= System.nanoTime();

        long elapsed = now - lastRefillTime;

        double newTokens = (elapsed / 1_000_000_000.0) * refillRate;

        lastRefillTime= now;

        tokens= Math.min(capacity,tokens+newTokens);

        if(tokens>=1.0){
            tokens-=1.0;
            return true;
        }

        return false;
    }
}
