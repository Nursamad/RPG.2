package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 5000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {300, 250, 200, 150, 500, 120, 180, 145};
    public static int[] heroesDamage = {75, 55, 35, 0, 15, 10, 30, 40};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical", "Tank", "Lovkach", "Berserk", "Tor"};
    public static int roundNumber = 0;
    public static int medicReability = 25;
    public static Random random = new Random();
    public static int tankTakeDamage = bossDamage / 5;
    public static int toAllDamage = bossDamage - tankTakeDamage;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        System.out.println("Round №" + roundNumber);
        heroesHit();
        lovkachDefence();
        torStan();
        bossHits();
        tankHelps();
        medicReabilytyHit();
        changeDefence();
        berserkBlock();
        printStatistics();
    }

    public static void changeDefence() {
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        if (heroesHealth[randomIndex] > 0) {
            System.out.println("Boss chose:" + bossDefenceType);
        }

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("heroes won!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("boss won!");

        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("____________________________");
        System.out.println("Boss health " + bossHealth + " [ " + bossDamage + "]");

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println();
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] - bossDamage <= 0) {
                heroesHealth[i] = 0;
            } else if (heroesHealth[4] > 0) {
                heroesHealth[i] = heroesHealth[i] - toAllDamage;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }

    }

    public static void medicReabilytyHit() {
        int randomHero = random.nextInt(heroesHealth.length);
        if (heroesHealth[3] > 0 && heroesHealth[randomHero] > 0) {
            if (heroesHealth[randomHero] - bossDamage <= 0) {
                heroesHealth[randomHero] = 0;
            } else {
                heroesHealth[randomHero] = heroesHealth[randomHero] + medicReability;
                System.out.println("\nMedic give: " + heroesAttackType[randomHero] + " [" + medicReability + "]");

            }
        }
    }

    public static void berserkBlock() {
        int block = bossDamage / 2;
        if (heroesHealth[6] - bossDamage <= 0) {
            heroesHealth[6] = 0;
        } else {
            bossHealth = bossHealth - block;
            System.out.println("Berserk damage: " + (heroesDamage[6] + block));
        }
    }


    public static void heroesHit() {
        int coeff = random.nextInt(5);
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical Damage: " + heroesDamage[i] * coeff);
                } else {
                }
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }

        }
    }

    public static void lovkachDefence() {
        boolean chance = random.nextBoolean();
        if (heroesHealth[5] <= 0) {
            heroesHealth[5] = 0;
        } else {
            if (chance) {
                heroesHealth[5] += bossDamage;
                System.out.println("Lucky dodged!");
            }
        }
    }

    public static void tankHelps() {
        if (heroesHealth[4] <= 0) {
            heroesHealth[4] = 0;
        }
        /*  while (heroesHealth[4] > 0 ) {
            for (int i = 0; i < heroesHealth.length; i++) {

                heroesHealth[i] += tankTakeDamage;
            }
            heroesHealth[4] -= tankTakeDamage * heroesHealth.length;
        }
*/
        else {
            heroesHealth[4] -= tankTakeDamage * heroesHealth.length;
            System.out.println("Tank take: " + (tankTakeDamage * (heroesHealth.length - 1)));
        }


    }

    public static void torStan() {
        int stun = random.nextInt(4);
        if (heroesHealth[7] <= 0) {
            heroesHealth[7] = 0;
        } else if (stun == 1) {
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[i] +=  toAllDamage;
            }
            System.out.println("Tor stan boss: " + stun + " round");
        }
    }
}