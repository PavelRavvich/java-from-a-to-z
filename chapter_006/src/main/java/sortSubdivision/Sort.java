package sortSubdivision;

import java.util.*;

import static java.lang.String.format;

public class Sort {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("K1\\SK2");
        list.add("K2\\SK1\\SSK2");
        list.add("K1\\SK1");
        list.add("K1\\SK1\\SSK2");
        list.add("K2");
        list.add("K1\\SK1\\SSK1");
        list.add("K2\\SK1\\SSK1");

        final List<String> result = new Sort().restorationStructure(list);

        System.out.println("=============");
        System.out.println("=== Тудас ===");
        System.out.println("=============");
        result.forEach(System.out::println);

        System.out.println("=============");
        System.out.println("== Обратно ==");
        System.out.println("=============");

        final Iterator<String> desIterator = new LinkedList<>(result).descendingIterator();

        while (desIterator.hasNext()) {
            System.out.println(desIterator.next());
        }
    }

    public List<String> restorationStructure(final List<String> src) {
        final Set<String> set = new HashSet<>();
        for (String fullPath : src) {
            String[] split = fullPath.split("\\\\");
            set.add(fullPath);

            String append = "";
            for (int i = 0; i < split.length - 1; i++) {
                set.add(append = format("%s%s\\", append, split[i]));
            }
        }

        src.clear();
        for (String s : set) {
            if (s.charAt(s.length() - 1) == '\\') {
                src.add(s.substring(0, s.length() - 1));
            } else {
                src.add(s);
            }
        }

        set.clear();
        set.addAll(src);
        src.clear();
        src.addAll(set);
        Collections.sort(src);
        return src;
    }
}
