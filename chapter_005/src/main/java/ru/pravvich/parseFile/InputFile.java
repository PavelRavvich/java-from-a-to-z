package ru.pravvich.parseFile;

import java.io.*;
import java.util.*;

class InputFile implements Input {
    private Map<String, Map<Integer, Order>> mapByBook = new HashMap<>();
    private String pathToFile;

    InputFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }


    public Collection<Order> readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(1) == 'A') {
                    Order order = getObjectOrderFrom(line);
                    Map<Integer, Order> mapByID = mapByBook.get(order.getBook());
                    if (mapByID == null) {
                        mapByID = new HashMap<>();
                        mapByBook.put(order.getBook(), mapByID);
                    }
                    mapByID.put(order.getOrderId(), order);
                } else if (line.charAt(1) == 'D') {
                    Order order = getObjectOrderFrom(line);
                    mapByBook.get(order.getBook()).remove(order.getOrderId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Order> orders = new LinkedList<>();
        for (Map<Integer, Order> map : mapByBook.values()) {
            orders.addAll(map.values());
        }
        return orders;
    }

    private Order getObjectOrderFrom(String line) {
        String[] arrParams = parse(line);
        if (arrParams[2] == null) {
            return new Order(arrParams[0], Integer.valueOf(arrParams[1]));
        }

        return new Order(
                arrParams[0],
                arrParams[1],
                Float.parseFloat(arrParams[2]),
                Integer.valueOf(arrParams[3]),
                Integer.valueOf(arrParams[4])
        );
    }

    private String[] parse(String line) {
        char[] param;
        boolean append = false;
        String[] result = new String[5];
        int countResult = 0;
        char[] data = line.toCharArray();
        for (int i = 0; i != data.length; i++) {
            if (append) {
                param = new char[7];
                int l = 0;
                while (data[i] != '\"') {
                    param[l++] = data[i++];
                }
                i++;
                append = false;
                result[countResult++] = new String(param).trim();
            }

            if (data[i] == '\"') {
                append = true;
            }
        }
        return result;
    }
}
