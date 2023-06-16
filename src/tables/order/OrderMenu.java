package tables.order;

import java.util.Scanner;

public class OrderMenu {
    private Scanner scanner;

    public OrderMenu() {
        scanner = new Scanner(System.in);
    }
    public void showOrderMenu() {
        System.out.println("\n\n\n===== Menu de Pedido =====");
        System.out.println("O que você deseja fazer com o Pedido:");
        System.out.println("1. Criar");
        System.out.println("2. Editar");
        System.out.println("3. Listar um pedidos");
        System.out.println("4. Listar todos pedidos");
        System.out.println("5. Voltar ao menu principal");
    }

    public void executeOrderMenu() {
        int option;
        do {
            showOrderMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            executeOrder(option);
            System.out.println();
        } while (option != 5);
    }

    private void executeOrder(int option) {
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
