import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Enter the range of prime numbers and No. of Threads to be used: ");
        int N = sc.nextInt();
        int numThreads = sc.nextInt();
        ParallelPrimeFinder result = new ParallelPrimeFinder(numThreads, N);
        List<Integer> Primeno = result.getPrimes();
        for(int p : Primeno){
            System.out.print(p+" ");
        }
    }   
}
