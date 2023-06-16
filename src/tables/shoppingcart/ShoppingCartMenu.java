package tables.shoppingcart;

import java.util.Scanner;

public class ShoppingCartMenu {

    private Scanner scanner;

    public ShoppingCartMenu() {
        scanner = new Scanner(System.in);
    }

    public void showShoppingCartMenu() {
        System.out.println("\n\n\n===== Menu do Carrinho de compras =====");
        System.out.println("O que você deseja fazer com o Carrinho de compras:");
        System.out.println("1. Criar");
        System.out.println("2. Editar");
        System.out.println("3. Listar um Carrinho de compras");
        System.out.println("4. Listar todos Carrinho de compras");
        System.out.println("5. Voltar ao menu principal");
    }

    public void executeShoppingCartMenu() {
        int option;
        do {
            showShoppingCartMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            executeShoppingCart(option);
            System.out.println();
        } while (option != 5);
    }

    private void executeShoppingCart(int option) {
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
