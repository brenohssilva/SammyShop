package Projeto;



// Classe abstrata para representar um equipamento
abstract class Equipment {
    private int id;
    private String name;
    private double basicRate;

    public Equipment(int id, String name, double basicRate) {
        this.id = id;
        this.name = name;
        this.basicRate = basicRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasicRate() {
        return basicRate;
    }

    // Método abstrato para calcular o valor total do aluguel
    public abstract double calculateRentalCost(int rentalTime);
}