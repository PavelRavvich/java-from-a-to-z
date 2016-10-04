package pravvich;

public class Point {
	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
		
	public double distanceTo(Point that) {//принимает объект типа Point и называет that
		//calculate distance between two points
		double distance;
		return distance = Math.sqrt((Math.pow(that.y - this.y), 2) + (Math.pow(that.x - this.x), 2));
		}
}