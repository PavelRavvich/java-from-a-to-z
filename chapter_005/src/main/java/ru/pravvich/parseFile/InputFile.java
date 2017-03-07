package ru.pravvich.parseFile;

import java.io.*;
import java.util.*;

import static ru.pravvich.parseFile.Params.*;

class InputFile implements Input {
    private String pathToFile;

    InputFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public Collection<Order> readFile() {
        File file = new File(pathToFile);
        String content;

        HashMap<Integer, Order> originalIdOrders = new HashMap<>();
        try (BufferedReader buff = new BufferedReader(new FileReader(file))) {
            while ((content = buff.readLine()) != null) {
                if (content.contains("AddOrder")) {
                    Order order = getObjectOrderFrom(content);
                    originalIdOrders.put(order.getOrderId(), order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return originalIdOrders.values();
    }

    private Order getObjectOrderFrom(String line) {
        String id = getOrderParam(line, ID);
        Integer orderId = Integer.parseInt(id);
        String book = getOrderParam(line, BOOK);
        String operation = getOrderParam(line, OPERATION);
        String priseOrder = getOrderParam(line, PRISE);
        float prise = Float.parseFloat(priseOrder);
        String volumeOrder = getOrderParam(line, VOLUME);
        Integer volume = Integer.parseInt(volumeOrder);
        return new Order(book, operation, volume, prise, orderId);
    }

    private String getOrderParam(String order, Params param) {
        String[] orderArr = order.split(param.getValue());
        return orderArr[1].split("\"")[0];
    }
}
