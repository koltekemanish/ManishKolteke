
package com.traffic.control.system;

import com.traffic.control.system.type.ITrafficSignal;

/** 
 * Represent Green Signal with time for Traffic controller
 * 
 * @author Manish
 * @date 27 Jun 2015
 */
public class GreenSignal implements ITrafficSignal {

    private int time;

    /**
     * Construct the Green signal with initialize zero time 
     */
    public GreenSignal(){
        this.time = 0;
    }
    
    /**
     * Construct the Green signal with specified time 
     */
    public GreenSignal(int time) {
        this.time = time;
    }
    
    /**
     * Set the Green signal time in seconds
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
        return this.time;
    }

    @Override
    public boolean isGreenLight() {
        return true;
    }

    @Override
    public boolean isRedLight() {
        return false;
    }

    @Override
    public boolean isYelloLight() {
        return false;
    }

}
