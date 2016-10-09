package ru.pravvich;

public class Maximum {

    private String biggestSide;

    public void setBiggestSide(String biggestSide) {
        this.biggestSide = biggestSide;
    }

    public String getBiggestSide() {
        return biggestSide;
    }

    public String detectionBiggestSideTriangle(Point a, Point b, Point c) {

        double distanceAB = a.distanceTo(b);
        double distanceAC = a.distanceTo(c);
        double distanceBC = b.distanceTo(c);

        if (distanceAB > distanceAC && distanceAB > distanceBC) {
            String distanceBetweenAB = "Сторона между точками А и В самая большая.";
            setBiggestSide(distanceBetweenAB);
            return distanceBetweenAB;
        } else if (distanceAC > distanceAB && distanceAC > distanceBC) {
            String distanceBetweenAC = "Сторона между точками А и С самая большая.";
            setBiggestSide(distanceBetweenAC);
            return distanceBetweenAC;
        } else if (distanceBC > distanceAB && distanceBC > distanceAC) {
            String distanceBetweenBС = "Сторона между точками B и С самая большая.";
            setBiggestSide(distanceBetweenBС);
            return distanceBetweenBС;
        } else if (distanceBC == distanceAB || distanceBC == distanceAC || distanceAB == distanceAC) {
            String isoscelesTriangle = "Это равнобедренный треугольник.";
            setBiggestSide(isoscelesTriangle);
            return isoscelesTriangle;
        } else {
            String equilateralTriangle = "Это равносторонний треугольник.";
            setBiggestSide(equilateralTriangle);
            return equilateralTriangle;
        } //else if
    }//method
}
