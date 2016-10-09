package ru.pravvich;

public class Square {

    private float a;
    private float b;
    private float c;

    private float resultShow;

    public float getResultShow() {
        return resultShow;
    }


    public Square(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    float calculate(float x) {
        float y;
        y = a * x + b * x + c;
        return y;
    }

    void show(float start, float finish, float step) {
        for (float x = start; x >= start && x <= finish; x += step) {
            float result;
            result = calculate(x);
            this.resultShow = result;
            System.out.print(this.resultShow + " ");
        }//for
    }
}