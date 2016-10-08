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

		
	//calculate the triangle area
	public double area(Point a, Point b, Point c) {
		double result, semiper, distanseAB, distanseAC, distanseBC;
		
		distanseAB = a.distanceTo(b);
		distanseAC = a.distanceTo(c);
		distanseBC = b.distanceTo(c);

		semiper = (distanseAB + distanseAC + distanseBC)/2;
		result = Math.sqrt(semiper * (semiper - distanseAB) * (semiper - distanseAC) * (semiper - distanseBC));
		return result;
	}
}