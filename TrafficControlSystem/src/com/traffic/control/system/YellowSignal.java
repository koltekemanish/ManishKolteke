
package com.traffic.control.system;

import com.traffic.control.system.type.ITrafficSignal;

/** 
 * Represent Yellow Signal with time for Traffic controller
 * 
 * @author Manish
 * @date 27 Jun 2015
 */
public class YellowSignal implements ITrafficSignal {

    private int time;

    /**
     * Construct the Yellow signal with initialize zero time 
     */
    public YellowSignal() {
        this.time = 0;
    }

    /**
     * Construct the Yellow signal with specified time 
     */
    public YellowSignal(int time) {
        this.time = time;
    }

    /**
     * Set the Yellow signal time in seconds
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**
     * Get the Green signal time in seconds
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
        return false;
    }

    @Override
    public boolean isYelloLight() {
        // TODO Auto-generated method stub
        return false;
    }

}
