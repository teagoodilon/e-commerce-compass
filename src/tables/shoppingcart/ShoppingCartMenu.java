package tables.shoppingcart;

import tables.cart_product.CartProduct;
import tables.cart_product.CartProductDao;
import tables.costumer.Costumer;
import tables.costumer.CostumerDao;
import tables.product.Product;
import tables.product.ProductDao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartMenu {

    private Scanner scanner;
    private CostumerDao costumerDao;
    private CartProductDao cartProductDao;
    private ProductDao productDao;
    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartMenu() {
        scanner = new Scanner(System.in);
        costumerDao = new CostumerDao();
        cartProductDao = new CartProductDao();
        productDao = new ProductDao();
        shoppingCartDao = new ShoppingCartDao();

    }

    public void showShoppingCartMenu() {
        System.out.println("\n\n\n===== Menu do Carrinho de compras =====");
        System.out.println("O que você deseja fazer com o Carrinho de compras:");
        System.out.println("1. Criar");
        System.out.println("2. Adicionar mais produtos");
        System.out.println("3. Editar");
        System.out.println("4. Listar um Carrinho de compras");
        System.out.println("5. Listar todos Carrinho de compras");
        System.out.println("6. Voltar ao menu principal");
    }

    public void executeShoppingCartMenu() throws SQLException {
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
    private void executeShoppingCart(int option) throws SQLException {
        int id;
        int idProduct;
        int qntProduct=0;
        int optionProduct = 0;
        Scanner scan = new Scanner(System.in);
        CartProduct cartProduct = new CartProduct();
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Product> list = new ArrayList<>();
        List<Integer> qntList = new ArrayList<>();
        switch (option){
            case 1:
                System.out.print("Digite o id do cliente que você deseja criar o carrinho de compras: ");
                id = scan.nextInt();
                Costumer cover = (Costumer) costumerDao.select(id);
                if(cover != null) {
                    shoppingCart.setCostumerId(cover);
                    if(Boolean.TRUE.equals(shoppingCartDao.insert(shoppingCart))) {
                        System.out.println("Cliente: " + cover.getName());
                        stockProducts();
                        do {
                            System.out.print("Digite o id do que produto você quer adicionar: ");
                            idProduct = scan.nextInt();
                            scan.nextLine();
                            Product coverProduct = (Product) productDao.select(idProduct);
                            if (coverProduct != null) {
                                System.out.print("Digite quantas unidades desse produto você deseja adicionar: ");
                                qntProduct = scan.nextInt();
                                while ((coverProduct.getQuantity() - qntProduct) < 0) {
                                    System.out.println("Não temos essa quantidade do produto em estoque, tente novamente.");
                                    System.out.print("Digite quantas unidades desse produto você deseja adicionar: ");
                                    qntProduct = scan.nextInt();
                                }
                                coverProduct.setQuantity(coverProduct.getQuantity() - qntProduct);
                                productDao.update(coverProduct, coverProduct.getId());
                                list.add(coverProduct);
                                qntList.add(qntProduct);
                                cartProduct.setShoppingCartId((ShoppingCart) shoppingCartDao.select(id));
                                cartProduct.setProductId(list);
                                cartProduct.setQntProduct(qntList);
                                cartProduct.setProductsValue((float) (qntProduct*coverProduct.getPrice()));
                                cartProductDao.insert(cartProduct);
                                if(productDao.selectAvaible().isEmpty()){
                                    System.out.println("Nosso estoque de produtos está vazio");
                                    optionProduct = 2;
                                } else {
                                    System.out.println("Você deseja adicionar mais produtos? ");
                                    System.out.println("1. Sim ");
                                    System.out.println("2. Não ");
                                    System.out.print("Escolha uma opção: ");
                                    optionProduct = scanner.nextInt();
                                    scanner.nextLine();
                                }
                            }
                        } while (optionProduct != 2);
                        list.clear();
                        qntList.clear();
                    }
                }
                break;
            case 2:
                break;
            case 3:
                System.out.print("Digite o id do cliente que você deseja editar o carrinho: ");
                id = scan.nextInt();
                int count = 0;
                int qntProductSc = 0;
                List<int[]> tuple = new ArrayList<>();
                if(costumerDao.select(id) != null) {
                    if(shoppingCartDao.select(id) != null) {
                        shoppingCart = (ShoppingCart) shoppingCartDao.select(id);
                        cartProduct = (CartProduct) cartProductDao.select(shoppingCart.getId());
                        System.out.println("Produtos que estão neste carrinho: ");
                        for (Product p : cartProduct.getProductId()) {
                            System.out.print("Id = " + p.getId() + " - ");
                            System.out.print("Nome = " + p.getName() + " - ");
                            System.out.println("Quantidade = " + cartProduct.getQntProduct().get(count));
                            int[] exTuple = {p.getId(), cartProduct.getQntProduct().get(count)};
                            tuple.add(exTuple);
                            count++;
                        }
                        if(count != 0) {
                            System.out.print("Digite o id do que produto você deseja modificar: ");
                            idProduct = scan.nextInt();
                            scan.nextLine();
                            Product haveProductQnt = (Product) productDao.select(idProduct);
                            if(haveProductQnt != null) {
                                if (haveProductQnt.getQuantity() > 0) {
                                    Product coverProduct = null;
                                    for (Product p : cartProduct.getProductId()) {
                                        if (p.getId() == idProduct) {
                                            coverProduct = new Product();
                                            coverProduct.setId(p.getId());
                                            coverProduct.setName(p.getName());
                                            coverProduct.setPrice(p.getPrice());
                                        }
                                    }
                                    for (int[] i : tuple) {
                                        assert coverProduct != null;
                                        if (coverProduct.getId() == i[0]) {
                                            qntProductSc = i[1];
                                        }
                                    }
                                    if (coverProduct != null) {
                                        System.out.print("Digite a nova quantidade desse produto: ");
                                        qntProduct = scan.nextInt();
                                        Product newProduct = (Product) productDao.select(coverProduct.getId());
                                        if (newProduct.getQuantity() != 0) {
                                            if (qntProduct < qntProductSc && qntProduct != 0) {
                                                coverProduct.setQuantity(newProduct.getQuantity() + (qntProductSc - qntProduct));
                                                productDao.update(coverProduct, coverProduct.getId());
                                                list.add(coverProduct);
                                                qntList.add(qntProduct);
                                                cartProduct.setShoppingCartId((ShoppingCart) shoppingCartDao.select(id));
                                                cartProduct.setProductId(list);
                                                cartProduct.setQntProduct(qntList);
                                                cartProduct.setProductsValue((float) (qntProduct * coverProduct.getPrice()));
                                                cartProductDao.update(cartProduct, coverProduct.getId());
                                                System.out.println("Produto editado com sucesso");
                                            } else {
                                                while (newProduct.getQuantity() - qntProduct < 0) {
                                                    System.out.println("Não temos essa quantidade do produto em estoque, tente novamente.");
                                                    System.out.println("Temos apenas: " + newProduct.getQuantity() + " unidades");
                                                    System.out.print("Digite quantas unidades desse produto você deseja adicionar: ");
                                                    qntProduct = scan.nextInt();
                                                }
                                                coverProduct.setQuantity((newProduct.getQuantity() + qntProductSc) - qntProduct);
                                                productDao.update(coverProduct, coverProduct.getId());
                                                if (qntProduct == 0) {
                                                    System.out.println("Produto removido do carrinho");
                                                    cartProductDao.delete(((ShoppingCart) shoppingCartDao.select(id)).getId());
                                                } else {
                                                    list.add(coverProduct);
                                                    qntList.add(qntProduct);
                                                    cartProduct.setShoppingCartId((ShoppingCart) shoppingCartDao.select(id));
                                                    cartProduct.setProductId(list);
                                                    cartProduct.setQntProduct(qntList);
                                                    cartProduct.setProductsValue((float) (qntProduct * coverProduct.getPrice()));
                                                    cartProductDao.update(cartProduct, coverProduct.getId());
                                                    System.out.println("Produto editado com sucesso");
                                                }
                                            }
                                        } else {
                                            System.out.println("Não temos mais esse produto em estoque");
                                        }
                                    } else {
                                        System.out.println("Não existe produto com esse id no carrinho");
                                        System.out.println("Voltando ao menu de Carrinhos..");
                                    }
                                } else {
                                    System.out.println("Não temos esse produto em estoque");
                                }
                            }
                        } else {
                            System.out.println("O carrinho está vazio");
                        }
                    } else {
                        System.out.println("O carrinho desse cliente ainda não foi criado ");
                    }
                    list.clear();
                    qntList.clear();
                }
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }
}
