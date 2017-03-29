package ru.pravvich.timeMoreClients;

class TimeZoneCline {
    private final float start;
    private final float finish;
    private int amountCline = 1;

    public int getAmountCline() {
        return amountCline;
    }

    TimeZoneCline(float start, float finish) {
        this.start = start;
        this.finish = finish;
    }

    TimeZoneCline increment() {
        ++this.amountCline;
        return this;
    }

    float getStart() {
        return start;
    }

    float getFinish() {
        return finish;
    }

    @Override
    public String toString() {
        return "TimeZoneCline{" +
                "start=" + start +
                ", finish=" + finish +
                '}';
    }
}
