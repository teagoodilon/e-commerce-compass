package tables.product;

import java.util.Scanner;

public class ProductMenu {
    private Scanner scanner;

    public ProductMenu() {
        scanner = new Scanner(System.in);
    }

    public void showProductMenu() {
        System.out.println("\n\n\n===== Menu de Produtos =====");
        System.out.println("O que você deseja fazer com o Produto:");
        System.out.println("1. Criar");
        System.out.println("2. Editar");
        System.out.println("3. Listar um produto");
        System.out.println("4. Listar todos produtos");
        System.out.println("5. Voltar ao menu principal");
    }

    public void executeProductMenu() {
        int option;
        do {
            showProductMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            executeProduct(option);
            System.out.println();
        } while (option != 5);
    }

    private void executeProduct(int option) {
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
