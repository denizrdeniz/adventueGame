package com.company;

public class safeHouse extends normalLoc {


    safeHouse(Player player) {
        super(player, "Güvenli Ev");
    }
    public boolean getLocation(){
        player.setHealty(player.getrHealty());
        System.out.println("Şu an Güvenli Ev adlı yerdesiniz. ");
        System.out.println("İyileştiniz... ");
        return true;

    }
}
