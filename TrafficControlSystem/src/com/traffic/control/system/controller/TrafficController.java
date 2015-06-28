
package com.traffic.control.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.traffic.control.system.GreenSignal;
import com.traffic.control.system.Road;
import com.traffic.control.system.Road.MOVEMENT;
import com.traffic.control.system.Road.STATE;
import com.traffic.control.system.YellowSignal;
import com.traffic.control.system.type.ISignalDirection;
import com.traffic.control.system.type.IRoadType;
import com.traffic.control.system.type.ITrafficSignal;

/**
 *  Represent Traffic controller to control the traffic on roads according 
 *  to traffic system rules.
 * 
 * @author Manish 
 * @date 27 Jun 2015
 */
public class TrafficController implements ISignalDirection {

    private GreenSignal greenSignal;

    private YellowSignal yellowSignal;

    //private RedSignal redSignal;

    private int carDelayTime;

    private List<Road> allRoad;

    /**
     * Constructs an empty Traffic controller with road initialization
     */
    public TrafficController() {
        allRoad = new ArrayList<Road>(2);
    }

    /**
     * Constructs a Traffic controller with the specified initial Green signal, Yellow signal,
     * Red signal and Car delay time
     * 
     * @param greenSignal
     * @param yellowSignal
     * @param redSignal
     * @param carDelayTime
     */
    public TrafficController(ITrafficSignal greenSignal, ITrafficSignal yellowSignal,
            ITrafficSignal redSignal, int carDelayTime) {
        allRoad = new ArrayList<Road>(2);
        
        this.greenSignal = (GreenSignal)greenSignal;
        this.yellowSignal = (YellowSignal) yellowSignal;
        //this.redSignal = (RedSignal) redSignal;
        this.carDelayTime = carDelayTime;
    }

    /**
     * Add the new road to Traffic control system
     * @param road
     */
    public void addRoad(Road road) {
        allRoad.add(road);
        
        if(allRoad.size() > 2)
            throw new ArrayIndexOutOfBoundsException("Only two roads are allowed (either one way or two way)");
    }

    /**
     * Initializing new green signal
     * @param greenSignal
     */
    public void setGreenSignal(ITrafficSignal greenSignal) {
        this.greenSignal = (GreenSignal)greenSignal;
    }

    /**
     * Initializing new yellow signal
     * @param yellowSignal
     */
    public void setYelloSignal(ITrafficSignal yellowSignal) {
        this.yellowSignal = (YellowSignal)yellowSignal;
    }

    /**
     * Set the car delay time while moving from Red to Green Signal
     * @param carDelayTime
     */
    public void setCarDelayTime(int carDelayTime) {
        this.carDelayTime = carDelayTime;
    }

    /**
     *  This method is calculating waiting cars at the intersection for
     *  n seconds using Traffic control system rules.
     *  Rules can be modified at any time by the traffic controller 
     * 
     * @param seconds
     * @return No. of wating cars at intersection
     */
    public String[] calculate(int seconds) {

        int roadSize = allRoad.size();
        
        // Counters for calculating intersection
        int cnt_green_signal = 0;
        int cnt_yellow_signal = 0;
        int cnt_car_delay = greenSignal.getTime();

        // Setting up the previous signal value 
        for (int i = 0; i < roadSize; i++) {
            allRoad.get(i).setPreviousSignal(allRoad.get(i).getSignal());
        }

        // Adding this for seperating controller and presenstation logic 
        String[] intersectionData = new String[seconds+1];
        
        for (int i = 0; i <= seconds; i++) {

            StringBuffer ouput = new StringBuffer();
            ouput.append(i + "  ");
            if (allRoad.get(0).getRoadType() == IRoadType.ONE_WAY_ROAD) {
                ouput.append("  " + allRoad.get(0).getDirection()[0] + "="
                        + allRoad.get(0).getCar());
            } else {
                ouput.append("  " + allRoad.get(0).getDirection()[0] + "="
                        + allRoad.get(0).getCar());
                ouput.append("  " + allRoad.get(0).getDirection()[1] + "="
                        + allRoad.get(0).getCar());
            }

            if (allRoad.get(1).getRoadType() == IRoadType.ONE_WAY_ROAD) {
                ouput.append("  " + allRoad.get(1).getDirection()[0] + "="
                        + allRoad.get(1).getCar());
            } else {
                ouput.append("  " + allRoad.get(1).getDirection()[0] + "="
                        + allRoad.get(1).getCar());
                ouput.append("  " + allRoad.get(1).getDirection()[1] + "="
                        + allRoad.get(1).getCar());
            }

            intersectionData[i] = ouput.toString();
            
            if (cnt_green_signal >= greenSignal.getTime() && cnt_yellow_signal >= yellowSignal.getTime()) {
                changeSignal();
                cnt_yellow_signal = 0;
                cnt_green_signal = 1;
                cnt_car_delay = 0;

            } else if (cnt_green_signal >= greenSignal.getTime()) {

                // Switch both signal to Yellow(or Red) 
                if (allRoad.get(0).getSignal() == STATE.GREEN) {
                    allRoad.get(0).setSignal(STATE.YELLOW);
                } else if (allRoad.get(1).getSignal() == STATE.GREEN) {
                    allRoad.get(1).setSignal(STATE.YELLOW);
                }
                cnt_yellow_signal++;
            } else {
                cnt_green_signal++;
            }

            if (allRoad.get(0).getSignal() == STATE.YELLOW) {
                allRoad.get(0).movement(MOVEMENT.STOP);
            } else if (allRoad.get(0).getSignal() == STATE.GREEN) {
                if (cnt_car_delay >= carDelayTime) {
                    allRoad.get(0).movement(MOVEMENT.MOVING);
                } else {
                    allRoad.get(0).movement(MOVEMENT.STOP);
                    cnt_car_delay++;
                }
            }

            if (allRoad.get(1).getSignal() == STATE.YELLOW) {
                allRoad.get(1).movement(MOVEMENT.STOP);
            } else if (allRoad.get(1).getSignal() == STATE.GREEN) {
                if (cnt_car_delay >= carDelayTime) {
                    allRoad.get(1).movement(MOVEMENT.MOVING);
                } else {
                    allRoad.get(1).movement(MOVEMENT.STOP);
                    cnt_car_delay++;
                }
            }

        }
        return intersectionData;
    }

    @Override
    public void changeSignal() {

        if (allRoad.get(0).getPreviousSignal() == STATE.GREEN) {

            allRoad.get(0).setSignal(STATE.YELLOW);
            allRoad.get(0).setPreviousSignal(STATE.YELLOW);

            allRoad.get(1).setSignal(STATE.GREEN);
            allRoad.get(1).setPreviousSignal(STATE.GREEN);

        } else if (allRoad.get(1).getPreviousSignal() == STATE.GREEN) {

            allRoad.get(0).setSignal(STATE.GREEN);
            allRoad.get(0).setPreviousSignal(STATE.GREEN);

            allRoad.get(1).setSignal(STATE.YELLOW);
            allRoad.get(1).setPreviousSignal(STATE.YELLOW);
        }

    }

}
