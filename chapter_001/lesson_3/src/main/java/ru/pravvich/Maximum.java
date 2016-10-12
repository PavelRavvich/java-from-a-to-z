package ru.pravvich;

public class Maximum {
    /*
    Вычисляет большую из сторон геометрической фигуры
    Принимает объекты типа класса Point это точки на двухмерной оси координат, объекты хранят
    две координаты по осям "х" и "у".
    Применяет к ним метод distanceTo для вычисления расстояния между точками из класса Point.
    Сравнивает стороны в поисках самой большой и возвращает значение большей стороны
    В случае если треугольник равнобедренный возвращает -1
    В случае если треугольник равносторонний возвращает -2
    */
    public double detectionBiggestSideTriangle(Point a, Point b, Point c) {

        //Вычисляем расстояние между точками
        double distanceAB = a.distanceTo(b);
        double distanceAC = a.distanceTo(c);
        double distanceBC = b.distanceTo(c);

        //Сравниваем вычисленные расстояния
        if (distanceAB > distanceAC && distanceAB > distanceBC) {
            return distanceAB;
        } else if (distanceAC > distanceAB && distanceAC > distanceBC) {
            return distanceAC;
        } else if (distanceBC > distanceAB && distanceBC > distanceAC) {
            return distanceBC;
        } else if (distanceBC == distanceAB || distanceBC == distanceAC || distanceAB == distanceAC) {
            return -1; // равнобедренный
        } else {
            return -2; //равносторонний треугольник
        } //else if
    }//method
}
