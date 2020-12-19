package lab04;

import lab03.Divider;

public class Main {
    public static void main(String[] args) {
        Point pointA = new Point(0,0);
        Point pointB = new Point(0,1);
        Point pointC = new Point(0,2);

        try {
            Triangle triangle = new Triangle(pointA, pointB, pointC);

            System.out.println("Area is " + triangle.getArea());
        } catch (Triangle.NotTriangleException e) {

        }
    }
}
