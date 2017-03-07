package ru.pravvich.parseFile;

enum  Params {
    OPERATION("n=\""),
    VOLUME("me=\""),
    PRISE("ce=\""),
    BOOK("k=\""),
    ID("d=\"");


    private String value;
    Params(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
