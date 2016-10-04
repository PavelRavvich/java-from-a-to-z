package pravvich;

public class Triangle {
	public Point a;
	public Point b;
	public Point c;

	public Triangle(Point a, Point b, Point c) 
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}

		
	//calculate the triangle area
	public double area(Point a, Point b, Point c) 
	{
		double result;
		double semiper = ((a.distanseTo(b))*(a.distanseTo(c))*(b.distanseTo(c)) )/2;
		result = Math.sqrt( semiper*(semiper - a.distanseTo(b))*(semiper - a.distanseTo(c))*(semiper - b.distanseTo(c) ));
		return result;
	}
}