package ru.pravvich.models;

import java.util.ArrayList;

public class Item {
    private String nameUser;
    private long create;
    private String header;
    private String description;
    private int id;
    private ArrayList<String> commit = new ArrayList<>();

    public Item() {
    }

    public Item(String header, int id) {
        this.header = header;
        this.id = id;
    }

    public Item(String header) {
        this.header = header;
    }

    public Item(String nameUser, long create, String header,
                String description, int id, ArrayList<String> commit) {
        this.nameUser = nameUser;
        this.create = create;
        this.header = header;
        this.description = description;
        this.id = id;
        this.commit = commit;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommit(ArrayList<String> commit) {
        this.commit = commit;
    }


    public String getNameUser() {
        return nameUser;
    }

    public long getCreate() {
        return create;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getCommits() {
        return commit;
    }
}