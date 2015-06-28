
package com.traffic.control.system;

import com.traffic.control.system.type.ITrafficSignal;

/** 
 * Represent Red Signal with time for Traffic controller
 * 
 * @author Manish
 * @date 27 Jun 2015
 */
public class RedSignal implements ITrafficSignal {

    private int time;

    /**
     * Construct the Red signal with initialize zero time 
     */
    public RedSignal() {
        this.time = 0;
    }

    /**
     * Construct the Red signal with specified time 
     */
    public RedSignal(int time) {
        this.time = time;
    }
    
    /**
     * Set the Red signal time in seconds
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**
     * Get the Red signal time in seconds
     * @return
     */
    public int getTime() {
        return time;
    }

    @Override
    public boolean isGreenLight() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isRedLight() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isYelloLight() {
        // TODO Auto-generated method stub
        return false;
    }

}
