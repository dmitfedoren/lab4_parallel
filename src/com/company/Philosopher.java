package com.company;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private int id;
    private Semaphore[] forks;

    public Philosopher(int id, Semaphore[] forks) {
        this.id = id;
        this.forks = forks;
    }

    @Override
    public void run() {
        int idLeftFork = id;
        int idRightFork = (id + 1) % 5;

        for (int i = 1; i <= 10; i++) {
            System.out.println("Philosopher " + id + " thinking " + i + " time");
            try {
                if (id == 4) { // Останній філософ
                    forks[idRightFork].acquire();
                    System.out.println("Philosopher " + id + " took right fork");
                    forks[idLeftFork].acquire();
                    System.out.println("Philosopher " + id + " took left fork");
                } else {
                    forks[idLeftFork].acquire();
                    System.out.println("Philosopher " + id + " took left fork");
                    forks[idRightFork].acquire();
                    System.out.println("Philosopher " + id + " took right fork");
                }

                System.out.println("Philosopher " + id + " eating " + i + " time");

                if (id == 4) {
                    forks[idLeftFork].release();
                    System.out.println("Philosopher " + id + " put left fork");
                    forks[idRightFork].release();
                    System.out.println("Philosopher " + id + " put right fork");
                } else {
                    forks[idRightFork].release();
                    System.out.println("Philosopher " + id + " put right fork");
                    forks[idLeftFork].release();
                    System.out.println("Philosopher " + id + " put left fork");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}