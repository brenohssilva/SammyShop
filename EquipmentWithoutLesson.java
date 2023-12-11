package Projeto;

class EquipmentWithoutLesson extends Equipment {
    public EquipmentWithoutLesson(int id, String name, double basicRate) {
        super(id, name, basicRate);
    }

    @Override
    public double calculateRentalCost(int rentalTime) {
        return getBasicRate() * rentalTime / 60.0;
    }
}