package ru.pravvich.timeMoreClients;

class TimeZoneCline {
    private final float start;
    private final float finish;

    TimeZoneCline(float start, float finish) {
        this.start = start;
        this.finish = finish;
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
