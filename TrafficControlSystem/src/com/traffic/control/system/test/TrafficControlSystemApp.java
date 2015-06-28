
package com.traffic.control.system.test;

import java.util.Scanner;
import com.traffic.control.system.GreenSignal;
import com.traffic.control.system.Road;
import com.traffic.control.system.YellowSignal;
import com.traffic.control.system.Road.Direction;
import com.traffic.control.system.Road.STATE;
import com.traffic.control.system.controller.TrafficController;
import com.traffic.control.system.type.IRoadType;
import com.traffic.control.system.type.ITrafficSignal;

/**
 * Traffic control system program implements an application that simply
 * display number of cars that are waiting at intersection in each direction at 
 * each seconds for the first N seconds 
 * 
 * @author Manish
 * @date 27 Jun 2015
 */
public class TrafficControlSystemApp {

    /* Traffic control system rules as follow : */
    
    /* Traffic control room will enable the green signal for n seconds */
    public static final int GREEN_SIGNAL = 3;

    /* Traffic control room will enable the yellow signal(Green to Red) for n seconds */
    public static final int YELLOW_SIGNAL = 1; // Car Stoppage

    /* Above two signal is enough to calculate waiting intersection between the road for n seconds*/
    // public static final int RED_SIGNAL = 0; //

    /* Car delay time when signal switching from Red to Green */
    public static final int CAR_DELAY_TIME = 1;
    
    public static void main(String[] args) {
        
        Road snellRoad = new Road("Snell road", IRoadType.TWO_WAY_ROAD);
        snellRoad.setDirection(Direction.NORTH.toString(), Direction.SOUTH.toString());
        snellRoad.setSignal(STATE.GREEN); // 

        Road weaverRoad = new Road("Weaver road", IRoadType.TWO_WAY_ROAD);
        weaverRoad.setDirection(Direction.EAST.toString(), Direction.WEST.toString());
        weaverRoad.setSignal(STATE.YELLOW);

        ITrafficSignal greenSignal = new GreenSignal(GREEN_SIGNAL); // Car Movement
        ITrafficSignal yellowSignal = new YellowSignal(YELLOW_SIGNAL); // Car Stoppage
                
        TrafficController trafficController = new TrafficController();
        trafficController.setGreenSignal(greenSignal);
        trafficController.setYelloSignal(yellowSignal);
        trafficController.setCarDelayTime(CAR_DELAY_TIME); // Car delay time from red to green signal
        trafficController.addRoad(snellRoad);
        trafficController.addRoad(weaverRoad);

        Scanner scanner = new Scanner(System.in);
        System.out.println("************* Welcome to Traffic control system *************");
        System.out.println("Traffic rules --> Green signal : "+GREEN_SIGNAL+" sec(s) and Yellow signal : "+YELLOW_SIGNAL+" sec(s)");
        System.out.println("Please enter the time(secs) to view no. of car waiting at intersection : ");
        int nSeconds = scanner.nextInt();
        scanner.close();
        System.out.println();
        
        displayIntersection(trafficController.calculate(nSeconds));
    }
    
    /**
     * Display all the cars that are waiting at the intersection 
     * for N seconds 
     * @param data
     */
    public static void displayIntersection(String [] data){
        for(String intersection : data) {
            System.out.println(intersection);
        }
    }
}
