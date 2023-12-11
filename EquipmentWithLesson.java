package Projeto;

class EquipmentWithLesson extends Equipment {
    private static final double LESSON_COST = 20.0;

    public EquipmentWithLesson(int id, String name, double basicRate) {
        super(id, name, basicRate);
    }

    @Override
    public double calculateRentalCost(int rentalTime) {
        return (getBasicRate() + LESSON_COST) * rentalTime / 60.0;
    }
}

