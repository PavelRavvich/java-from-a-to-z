package ru.pravvich;

public class Triangle {
    public Point a;
    public Point b;
    public Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /*
    Вычисляет площадь треугольника. Использует поля этого класса принимая их значения через конструктор.
    Площадь вычисляется по формуле Герона. Переменная semiper хранит полупериметр.
    Метод так же определяет невозможность построения треугольника в случае если сумма двух
    любых сторон меньше третей, в этом случае возвращает -1.
    */
    public double area() {
        double result, distanseAB, distanseAC, distanseBC;
        distanseAB = a.distanceTo(b);
        distanseAC = a.distanceTo(c);
        distanseBC = b.distanceTo(c);
        if (distanseAB + distanseAC <= distanseBC || distanseAB + distanseBC <= distanseAC || distanseAC + distanseBC <= distanseAB) {
            System.out.println("Такого треугольника не существует!");
            return -1;//тут бы эксепшен выбросить
        } else {
            double semiper;
            semiper = (distanseAB + distanseAC + distanseBC) / 2;
            result = Math.sqrt(semiper * (semiper - distanseAB) * (semiper - distanseAC) * (semiper - distanseBC));
            return result;
        }//if
    }//method
}