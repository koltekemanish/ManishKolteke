
package com.traffic.control.system;

import com.traffic.control.system.type.IRoadType;

/**
 * Represent Road which can be any type ONE or TWO way road and follows 
 * traffic rules as per traffic system. 
 * 
 * @author Manish
 */
public class Road implements IRoadType {

    
    /*  Four type of road directions */
    public enum Direction {

        EAST("E"), WEST("W"), NORTH("N"), SOUTH("S");

        private String value;

        private Direction(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    /* Movement which follows traffic signal rule */
    public enum MOVEMENT {
        MOVING, STOP
    };

    /* Signal light states */
    public enum STATE {
        GREEN, YELLOW, RED
    };
    
    @SuppressWarnings("unused")
    private String name;

    private int roadType;

    private String[] direction;

    private int car;

    private STATE signal;

    private STATE previous;

    /**
     * Construct an empty Road  
     */
    public Road() {
        car = 0;
    }

    /**
     * Construct the Road with specified name and road type
     * @param name
     * @param roadType
     */
    public Road(String name, int roadType) {
        this.name = name;
        car = 0;
        this.roadType = roadType;
    }

    @Override
    public void setRoadName(String name) {
        this.name = name;
    }

    @Override
    public void setRoadType(int type) {
        this.roadType = type;
    }

    /**
     *  Get the type of road
     * @return
     */
    public int getRoadType() {
        return roadType;
    }

    @Override
    public void setDirection(String first) {

        direction = new String[1];
        direction[0] = first.toString();
        
        int roadCount = (roadType == IRoadType.TWO_WAY_ROAD) ? 2 : 1; 
        if(direction.length != roadCount){
            throw new IllegalArgumentException("Please check the Road type and Direction, It should be same");
        }
    }

    @Override
    public void setDirection(String first, String second) {

        direction = new String[2];
        direction[0] = first;
        direction[1] = second;
        
        int roadCount = (roadType == IRoadType.TWO_WAY_ROAD) ? 2 : 1; 
        if(direction.length != roadCount){
            throw new IllegalArgumentException("Please check the Road type and Direction, It should be same");
        }
        
        if(first.equalsIgnoreCase(second)){
            throw new IllegalArgumentException("Direction will not be same, Change the direction !");
        }
    }

    /**
     * Get the road direction name list (EAST or WEST or NORTH or SOUTH) 
     * @return
     */
    public String[] getDirection() {
        return direction;
    }

    public int getCar() {
        return car;
    }

    /**
     * Increment the car counter when the Car state is STOP otherwise nothing
     * @param move
     */
    public void movement(MOVEMENT move) {
        if (move == MOVEMENT.STOP) {
            car++;
        } else {
            // nothing
        }
    }

    /**
     * Set the road signal state
     * @param signal
     */
    public void setSignal(STATE signal) {
        this.signal = signal;
    }
    
    /**
     * Get the road signal state
     * @return
     */
    public STATE getSignal() {
        return signal;
    }

    /**
     * Get the previous state for changing the traffic signal 
     * @return
     */
    public STATE getPreviousSignal() {
        return previous;
    } 
    
    /**
     * Set the previous state for changing the traffic signal
     * @param previous
     */
    public void setPreviousSignal(STATE previous) {
        this.previous = previous;
    } 
}
