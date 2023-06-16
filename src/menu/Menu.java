package menu;

import database.CreateTables;
import tables.costumer.CostumerMenu;
import tables.order.OrderMenu;
import tables.product.ProductMenu;
import tables.shoppingcart.ShoppingCartMenu;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private final ProductMenu productMenu = new ProductMenu();
    private final CostumerMenu costumerMenu = new CostumerMenu();
    private final ShoppingCartMenu shoppingCartMenu = new ShoppingCartMenu();
    private final OrderMenu orderMenu = new OrderMenu();

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("===== Menu Principal =====");
        System.out.println("Qual desses objetos você deseja manipular?");
        System.out.println("1. Produto");
        System.out.println("2. Cliente");
        System.out.println("3. Carrinho de Compras");
        System.out.println("4. Pedidos");
        System.out.println("5. Sair");
    }

    public void executeOption(int option) throws SQLException{
        switch (option) {
            case 1:
                productMenu.executeProductMenu();
                break;
            case 2:
                costumerMenu.executeProductMenu();
                break;
            case 3:
                shoppingCartMenu.executeShoppingCartMenu();
                break;
            case 4:
                orderMenu.executeOrderMenu();
                break;
            case 5:
                System.out.println("Encerrando o programa...");
                System.out.println("Até mais!");
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
                break;
        }
    }

    public void execute() throws SQLException {
        int option;
        System.out.println("Bem-Vindo ao Odilon's E-commerce! \n");
        CreateTables.start();
        do {
            showMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            executeOption(option);

            System.out.println();
        } while (option != 5);

        scanner.close();
    }
}
