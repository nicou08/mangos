package etphoneshome.objects;

public class Location {
    /* This is the location class that holds 2-Dimensional coordinates and
     * calculates distances between objects of this particular class
     */
    private int xcord;
    private int ycord;

    public Location(Location location) {
        this.xcord = location.getXcord();
        this.ycord = location.getYcord();
    }

    public Location(int xCord, int yCord) {
        this.xcord = xCord;
        this.ycord = yCord;
    }

    public int getXcord() {
        return xcord;
    }

    public int getYcord() {
        return ycord;
    }

/*    public void setXcord(int x) {
        xcord = x;
    }

    public void setYcord(int y) {
        ycord = y;
    }

    public Location addX(int x) {
        return this.add(x, 0);
    }

    public Location addY(int y) {
        return this.add(0, y);
    }

    public Location add(int x, int y) {
        this.xcord += x;
        this.ycord += y;
        return this;
    }*/

    public double getDistance(Location m) {
        /* The if statements are used to determine which value in the same axis is bigger
         * in order to not obtain negative differences
         */
        double xdif, ydif, distance;
        int x1 = m.getXcord();
        int y1 = m.getYcord();
        if (x1 > xcord) {
            xdif = x1 - xcord;
        } else if (xcord > x1) {
            xdif = xcord - x1;
        } else // x1 ==x2
        {
            xdif = 0;
        }
        if (y1 > ycord) {
            ydif = y1 - ycord;
        } else if (ycord > y1) {
            ydif = ycord - y1;
        } else // y1 == y2
        {
            ydif = 0;
        }
        //Using Pythagoras' theorem
        distance = Math.sqrt(xdif * xdif + ydif * ydif);
        return distance;
    }

    public String toString() {
        return "Location: {x = " + xcord + ", y = " + ycord + "}";
    }

    public static void main(String args[]) {
        Location loc = new Location(1, 2);
        System.out.println("Object One X-Coordinate: " + loc.getXcord());
        Location loc2 = new Location(0, 0);
        Location loc3 = new Location(3, 4);
        double distance2to3 = loc2.getDistance(loc3);
        System.out.println("The distance from ObjectTwo to ObjectThree is: " + distance2to3);

    }

}