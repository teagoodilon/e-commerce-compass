package tables.costumer;

import java.util.Scanner;

public class CostumerMenu {
    private Scanner scanner;

    public CostumerMenu() {
        scanner = new Scanner(System.in);
    }
    public void showCostumerMenu() {
        System.out.println("\n\n\n===== Menu de Cliente =====");
        System.out.println("O que você deseja fazer com o Cliente:");
        System.out.println("1. Criar");
        System.out.println("2. Editar");
        System.out.println("3. Listar um cliente");
        System.out.println("4. Listar todos clientes");
        System.out.println("5. Voltar ao menu principal");
    }

    public void executeProductMenu() {
        int option;
        do {
            showCostumerMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            executeCostumer(option);
            System.out.println();
        } while (option != 5);
    }

    private void executeCostumer(int option) {
        switch (option){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}
