package com.stp94.truckforwarder;

public class player {
    private String player_Name;
    private double player_Cash;
    private double player_Respect;

    player(String player_Name, double player_Cash, double player_Respect)
    {
        this.player_Name=player_Name;
        this.player_Cash=player_Cash;
        this.player_Respect=player_Respect;
    }

    String getPlayer_Name() {
        return player_Name;
    }

    public void setPlayer_Name(String player_Name) {
        this.player_Name = player_Name;
    }

    double getPlayer_Cash() {
        return player_Cash;
    }

    void setPlayer_Cash(double player_Cash) {
        this.player_Cash = player_Cash;
    }

    double getPlayer_Respect() {
        return player_Respect;
    }

    public void setPlayer_Respect(double player_Respect) {
        this.player_Respect = player_Respect;
    }
}
