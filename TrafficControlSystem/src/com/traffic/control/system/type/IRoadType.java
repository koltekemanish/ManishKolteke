
package com.traffic.control.system.type;

/**
 * Represent Road types and their direction for Traffic control
 * System
 * 
 * @author Manish
 * @date 27 Jun 2015
 */
public interface IRoadType {

    /* Road apply only one way road like EAST or WEST or NORTH or SOUTH */
    public static final int ONE_WAY_ROAD = 1;

    /* Road apply two way road like EAST & WEST or NORTH & SOUTH */
    public static final int TWO_WAY_ROAD = 2;

    /* Represent the road */
    public void setRoadName(String name);

    /* Represent the road type */
    public void setRoadType(int type);

    /* Represent the one way road direction */
    public void setDirection(String first);

    /* Represent the two way road direction */
    public void setDirection(String first, String second);
}
