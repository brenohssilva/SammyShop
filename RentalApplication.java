package Projeto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RentalApplication<ObjectOutputStream> {
    private List<Rental> rentals;
    private Map<Integer, Equipment> equipmentMap;

    public RentalApplication() {
        rentals = new List<>();
        equipmentMap = new Map<>();

        // Adicionando equipamentos ao mapa
        equipmentMap.put(1, new EquipmentWithLesson(1, "jet ski", 50));
        equipmentMap.put(2, new EquipmentWithLesson(2, "barco pontão", 40));
        equipmentMap.put(3, new EquipmentWithLesson(3, "barco a remo", 15));
        equipmentMap.put(4, new EquipmentWithLesson(4, "canoa", 12));
        equipmentMap.put(5, new EquipmentWithLesson(5, "caiaque", 10));
        equipmentMap.put(6, new EquipmentWithoutLesson(6, "cadeira de praia", 2));
        equipmentMap.put(7, new EquipmentWithoutLesson(7, "guarda-sol", 1));
        equipmentMap.put(8, new EquipmentWithoutLesson(8, "gazebo", 3));
    }

    // Método para salvar os aluguéis em um arquivo
    public void saveRentalsToFile() {
        if (rentals == null) {
            System.out.println("A lista de aluguéis está nula. Não é possível salvar.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rentals.ser"))) {
            oos.writeObject(rentals);
            System.out.println("Aluguéis salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar aluguéis: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Método para criar a GUI
    public void createGUI() {
        // Implemente a interface gráfica aqui
        // Use um combobox para listar os equipamentos, campos de texto para inserir o tempo de aluguel, etc.
        // Ao confirmar o aluguel, crie um objeto Rental e adicione à lista de aluguéis
    }

    public static void main(String[] args) {
        RentalApplication rentalApp = new RentalApplication();
        rentalApp.createGUI();
        // Encerre a aplicação e salve os aluguéis ao finalizar
        rentalApp.saveRentalsToFile();
    }
}