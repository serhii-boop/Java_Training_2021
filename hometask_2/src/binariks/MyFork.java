package binariks;

import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyFork extends RecursiveTask<Map<String, Integer>> {

    List<String> data;
    int from, to;

    public MyFork(List<String> data) {
        this.data = data;
    }

    Map<String, Integer> countAmount = new HashMap<>();



    @Override
    protected Map<String, Integer> compute() {
//        if ((to-from) <= numOfOperations/numOfThread){
//            if (countAmount.containsKey(data.g))
//        }
//        else {
//            int middle = (to+from)/2;
//            MyFork firstHalf = new MyFork(data, from, middle);
//            firstHalf.fork();
//            MyFork secondHalf = new MyFork(data,middle+1,to);
//            Map<String, Integer> secondValue = secondHalf.compute();
//
//            return firstHalf.join()+secondValue;
//        }

        //if work is above threshold, break tasks up into smaller tasks
        Map<String, Integer> countAmount = new HashMap<>();
        if (data.size()<=2){
            for (String i: data) {
                String userName = i.split(",")[1];
                Integer amount = Integer.parseInt(i.split(",")[2]);
                if (countAmount.containsKey(userName))
                    countAmount.put(userName, countAmount.get(userName)+amount);
                else
                    countAmount.put(userName,amount);
            }
            return countAmount;
        } else {
            MyFork HalfArrayValueSumCounter = new MyFork(data.subList(0, data.size()/2));
            MyFork secondHalfArrayValueSumCounter = new MyFork(data.subList(data.size()/2, data.size()));
            HalfArrayValueSumCounter.fork();
            secondHalfArrayValueSumCounter.fork();
            Map<String, Integer> map3 = Stream.of(HalfArrayValueSumCounter.join(), secondHalfArrayValueSumCounter.join())
                    .flatMap(map -> map.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            Integer::sum));
            return map3;

        }


    }

}
