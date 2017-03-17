package demoGC;

import java.util.ArrayList;

class User {
    private String fieldOne = "abc";
    private String fieldTwo = "cba";

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}

class GCStart {
    public void checkGC() {
        ArrayList<User> list = new ArrayList<>();

        for (int i = 0; i < 55000; i++) {
            list.add(new User());
        }

        info();
        //list = null;
        System.gc();

        info();
    }

    private static void info() {
        Runtime runtime = Runtime.getRuntime();
        int mb = 1024 * 1024;

        System.out.println("MEMORY STATE");

        //Print used memory
        System.out.println("Used memory: "
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free memory: "
                + (runtime.freeMemory()) / mb);

        //Print total available memory
        System.out.println("Total memory: "
                + (runtime.totalMemory()) / mb);

        //Print maximum available memory
        System.out.println("Max memory: "
                + runtime.maxMemory() / mb);
    }

    public static void main(String[] args) {
        GCStart gcStart = new GCStart();
        gcStart.checkGC();
    }
}
