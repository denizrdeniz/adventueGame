package com.company;

import java.util.Scanner;

public class Game {
    Player Player;
    Location location;
    Scanner scan = new Scanner(System.in);

    public void login() {
        Scanner scan = new Scanner(System.in);
        System.out.println(" Macera Oyununa Hoşgeldiniz !");
        System.out.println(" Oyuna Başlamadan Önce İsminizi Giriniz :");
        String playerName = scan.nextLine();
        Player = new Player("a");
        Player.selectCharacter();
        start();
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("=========================");
            System.out.println();
            System.out.println("Eylem gerçekleştirmek için bir yer seçiniz : ");
            System.out.println("1.Güvenli Ev --> Size ait güvenli bir mekan, düşman yok !! ");
            System.out.println("2.Mağara --> Karşınıza belki zombi çıkabilir ! ");
            System.out.println("3.Orman --> Karşınıza belki vampir çıkabilir ! ");
            System.out.println("4.Nehir --> Karşınıza belki ayı çıkabilir ! ");
            System.out.println("5.Mağaza --> Silah veya Zırh alabilirsiniz ! ");
            System.out.print("Gitmek istediğiniz yer : ");
            int selLoc = scan.nextInt();
            while (selLoc < 0 || selLoc > 5) {
                System.out.print("Lütfen geçerli bir yer seçiniz : ");
                selLoc = scan.nextInt();
            }

            switch (selLoc) {
                case 1:
                    location = new safeHouse(Player);
                    break;
                case 2:
                    location = new Cave(Player);
                    break;
                case 3:
                    location = new Forest(Player);
                    break;
                case 4:
                    location = new River(Player);
                    break;
                case 5:
                    location = new toolStore(Player);
                    break;
                default:
                    location = new safeHouse(Player);
                    break;
            }

            if (location.getClass().getName().equals("SafeHouse")){
                if (Player.getInventory().isFirewood() && Player.getInventory().isfood()&& Player.getInventory().isWater()){
                    System.out.println(" Tebrikler oyunu kazandınız !");
                }
            }
             if (!location.getLocation()){
                System.out.println(" Oyun Bitti !");
                break;
            }
        }
    }
}