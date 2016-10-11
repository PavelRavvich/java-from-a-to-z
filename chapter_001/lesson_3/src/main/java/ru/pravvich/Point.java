package ru.pravvich;

public class Point {
	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	Метод вычисляет расстояние между двумя точками на двухмерной оси координат x,y.
	Вызывается на одном объекте(точке) и принимает в качестве параметра другой объект(точку).
	К полям объекта на котором вызывается метод доступаемся через this.поле так как 
	он у нас основной и на нем и вызывается метод. А к полям принятого объекта доступаемся
	через его имя которое он получает в сигнатуре(that.поле)
	*/	
	public double distanceTo(Point that) {
		double distancePre;
		distancePre = Math.pow((that.y - this.y), 2) + Math.pow((that.x - this.x), 2);
		double distance;
		distance = Math.sqrt(distancePre);
		return distance;
		}
}