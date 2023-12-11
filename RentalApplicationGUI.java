package Projeto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ... (Código das classes Equipment, EquipmentWithoutLesson, EquipmentWithLesson, Rental) ...
//Classe abstrata para representar um equipamento


public class RentalApplicationGUI {
    private List<Rental> rentals;
    private Map<Integer, Equipment> equipmentMap;

    private JTextField timeField;
    private JComboBox<String> equipmentComboBox;
    private JCheckBox lessonCheckBox;
    private JTextArea resultTextArea;

    public RentalApplicationGUI() {
        rentals = new ArrayList<>();
        equipmentMap = new HashMap<>();
        // (Código do mapa de equipamentos permanece o mesmo)
        equipmentMap.put(1, new EquipmentWithLesson(1, "jet ski", 50));
        equipmentMap.put(2, new EquipmentWithLesson(2, "barco pontão", 40));
        equipmentMap.put(3, new EquipmentWithLesson(3, "barco a remo", 15));
        equipmentMap.put(4, new EquipmentWithLesson(4, "canoa", 12));
        equipmentMap.put(5, new EquipmentWithLesson(5, "caiaque", 10));
        equipmentMap.put(6, new EquipmentWithoutLesson(6, "cadeira de praia", 2));
        equipmentMap.put(7, new EquipmentWithoutLesson(7, "guarda-sol", 1));
        equipmentMap.put(8, new EquipmentWithoutLesson(8, "gazebo", 3));
    }
 
        // Inicialização da GUI
        JFrame frame = new JFrame("Rental Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel timeLabel = new JLabel("Tempo de Aluguel (minutos):");
        timeLabel.setBounds(10, 20, 200, 25);
        panel.add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(210, 20, 160, 25);
        panel.add(timeField);

        JLabel equipmentLabel = new JLabel("Equipamento:");
        equipmentLabel.setBounds(10, 50, 200, 25);
        panel.add(equipmentLabel);

        equipmentComboBox = new JComboBox<>();
        for (Equipment equipment : equipmentMap.values()) {
            equipmentComboBox.addItem(equipment.getName());
        }
        equipmentComboBox.setBounds(210, 50, 160, 25);
        panel.add(equipmentComboBox);

        lessonCheckBox = new JCheckBox("Incluir Aula");
        lessonCheckBox.setBounds(10, 80, 200, 25);
        panel.add(lessonCheckBox);

        JButton confirmButton = new JButton("Confirmar Aluguel");
        confirmButton.setBounds(10, 110, 160, 25);
        panel.add(confirmButton);

        resultTextArea = new JTextArea();
        resultTextArea.setBounds(10, 140, 360, 100);
        resultTextArea.setEditable(false);
        panel.add(resultTextArea);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmRental();
            }
        });
    }

    private void confirmRental() {
        try {
            int rentalTime = Integer.parseInt(timeField.getText());
            int equipmentIndex = equipmentComboBox.getSelectedIndex() + 1; // Index começa em 0, IDs começam em 1
            boolean includeLesson = lessonCheckBox.isSelected();

            Equipment selectedEquipment = equipmentMap.get(equipmentIndex);
            Rental rental;

            if (includeLesson && selectedEquipment instanceof EquipmentWithLesson) {
                rental = new Rental(rentalTime, (EquipmentWithLesson) selectedEquipment);
            } else if (!includeLesson && selectedEquipment instanceof EquipmentWithoutLesson) {
                rental = new Rental(rentalTime, (EquipmentWithoutLesson) selectedEquipment);
            } else {
                throw new IllegalArgumentException("Seleção de equipamento ou aula inválida.");
            }

            rentals.add(rental);
            updateResultTextArea();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico para o tempo de aluguel.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void updateResultTextArea() {
        resultTextArea.setText("Aluguéis:\n");
        for (Rental rental : rentals) {
            resultTextArea.append("Contrato #" + rental.getContractNumber() + ": "
                    + rental.getEquipment().getName() + " - "
                    + rental.getRentalTime() + " minutos - "
                    + "$" + rental.getTotalCost() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RentalApplicationGUI();
            }
        });
    }
}
