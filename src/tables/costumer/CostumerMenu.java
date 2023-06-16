package tables.costumer;

import java.sql.SQLException;
import java.util.Scanner;

public class CostumerMenu {
    private Scanner scanner;
    private CostumerDao costumerDao;

    public CostumerMenu() {
        scanner = new Scanner(System.in);
        costumerDao = new CostumerDao();
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

    public void executeProductMenu() throws SQLException {
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
                    System.out.println("Produto editado com sucesso!");
                }
                break;
            case 3:
                System.out.print("Digite o id do cliente que você deseja listar: ");
                id = scan.nextInt();
                Costumer cover1 = (Costumer) costumerDao.select(id);
                if(cover1 != null){
                    System.out.println("Id = " + cover1.getId());
                    System.out.println("Nome = " + cover1.getName());
                    System.out.println("E-mail = " + cover1.getEmail());
                }

                break;
            case 4:
                for (Object obj : costumerDao.select()){
                    if(obj instanceof Costumer c){
                        System.out.println("Id = " + c.getId());
                        System.out.println("Nome = " + c.getName());
                        System.out.println("E-mail = " + c.getEmail());
                    }
                    System.out.println();
                }
                break;
            case 5:
            default:
                System.out.println("Opção inválida, tente novamente");
                break;
        }
    }
}
