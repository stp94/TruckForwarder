package com.stp94.truckforwarder;

import javafx.collections.ObservableList;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer implements Runnable
{





    long secondsPassed=0;
    long minutePassed=0;
    long hourPassed=0;
    long TimeLimit;

    GameTimer(long TimeLimit)
    {this.TimeLimit=TimeLimit;

    }


    public void run()
            {




                Timer myTimer=new Timer();
                TimerTask task=new TimerTask()
                {

                    @Override
                    public void run()
                    {


                            secondsPassed++;


                            if (secondsPassed == 60) {
                                minutePassed++;
                                secondsPassed = 0;
                            }

                            if (minutePassed == 60) {
                                hourPassed++;
                                minutePassed = 0;
                            }

                            System.out.println(getSecondsPassed());








                    }
                };

                    myTimer.scheduleAtFixedRate(task, 1000, 1000);







            }
    public long getSecondsPassed() {
        return secondsPassed;
    }

    public long getMinutePassed() {
        return minutePassed;
    }

    public long getHourPassed() {
        return hourPassed;
    }
}