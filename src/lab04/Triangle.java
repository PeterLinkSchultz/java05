package lab04;

public class Triangle {
    private final Point a;
    private final Point b;
    private final Point c;
    double ab;
    double bc;
    double ca;

    Triangle(Point pointA, Point pointB, Point pointC) throws NotTriangleException {
        a = pointA;
        b = pointB;
        c = pointC;

        ab = pointA.getLengthToPoint(pointB);
        bc = pointB.getLengthToPoint(pointC);
        ca = pointC.getLengthToPoint(pointA);

        if (ab + bc <= ca || bc + ca <= ab || ca + ab <= bc) {
            throw new NotTriangleException(pointA.toString() + pointB.toString() + pointC.toString());
        }
    }

    double getArea() {
        double halfP = getPerimeter() / 2;

        return Math.sqrt(halfP * (halfP - ab) * (halfP - bc) * (halfP - ca));
    }

    double getPerimeter() {
        return ab + bc + ca;
    }

    Point getMediansPoint() {
        return new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
    }

    static class NotTriangleException extends Exception {
        NotTriangleException(String message) {
            super(message);
            System.out.println("This is not triangle with points: " + message);
        }
    }
}
