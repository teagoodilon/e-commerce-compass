package tables.costumer;

import tables.shoppingcart.ShoppingCartDao;

import java.sql.SQLException;
import java.util.Scanner;

public class CostumerMenu {
    private Scanner scanner;
    private final CostumerDao costumerDao;
    private final ShoppingCartDao shoppingCartDao;

    public CostumerMenu() {
        scanner = new Scanner(System.in);
        costumerDao = new CostumerDao();
        shoppingCartDao = new ShoppingCartDao();
    }
    public void showCostumerMenu() {
        System.out.println("\n\n\n===== Menu de Cliente =====");
        System.out.println("O que você deseja fazer com o Cliente:");
        System.out.println("1. Criar");
        System.out.println("2. Editar");
        System.out.println("3. Apagar um cliente");
        System.out.println("4. Listar um cliente");
        System.out.println("5. Listar todos clientes");
        System.out.println("6. Voltar ao menu principal");
    }

    public void executeProductMenu() throws SQLException {
        int option;
        do {
            showCostumerMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            executeCostumer(option);
            System.out.println();
        } while (option != 6);
    }

    private void executeCostumer(int option) throws SQLException {
        int id;
        Scanner scan = new Scanner(System.in);
        Costumer costumer = new Costumer();
        switch (option){
            case 1:
                System.out.print("Digite o nome do cliente: ");
                costumer.setName(scan.nextLine());
                System.out.print("Digite o email do cliente: ");
                costumer.setEmail(scan.nextLine());
                try {
                    costumerDao.insert(costumer);
                } catch (SQLException e){
                    throw new SQLException(e.getMessage());
                }
                System.out.println("Cliente cadastrado com sucesso!");
                break;
            case 2:
                System.out.print("Digite o id do cliente que você deseja editar: ");
                id = scan.nextInt();
                scan.nextLine();
                Costumer cover = (Costumer) costumerDao.select(id);
                if(cover != null){
                    System.out.print("Digite o novo nome do cliente: ");
                    cover.setName(scan.nextLine());
                    System.out.print("Digite o novo e-mail do cliente: ");
                    cover.setEmail(scan.nextLine());
                    try {
                        costumerDao.update(cover, cover.getId());
                    } catch (SQLException e){
                        throw new SQLException(e.getMessage());
                    }
                    System.out.println("Cliente editado com sucesso!");
                }
                break;
            case 3:
                System.out.print("Digite o id do cliente que você deseja apagar: ");
                id = scan.nextInt();
                scan.nextLine();
                Costumer cover2 = (Costumer) costumerDao.select(id);
                if(cover2 != null){
                    if(shoppingCartDao.select(cover2.getId())!= null){
                        System.out.println("Não é possível apagar o cliente porque ele está associado a um carrinho de compras");
                    } else {
                        costumerDao.delete(cover2.getId());
                        System.out.println("Cliente apagado com sucesso");
                    }
                }
                break;
            case 4:
                System.out.print("Digite o id do cliente que você deseja listar: ");
                id = scan.nextInt();
                Costumer cover1 = (Costumer) costumerDao.select(id);
                if(cover1 != null){
                    System.out.println("Id = " + cover1.getId());
                    System.out.println("Nome = " + cover1.getName());
                    System.out.println("E-mail = " + cover1.getEmail());
                }

                break;
            case 5:
                for (Object obj : costumerDao.select()){
                    if(obj instanceof Costumer c){
                        System.out.println("Id = " + c.getId());
                        System.out.println("Nome = " + c.getName());
                        System.out.println("E-mail = " + c.getEmail());
                    }
                    System.out.println();
                }
                break;
            case 6:
            default:
                System.out.println("Opção inválida, tente novamente");
                break;
        }
    }
}
