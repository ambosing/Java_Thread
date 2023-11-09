package atomic.practice;

public class MinMaxMetrics {
    private volatile long min;
    private volatile long max;

    public MinMaxMetrics() {
        // Add code here
        min = Long.MAX_VALUE;
        max = 0;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        // Add code here
        max = Math.max(max, newSample);
        min = Math.min(min, newSample);
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return max;
    }
}
