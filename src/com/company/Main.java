package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {300, 250, 95, 150};
    public static int[] heroesDamage = {50, 45, 40, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical"};
    public static int roundNumber = 0;
    public static int medicReability = 25;

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
        bossHits();
        medicReabilytyHit();
        printStatistics();
        changeDefence();
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose:" + bossDefenceType);

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

        for (int i = 0; i < heroesHealth.length; i++)
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        System.out.println("Medic give " + heroesHealth.length + " [" + medicReability + "]");

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] - bossDamage <= 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;

            }
        }

    }

    public static void medicReabilytyHit() {
        for (int i = 0; i < heroesHealth.length; i++) {

            if (heroesHealth[3] > 0 && heroesHealth[i] - bossDamage < heroesHealth[i]) {
                if (heroesHealth[i] - bossDamage <= 0) {
                    heroesHealth[i] = 0;
                } else heroesHealth[i] = heroesHealth[i] + medicReability;
            }
        }
    }

    public static void heroesHit() {
        Random random = new Random();
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
}
// проблемы : нанесение урона после смерти