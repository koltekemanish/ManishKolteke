
package com.traffic.control.system.type;

/**
 * Represent all the signal direction which are require to control
 * the Traffic System. In future, directions will be two way signal direction
 * (Straight and Left only  /  Straight and Right only  / Left only ....)
 * 
 * This system apply only Straight signal direction
 * 
 * @author Manish
 * @date 27 Jun 2015
 */
public interface ISignalDirection {

    /* only straight line is enable */
    /* Apply STRAIGHT signal for all the cars for specific road */
    public static final boolean DIRECTION_STRAIGHT = Boolean.TRUE;

    /* Apply LEFT signal for all the cars for specific road */
    public static final boolean DIRECTION_LEFT = Boolean.FALSE;

    /* Apply RIGHT signal for all the cars for specific road */
    public static final boolean DIRECTION_RIGHT = Boolean.FALSE;
    
    /**
     * Change traffic signal from :
     *  1) Green to Yellow(or red)
     *  2) Yellow(or Red) to Green
     *  
     *  and also maintain road previous signal state
     */
    public void changeSignal();

}
