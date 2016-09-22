package ru.pravvich;
/**
*Implements Calculate. 
*Supports repeated using previous calculation.
*/
public class Calculate {
	double result;

    public double getResult() {
        return this.result;
    }
	
	public static void main(String[] args) {
	
	
/**
*The add operations.
*@param first - first argument, second - second argument
*/ 

	public void add(double first, double second) {
	this.result = first + second;
	
	}


/**
*substract operation.	
*@param first - first argument, second - second argument
*/ 

	public void substract(double first, double second) {
	this.result = first - second;
	
	}


/**
*Mult operation.
*@param first - first argument, second - second argument
*/ 

	public void mult(double first, double second) {
	this.result = first * second;
	
	}


/**
*Division operation.
*@param first - first argument, second - second argument
*/ 

	public void div(double first, double second) {
	
		if (second != 0) {
		this.result = first / second;
		} else {
		System.out.println("Делить на ноль нельз¤!");
		}
	
	}

//The and...

}



}