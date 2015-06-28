
package com.traffic.control.system.type;

/**
 * Represent all the signal lights which are require to control
 * the Traffic System
 * 
 * @author Manish
 * @date 27 Jun 2015
 */
public interface ITrafficSignal {

    /**
     *  Check whether current signal is green or not 
     * @return
     */
    public boolean isGreenLight();

    /**
     *  Check whether current signal is red or not 
     * @return
     */
    public boolean isRedLight();
    
    /**
     *  Check whether current signal is yellow or not 
     * @return
     */
    public boolean isYelloLight();
}
