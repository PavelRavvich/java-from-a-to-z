package ru.pravvich.parseFile;

class Order {
    private String book;
    private String operation;
    private float price;
    private int volume;
    private int orderId;

    Order(String book,
          String operation,
          float price,
          int volume,
          int orderId) {

        this.book = book;
        this.operation = operation;
        this.volume = volume;
        this.price = price;
        this.orderId = orderId;
    }

    Order(String book, int orderId) {
        this.orderId = orderId;
        this.book = book;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String toString() {
        return "book='" + book + '\'' +
                ", operation='" + operation + '\'' +
                ", volume=" + volume +
                ", price=" + price +'}';
    }
}
