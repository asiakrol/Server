import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelSort {
    public List<Integer> sort(List<Integer> toSort) {
        //Podziel tablice wejsciowa na 4 czesci
        List<List<Integer>> partitions = partition(toSort);
        //Dla kazdej czesci z partycji utworz nowy watek z uzyciem executora
        //i callable
        ExecutorService exececutorService = Executors.newFixedThreadPool(4);
        List<Future<List<Integer>>> sortedPartitionsFutures = new ArrayList<>();
        for (List<Integer> partition : partitions) {
            Future<List<Integer>> wynikSubmit = exececutorService.submit(() -> {
                Collections.sort(partition);
                return partition;
            });
            sortedPartitionsFutures.add(wynikSubmit);
        }

        //posortuj w kazdym z watkow jedna czesc z partycji
        //posortowane czesci przypisz do tablicy tablic sortedPartitions
        List<List<Integer>> sortedPartitions = new ArrayList<>();
        try {
            for (Future<List<Integer>> future : sortedPartitionsFutures) {
                sortedPartitions.add(future.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Scal posortowane czesci
        return merge(sortedPartitions);
    }

    private List<Integer> merge(List<List<Integer>> sortedPartitions) {
        return null;
    }

    /**
     * Dzieli tablice wejsciowa na 4 elementowa tablice tablic
     *
     * @param toSort
     * @return
     */

    public List<List<Integer>> partition(List<Integer> toSort) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        result.add(new ArrayList<Integer>());
        result.add(new ArrayList<Integer>());
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < toSort.size(); i++) {
            int reminder = i % 4;
            result.get(reminder).add(toSort.get(i));
        }

        return result;

    }

   /* Napisz program, który dla czterech list wypełnionych intami (0,1,2,3,4,5) wypisze na ekran zawartość tablic tak by na początku pojawiły się cztery 0, potem cztery 1 itd. Pobranie elementu z listy powoduje jego usunięcie z listy.*/


    public static void main(String[] args) {

        List<Integer> lista1 = new ArrayList(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5}));
        List<Integer> lista2 = new ArrayList(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5}));
        List<Integer> lista3 = new ArrayList(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5}));
        List<Integer> lista4 = new ArrayList(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5}));

        List<Integer> tempList = new ArrayList<>();

        while (!lista1.isEmpty()){

            tempList.add(lista1.get(0));
            tempList.add(lista2.get(0));
            tempList.add(lista3.get(0));
            tempList.add(lista4.get(0));
//            i++;

            //System.out.println(tempList);

            lista1.remove(0);
            lista2.remove(0);
            lista3.remove(0);
            lista4.remove(0);

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(tempList);
    }

   //for()

    {
        //System.out.println();


        //sysoutprtl
        //wyprintuje 0,0,0,0...
        //koniec listy puste


    /*}Listy zawierają elementy
        1: 12, 22, 32, 42, 52
        2: 10, 20, 30, 40, 50
        3: 13, 23, 33, 43, 53
        4: 11, 21, 31, 41, 51

    Na ekranie powinno pojawić się 10, 11, 12, 13, 20, 21, 22, 23, 30, 31, 32, 33…...*/

        List<Integer> list1 = new ArrayList(Arrays.asList(new Integer[]{12, 22, 32, 42, 52}));
        List<Integer> list2 = new ArrayList(Arrays.asList(new Integer[]{10, 20, 30, 40, 50}));
        List<Integer> list3 = new ArrayList(Arrays.asList(new Integer[]{13, 23, 33, 43, 53}));
        List<Integer> list4 = new ArrayList(Arrays.asList(new Integer[]{11, 21, 31, 41, 51}));
    }
}