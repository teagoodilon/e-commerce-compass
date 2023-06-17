package tables.product;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductMenu {
    private Scanner scanner;
    private ProductDao productDao;
    public ProductMenu() {
        scanner = new Scanner(System.in);
        productDao = new ProductDao();
    }

    public void stockProducts() throws SQLException {
        System.out.println("Lista de produtos disponíveis: ");
        for (Object obj : productDao.selectAvaible()) {
            if (obj instanceof Product p) {
                System.out.print("Id = " + p.getId() + " - ");
                System.out.print("Nome = " + p.getName() + " - ");
                System.out.print("Preço = R$" + p.getPrice() + " - ");
                System.out.print("Quantidade = " + p.getQuantity());
            }
            System.out.println();
        }
    }

    public void showProductMenu() {
        System.out.println("\n\n\n===== Menu de Produtos =====");
        System.out.println("O que você deseja fazer com o Produto:");
        System.out.println("1. Criar");
        System.out.println("2. Editar");
        System.out.println("3. Listar um produto");
        System.out.println("4. Listar todos produtos disponíveis");
        System.out.println("5. Voltar ao menu principal");
    }

    public void executeProductMenu() throws SQLException {
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

    private void executeProduct(int option) throws SQLException {
        int id;
        Scanner scan = new Scanner(System.in);
        Product product = new Product();
        switch (option){
            case 1:
                System.out.print("Digite o nome do produto: ");
                product.setName(scan.nextLine());
                System.out.print("Digite o preço do produto: ");
                product.setPrice(scan.nextDouble());
                System.out.print("Digite a quantidade disponível do produto: ");
                product.setQuantity(scan.nextInt());
                try {
                    productDao.insert(product);
                } catch (SQLException e){
                    throw new SQLException(e.getMessage());
                }
                System.out.println("Produto cadastrado com sucesso!");

                break;
            case 2:
                System.out.print("Digite o id do produto que você deseja editar: ");
                id = scan.nextInt();
                scan.nextLine();
                Product cover = (Product) productDao.select(id);
                if(cover != null){
                    System.out.print("Digite o novo nome do produto: ");
                    product.setName(scan.nextLine());
                    System.out.print("Digite o novo preço do produto: ");
                    product.setPrice(scan.nextDouble());
                    System.out.print("Digite a nova quantidade disponível do produto: ");
                    product.setQuantity(scan.nextInt());
                    try {
                        productDao.update(product, cover.getId());
                    } catch (SQLException e){
                        throw new SQLException(e.getMessage());
                    }
                    System.out.println("Produto editado com sucesso!");
                }
                break;
            case 3:
                System.out.print("Digite o id do produto que você deseja listar: ");
                id = scan.nextInt();
                Product cover1 = (Product) productDao.select(id);
                if(cover1 != null){
                    System.out.println("Id = " + cover1.getId());
                    System.out.println("Nome = " + cover1.getName());
                    System.out.println("Preço = " + cover1.getPrice());
                    System.out.println("Quantidade = " + cover1.getQuantity());
                }
                break;
            case 4:
                System.out.println("Lista de produtos disponíveis: ");
                for (Object obj : productDao.selectAvaible()) {
                    if (obj instanceof Product p) {
                        System.out.print("Id = " + p.getId() + " - ");
                        System.out.print("Nome = " + p.getName() + " - ");
                        System.out.print("Preço = R$" + p.getPrice() + " - ");
                        System.out.print("Quantidade = " + p.getQuantity());
                    }
                    System.out.println();
                }
                break;
            case 5:
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
                break;
        }
    }
}
