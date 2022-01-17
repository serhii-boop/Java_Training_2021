package binariks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {


    public static int numOfThread = Runtime.getRuntime().availableProcessors();

    static class UsualThread extends Thread {
        private Queue<Transaction> data;
        public UsualThread(Queue<Transaction> data){
            this.data = data;
        }

        @Override
        public void run(){
            long startTime = System.nanoTime();
                Map<String, Integer> countAmount1 = new HashMap<>();
                for (Transaction k: data) {
                    if (countAmount1.containsKey(k.getUserName()))
                        countAmount1.put(k.getUserName(), countAmount1.get(k.getUserName())+ k.getAmount());
                    else
                        countAmount1.put(k.getUserName(), k.getAmount());
                }
            long time = System.nanoTime() - startTime;
            System.out.println("UsualThread " + this.getId() + " execution time: " + time + " ns");
            System.out.println(countAmount1);
        }

    }

    static class ParallelStreamThread extends Thread {
        private Queue<Transaction> data;
        public ParallelStreamThread(Queue<Transaction> data){
            this.data = data;
        }

        @Override
        public void run(){
            long startTime = System.nanoTime();
            Map<String,Integer> countAmount = data.parallelStream().collect(Collectors.toMap(Transaction::getUserName,
                    Transaction::getAmount,
                    Integer::sum));
            long time = System.nanoTime() - startTime;
            System.out.println("ParallelThread " + this.getId() + " execution time: " + time + " ns");
            System.out.println(countAmount);
        }
    }

    static class ForkThread extends Thread{

        private List<String> dataFork;
        public ForkThread(List<String> dataFork){
            this.dataFork = dataFork;
        }
        @Override
        public void run(){
            long startTime = System.nanoTime();
            ForkJoinPool pool = new ForkJoinPool(numOfThread);
            Map<String,Integer> summ = pool.invoke(new MyFork(dataFork));
            long time = System.nanoTime() - startTime;
            System.out.println("ForkThread " + this.getId() + " execution time: " + time + " ns");
            for (Map.Entry<String,Integer> entry: summ.entrySet()) {
                System.out.println(entry);
            }
        }
    }



    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\binariks\\hometask_2\\input.txt")));
        Queue<Transaction> data = new LinkedList<>();
        List<String> dataFork = new ArrayList<>();
        String line;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            dataFork.add(line);
            data.add(new Transaction(Integer.parseInt(line.split(",")[0]),
                    line.trim().split(",")[1],
                    Integer.parseInt(line.trim().split(",")[2])));
        }




        UsualThread usualThread = new UsualThread(data);
        usualThread.start();


        ParallelStreamThread parallelStreamThread = new ParallelStreamThread(data);
        parallelStreamThread.start();


        usualThread.join();
        parallelStreamThread.join();
        ForkThread forkThread = new ForkThread(dataFork);
        forkThread.start();

    }
}
