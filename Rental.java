package Projeto;

import java.io.Serializable;

class Rental implements Serializable {
    private static int nextContractNumber = 1;

    private int contractNumber;
    private int rentalTime; // em minutos
    private double totalCost;
    private Equipment equipment;

    public Rental(int rentalTime, Equipment equipment) {
        this.contractNumber = nextContractNumber++;
        this.rentalTime = rentalTime;
        this.equipment = equipment;
        this.totalCost = equipment.calculateRentalCost(rentalTime);
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public int getRentalTime() {
        return rentalTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public Equipment getEquipment() {
        return equipment;
    }
}