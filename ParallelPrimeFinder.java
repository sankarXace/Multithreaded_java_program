import java.util.ArrayList;
import java.util.List;

public class ParallelPrimeFinder {
    private final int numThreads;
    private final int range;
    private boolean[] primes;
    private int blockSize;
    private List<Thread> threads;

    public ParallelPrimeFinder(int numThreads, int range) {
        this.numThreads = numThreads;
        this.range = range;
        this.primes = new boolean[range + 1];
        this.blockSize = (range + numThreads - 1) / numThreads;
        this.threads = new ArrayList<>();

        // Initialize primes array
        for (int i = 2; i <= range; i++) {
            primes[i] = true;
        }
    }

    public List<Integer> getPrimes() throws InterruptedException {
        // Create threads
        for (int i = 0; i < numThreads; i++) {
            int start = i * blockSize + 2;
            int end = Math.min((i + 1) * blockSize + 1, range);

            Thread thread = new Thread(() -> {
                // Sieve of Eratosthenes algorithm
                for (int j = 2; j * j <= end; j++) {
                    if (primes[j]) {
                        int startMultiple = (start / j) * j;
                        if (startMultiple < j * j) {
                            startMultiple = j * j;
                        }
                        for (int k = startMultiple; k <= end; k += j) {
                            primes[k] = false;
                        }
                    }
                }
            });

            threads.add(thread);
            thread.start();
        }

        // Wait for threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Create list of prime numbers
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= range; i++) {
            if (primes[i]) {
                primeNumbers.add(i);
                
            }
        }

        return primeNumbers;
    }
}
