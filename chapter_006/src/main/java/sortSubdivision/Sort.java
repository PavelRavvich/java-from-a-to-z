package sortSubdivision;

import java.util.*;

public class Sort {
    public static void main(String[] args) {

        System.out.println("Туда");

        List<String> list = new ArrayList<>();
        list.add("K1\\SK1");
        list.add("K1\\SK2");
        list.add("K1\\SK1\\SSK1");
        list.add("K1\\SK1\\SSK2");
        list.add("K2");
        list.add("K2\\SK1\\SSK1");
        list.add("K2\\SK1\\SSK2");
        Collections.sort(list);

        // по порядку
        list.forEach(System.out::println);

        System.out.println("Обратно");
        // в обратном порядке

        final Iterator<String> iterator = new LinkedList<>(list).descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
