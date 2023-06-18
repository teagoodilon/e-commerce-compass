package tables.product;

import tables.cartproduct.CartProduct;
import tables.cartproduct.CartProductDao;
import tables.order.Order;
import tables.order.OrderDao;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductMenu {
    private final Scanner scanner;
    private final ProductDao productDao;

    private final CartProductDao cartProductDao;
    private final OrderDao orderDao;
    static final String IDSTRING = "Id = ";
    static final String NAMESTRING = "Nome = ";
    static final String QUANTITY = "Nome = ";
    public ProductMenu() {
        scanner = new Scanner(System.in);
        productDao = new ProductDao();
        cartProductDao = new CartProductDao();
        orderDao = new OrderDao();
    }

    public void stockProducts() throws SQLException {
        System.out.println("Lista de produtos disponíveis: ");
        for (Object obj : productDao.selectAvaible()) {
            if (obj instanceof Product p) {
                System.out.print(IDSTRING + p.getId() + " - ");
                System.out.print(NAMESTRING + p.getName() + " - ");
                System.out.print("Preço = R$" + p.getPrice() + " - ");
                System.out.print(QUANTITY + p.getQuantity());
            }
            System.out.println();
        }
    }
    public void sellProducts() throws SQLException {
        CartProduct cartProduct;
        Order order;
        int count = 0;
        System.out.println("Lista de produtos vendidos: ");
        for (Object obj : orderDao.select()) {
            if (obj instanceof Order o) {
                cartProduct = (CartProduct) cartProductDao.select(o.getShoppingCartId().getId());
                order = (Order) orderDao.select(o.getShoppingCartId().getId());
                if(order.isConfirmed()) {
                    for (Product p : cartProduct.getProductId()) {
                        System.out.print(IDSTRING + p.getId() + " - ");
                        System.out.print(NAMESTRING + p.getName() + " - ");
                        System.out.print(QUANTITY + cartProduct.getQntProduct().get(count) + "\n");
                        count++;
                    }
                    count = 0;
                }
            }

        }
    }
    public void showProductMenu() {
        System.out.println("\n\n\n===== Menu de Produtos =====");
        System.out.println("O que você deseja fazer com o Produto:");
        System.out.println("1. Criar");
        System.out.println("2. Editar");
        System.out.println("3. Apagar produto");
        System.out.println("4. Listar um produto");
        System.out.println("5. Listar todos produtos disponíveis");
        System.out.println("6. Listar todos produtos vendidos");
        System.out.println("7. Voltar ao menu principal");
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
        } while (option != 7);
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
                System.out.print("Digite o id do produto que você deseja apagar: ");
                id = scan.nextInt();
                scan.nextLine();
                Product cover2 = (Product) productDao.select(id);
                if(cover2 != null){
                    if(Boolean.TRUE.equals(cartProductDao.selectProduct(cover2.getId()))){
                        System.out.println("Não é possível apagar o produto porque ele está em um carrinho de compras");
                    } else {
                        productDao.delete(cover2.getId());
                        System.out.println("Produto apagado com sucesso");
                    }
                }
                break;
            case 4:
                System.out.print("Digite o id do produto que você deseja listar: ");
                id = scan.nextInt();
                Product cover1 = (Product) productDao.select(id);
                if(cover1 != null){
                    System.out.println(IDSTRING + cover1.getId());
                    System.out.println(NAMESTRING + cover1.getName());
                    System.out.println("Preço = " + cover1.getPrice());
                    System.out.println(QUANTITY + cover1.getQuantity());
                }
                break;
            case 5:
                stockProducts();
                break;
            case 6:
                sellProducts();
                break;
            case 7:
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
                break;
        }
    }
}
