package ru.pravvich;

public class Point {
	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
		
	public double distanceTo(Point that) {
		double thatX, thatY, thisX, thisY;
		
		thisX = this.x;
		thisY = this.y;
		thatX = that.x;
		thatY = that.y;
		
		double distancePre;
		distancePre = Math.pow((thatY - thisY), 2) + Math.pow((thatX - thisX), 2);
		
		double distance;
		distance = Math.sqrt(distancePre);
		
		return distance;
		}
}