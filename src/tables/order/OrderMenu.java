package tables.order;

import tables.cartproduct.CartProduct;
import tables.cartproduct.CartProductDao;
import tables.costumer.Costumer;
import tables.costumer.CostumerDao;
import tables.product.Product;
import tables.shoppingcart.ShoppingCart;
import tables.shoppingcart.ShoppingCartDao;

import java.sql.SQLException;
import java.util.Scanner;

public class OrderMenu {
    private final Scanner scanner;
    private final CostumerDao costumerDao;
    private final ShoppingCartDao shoppingCartDao;
    private final OrderDao orderDao;
    private final CartProductDao cartProductDao;
    static final String NOTCART = "O carrinho desse cliente não foi criado ainda";
    public OrderMenu() {
        scanner = new Scanner(System.in);
        costumerDao = new CostumerDao();
        shoppingCartDao = new ShoppingCartDao();
        orderDao = new OrderDao();
        cartProductDao = new CartProductDao();
    }
    public void showOrderMenu() {
        System.out.println("\n\n\n===== Menu de Pedido =====");
        System.out.println("O que você deseja fazer com o Pedido:");
        System.out.println("1. Criar");
        System.out.println("2. Confirmar pedido");
        System.out.println("3. Listar um pedido");
        System.out.println("4. Voltar ao menu principal");
    }

    public void executeOrderMenu() throws SQLException {
        int option;
        do {
            showOrderMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            executeOrder(option);
            System.out.println();
        } while (option != 4);
    }

    private void executeOrder(int option) throws SQLException {
        int id;
        Scanner scan = new Scanner(System.in);
        ShoppingCart shoppingCart;
        Order order = new Order();
        CartProduct cartProduct;
        switch (option){
            case 1:
                System.out.print("Digite o id do cliente que você deseja criar o pedido: ");
                id = scan.nextInt();
                Costumer cover = (Costumer) costumerDao.select(id);
                if(cover != null) {
                    if(shoppingCartDao.select(id) != null){
                      shoppingCart = (ShoppingCart) shoppingCartDao.select(id);
                      if(orderDao.select(shoppingCart.getId()) == null){
                          order.setShoppingCartId(shoppingCart);
                          order.setConfirmed(false);
                          orderDao.insert(order);
                          System.out.print("Pedido criado com sucesso!");
                      } else {
                          System.out.print("O pedido desse cliente já foi criado");
                      }
                    } else {
                        System.out.print(NOTCART);
                    }
                }
                break;
            case 2:
                System.out.print("Digite o id do cliente que você deseja confirmar o pedido: ");
                id = scan.nextInt();
                Costumer cover1 = (Costumer) costumerDao.select(id);
                Order orderCheck;
                if(cover1 != null) {
                    if(shoppingCartDao.select(id) != null){
                        shoppingCart = (ShoppingCart) shoppingCartDao.select(id);
                        orderCheck = (Order) orderDao.select(shoppingCart.getId());
                        if(!orderCheck.isConfirmed()) {
                            order.setShoppingCartId(shoppingCart);
                            order.setConfirmed(true);
                            orderDao.update(order, shoppingCart.getId());
                            System.out.print("Pedido confirmado com sucesso!");
                        } else {
                            System.out.print("O pedido já foi confirmado!");
                        }
                    } else {
                        System.out.print(NOTCART);
                    }
                }
                break;
            case 3:
                System.out.print("Digite o id do cliente que você deseja listar o pedido: ");
                id = scan.nextInt();
                int count = 0;
                Costumer cover2 = (Costumer) costumerDao.select(id);
                Order orderCheck2;
                if(cover2 != null) {
                    if(shoppingCartDao.select(id) != null){
                        shoppingCart = (ShoppingCart) shoppingCartDao.select(id);
                        orderCheck2 = (Order) orderDao.select(shoppingCart.getId());
                        if(orderCheck2 != null){
                            System.out.print("Pedido do cliente: " + shoppingCart.getCostumerId().getName());
                            System.out.println(" - Id: " + shoppingCart.getCostumerId().getId());
                            System.out.println("Lista de produtos: ");
                            cartProduct = (CartProduct) cartProductDao.select(shoppingCart.getId());
                            if (cartProduct != null){
                                for (Product p : cartProduct.getProductId()) {
                                    System.out.print("Id = " + p.getId() + " - ");
                                    System.out.print("Nome = " + p.getName() + " - ");
                                    System.out.println("Quantidade = " + cartProduct.getQntProduct().get(count));
                                    count++;
                                }
                                System.out.print("Valor total do carrinho: R$");
                                System.out.println(shoppingCart.getTotalValue());
                                if(orderCheck2.isConfirmed()){
                                    System.out.print("Este pedido já foi confirmado");
                                } else {
                                    System.out.print("Este pedido não foi confirmado");
                                }

                            }
                        } else {
                            System.out.print("O pedido não foi criado ainda");
                        }
                    } else {
                        System.out.print(NOTCART);
                    }
                }
                break;
            case 4:
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
                break;
        }
    }
}
