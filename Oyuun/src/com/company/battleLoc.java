package com.company;

public abstract class battleLoc extends Location{
    protected Obstacle obstacle;
    protected String award;

    battleLoc(Player player, String name, Obstacle obstacle, String award) {
        super(player);
        this.obstacle=obstacle;
        this.name=name;
        this.award=award;
    }
    public boolean getLocation() {
        int obsCount = obstacle.count();
        System.out.println("Şu an buradasınız :  " + this.getName());
        System.out.println("Dikkatli ol ! Burada " + obsCount + " Tane " + obstacle.getName() + " Yaşıyor ! ");
        System.out.print("<S>avaş veya <K>aç :");
        String selCase = scan.nextLine();
        selCase = selCase.toUpperCase();
        if (selCase.equals("S")) {
            if (combat(obsCount)) {
                System.out.println(this.getName() + " Bölgesindeki tüm düşmanları temizlediniz !");
                if (this.award.equals("Food") && player.getInventory().isFood() == false) {
                    System.out.println(this.award + " Kazandınız ! ");
                    player.getInventory().setFood(true);
                } else if (this.award.equals("Water") && player.getInventory().isWater() == false) {
                    System.out.println(this.award + " Kazandınız !");
                    player.getInventory().setWater(true);
                } else if (this.award.equals("Firewood") && player.getInventory().isFirewood() == false) {
                    System.out.println(this.award + " Kazandınız !");
                    player.getInventory().setFirewood(true);
                }
                return true;
            }
            if (player.getHealty() <= 0) {
                System.out.println("Öldünüz !");
                return false;
            }
        }
        return true;
    }

    public boolean combat(int obsCount){
     for (int i = 0 ; i < obsCount; i++){
         int defObsHealth = Obstacle.getHealth();
         playerStats();
         enemyStats();

         while (player.getHealty() > 0 && obstacle.getHealth() > 0){
             System.out.print("<V>ur && <K>aç :");
             String selCase = scan.nextLine();
             selCase = selCase.toUpperCase();
             if (selCase.equals("V")){
                 System.out.println();
                 System.out.println("Siz vurdunuz ! ");
                 obstacle.setHealth(obstacle.getHealth()-player.getTotalDamage());
                 afterHit();
                 if (obstacle.getHealth() > 0){
                     System.out.println("Canavar size vurdu !");
                     player.setHealty(player.getHealty() - (obstacle.getDamage()- player.getInventory().getArmor()));
                     afterHit();
                 }

             }else{
                 return false;
             }
         }
         if (obstacle.getHealth() < player.getHealty() ){
             System.out.println("Düşmanı yendiniz !");
             player.setMoney(player.getMoney() + obstacle.getAward());
             System.out.println("Güncel Paranız :" + player.getMoney());
             obstacle.setHealth(defObsHealth);
         }else{
             return false;
         }
         System.out.println("------------------------------------------");
     }
    return true;
  }
  public void playerStats(){
      System.out.println();
      System.out.println("Oyuncu Değerleri\n-------------- ");
      System.out.println("Can:" + player.getHealty());
      System.out.println("Hasar:" + player.getTotalDamage());
      System.out.println("Para:" + player.getMoney());
     if (player.getInventory().getDamage() > 0 ){
         System.out.println("Silah:" + player.getInventory().getwName());
     }
      if (player.getInventory().getArmor() > 0 ){
          System.out.println("Zırh:" + player.getInventory().getwName());
      }
  }
  public void enemyStats(){
      System.out.println();
      System.out.println(obstacle.getName() + " Değerleri\n--------------");
      System.out.println("Can:" + obstacle.getHealth());
      System.out.println("Hasar:" + obstacle.getDamage());
      System.out.println("Ödül:" + obstacle.getAward());
  }
  public void afterHit(){
      System.out.println("Oyuncu canı : " + player.getHealty());
      System.out.println(obstacle.getName() + " Canı :" + obstacle.getHealth());
      System.out.println();

  }
}
