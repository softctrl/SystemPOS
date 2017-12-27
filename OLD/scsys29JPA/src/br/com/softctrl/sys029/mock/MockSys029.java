/**
 * 
 */
package br.com.softctrl.sys029.mock;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.softctrl.sys029.model.AccountingPlan;
import br.com.softctrl.sys029.model.Composition;
import br.com.softctrl.sys029.model.CompositionProduct;
import br.com.softctrl.sys029.model.Product;
import br.com.softctrl.sys029.model.Restaurant;
import br.com.softctrl.sys029.model.RestaurantTable;
import br.com.softctrl.sys029.model.User;
import br.com.softctrl.sys029.model.base.connection.SCDBaseConnection;
import br.com.softctrl.sys029.model.enumeration.AccountType;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 15, 2012 1:32:05 PM
 * 
 */
public final class MockSys029 {

	/**
	 * 
	 */
	public MockSys029() {
		super();
	}

	/**
	 * Lista de usuarios
	 * 
	 * @param count
	 * @return
	 */
	// User====================================================================
	public static List<User> mockUsers(Restaurant restaurant, int count) {
		java.util.List<User> users = new java.util.ArrayList<User>();
		if (count > 999) {
			count = 10;
		}
		for (int i = 0; i < count; i++) {
			users.add(new User(restaurant, String.format("%03d", i),
					"Usuario numero " + i, "000"));
		}
		return users;

	}

	// Restaurant===============================================================
	public static Restaurant mockRestaurant() {

		return new Restaurant("100", "DEUS Quer Arrumadinho", 50, 4);
	}

	// RestaurantTable===============================================================
	public static List<RestaurantTable> mockRestaurantTable(
			Restaurant restaurant, int count) {

		java.util.List<RestaurantTable> tables = new java.util.ArrayList<RestaurantTable>();
		if (count > 999) {
			count = 10;
		}
		for (int i = 0; i < count; i++) {
			tables.add(new RestaurantTable(restaurant, (i + 1), "Mesa numero "
					+ i));
		}
		return tables;

	}

	// PaymentType==============================================================
	// public static List<PaymentType> mockPaymentType(AccountingPlan ap, int
	// count) {
	//
	// java.util.List<PaymentType> payments = new
	// java.util.ArrayList<PaymentType>();
	// if (count > 100) {
	// count = 10;
	// }
	// for (int i = 0; i < count; i++) {
	// payments.add(new PaymentType(ap, String.format("%03d", i),
	// "Forma pagamento-" + i));
	// }
	// return payments;
	//
	// }

	// Product==================================================================
	public static List<Product> mockProducts(int count) {

		java.util.List<Product> products = new java.util.ArrayList<Product>();
		if (count > 1000) {
			count = 100;
		}
		for (int i = 0; i < count; i++) {
			products.add(new Product(String.format("%010d", i),
					"Descricao produto" + i, (i % 2 != 0), BigDecimal
							.valueOf(2.5)));
		}
		return products;

	}

	// Composition==============================================================
	public static List<Composition> mockCompositions(int count) {

		java.util.List<Composition> compositions = new java.util.ArrayList<Composition>();
		if (count > 1000) {
			count = 100;
		}
		for (int i = 0; i < count; i++) {
			compositions.add(new Composition(String.format("%010d", i)));
		}
		return compositions;

	}

	// CompositionProduct=======================================================
	public static List<CompositionProduct> mockCompositionProducts(
			Composition composition, java.util.List<Product> products) {

		for (Product p : products) {
			composition.addProduct((p.getId() % 2 == 0),
					(p.getId() % 2 == 0 ? p.getPriceTag() : p.getPriceTag()
							.add(new BigDecimal(0.7))), p);
		}
		return composition.getCompositionProducts();

	}

	public static void main(String[] args) {

		// fakeDataBase();
		realisticDataBase();

	}

	private static void realisticDataBase() {

		SCDBaseConnection conn = SCDBaseConnection.getInstance();
		EntityManager em = conn.getEntityManager();

		em.getTransaction().begin();

		AccountingPlan accountLevel1 = null;
		AccountingPlan accountLevel2 = null;
		AccountingPlan accountLevel3 = null;

		accountLevel1 = new AccountingPlan("1", "Receita", AccountType.DEBIT,
				Boolean.TRUE, null);

		em.persist(accountLevel1);

		accountLevel2 = accountLevel1.addChildAccount("1",
				"Receitas Operacionais");

		em.persist(accountLevel2);

		accountLevel3 = accountLevel2.addChildAccount("1", "Venda à Vista");

		em.persist(accountLevel3);

		em.persist(accountLevel3.addChildAccount("1", "Dinheiro"));
		em.persist(accountLevel3.addChildAccount("2", "Cheque"));

		accountLevel3 = accountLevel2.addChildAccount("2", "Venda à Prazo");

		em.persist(accountLevel3);

		em.persist(accountLevel3.addChildAccount("1", "Cheque Pré"));
		em.persist(accountLevel3.addChildAccount("2", "Amex"));
		em.persist(accountLevel3.addChildAccount("3", "Mastercard"));
		em.persist(accountLevel3.addChildAccount("4", "Visa"));
		em.persist(accountLevel3.addChildAccount("5", "Redeshop"));
		em.persist(accountLevel3.addChildAccount("6", "Visa Net"));

		accountLevel3 = accountLevel2.addChildAccount("3", "Ticket");

		em.persist(accountLevel3);

		em.persist(accountLevel3.addChildAccount("1", "Cheque Cardápio"));
		em.persist(accountLevel3.addChildAccount("2", "TR"));
		em.persist(accountLevel3.addChildAccount("3", "Vale Refeição"));
		em.persist(accountLevel3.addChildAccount("4", "Vale Ticket"));
		em.persist(accountLevel3.addChildAccount("5", "Eat cheque"));

		accountLevel2 = accountLevel1.addChildAccount("2",
				"Receitas Não Operacionais");

		em.persist(accountLevel2);

		em.persist(accountLevel2.addChildAccount("1", "Rendimentos Bancários"));
		em.persist(accountLevel2.addChildAccount("2", "Empréstimos"));
		em.persist(accountLevel2.addChildAccount("3", "Transferências"));
		em.persist(accountLevel2.addChildAccount("4", "Reembolsos Diversos"));

		accountLevel1 = new AccountingPlan("2", "Despesas", AccountType.CREDIT,
				Boolean.TRUE, null);

		em.persist(accountLevel1);

		accountLevel2 = accountLevel1.addChildAccount("1", "Despesas Diretas");

		em.persist(accountLevel2);

		accountLevel3 = accountLevel2.addChildAccount("1", "Insumos Diversos");

		em.persist(accountLevel3);

		em.persist(accountLevel3.addChildAccount("1", "Hortifruti"));
		em.persist(accountLevel3.addChildAccount("2", "Laticínios"));
		em.persist(accountLevel3.addChildAccount("3", "Massas"));
		em.persist(accountLevel3.addChildAccount("4", "Pães"));
		em.persist(accountLevel3.addChildAccount("5", "Sobremesas Próprias"));
		em.persist(accountLevel3.addChildAccount("6", "Sobremesas Terceiros"));
		em.persist(accountLevel3.addChildAccount("7", "Cigarros"));
		em.persist(accountLevel3.addChildAccount("8", "Embalagens"));
		em.persist(accountLevel3.addChildAccount("9", "Descartáveis"));
		em.persist(accountLevel3.addChildAccount("10", "Congelados"));
		em.persist(accountLevel3.addChildAccount("11", "Orgânicos"));

		accountLevel3 = accountLevel2.addChildAccount("2", "Insumos/ Carnes");

		em.persist(accountLevel3);

		em.getTransaction().commit();
		em.getTransaction().begin();

		em.persist(accountLevel3.addChildAccount("1", "Carnes Brancas"));
		em.persist(accountLevel3.addChildAccount("2", "Carnes Vermelhas"));
		em.persist(accountLevel3.addChildAccount("3", "Frutos do Mar"));
		em.persist(accountLevel3.addChildAccount("4", "Frios / Embutidos"));

		accountLevel3 = accountLevel2.addChildAccount("3", "Insumos/ Bebidas");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Alcoólicas"));
		em.persist(accountLevel3.addChildAccount("2", "Não Alcoólicas"));

		accountLevel3 = accountLevel2.addChildAccount("4",
				"Utensílios de Reposição");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Utensílios de Cozinha"));
		em.persist(accountLevel3.addChildAccount("2", "Utensílios de Salão"));

		accountLevel3 = accountLevel2.addChildAccount("5",
				"Impostos sobre o faturamento");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "ICMS"));
		em.persist(accountLevel3.addChildAccount("2", "PIS"));
		em.persist(accountLevel3.addChildAccount("3", "COFINS"));
		em.persist(accountLevel3.addChildAccount("4", "IRPJ"));
		em.persist(accountLevel3.addChildAccount("5", "Contribuição Social"));
		em.persist(accountLevel3.addChildAccount("6", "IRRF"));
		em.persist(accountLevel3.addChildAccount("7", "SIMPLES"));

		accountLevel2 = accountLevel1.addChildAccount("2",
				"Despesas Operacionais");
		em.persist(accountLevel2);

		em.getTransaction().commit();
		em.getTransaction().begin();

		accountLevel3 = accountLevel2.addChildAccount("1", "Investimentos");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Móveis e Utensílios"));
		em.persist(accountLevel3.addChildAccount("2", "Equipamentos"));
		em.persist(accountLevel3.addChildAccount("3", "Benfeitorias"));
		em.persist(accountLevel3.addChildAccount("4", "Aplicações"));

		em.getTransaction().commit();
		em.getTransaction().begin();

		accountLevel3 = accountLevel2.addChildAccount("2", "Administrativo");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Correio"));
		em.persist(accountLevel3.addChildAccount("2", "Xerox"));
		em.persist(accountLevel3.addChildAccount("3", "Cartório"));
		em.persist(accountLevel3.addChildAccount("4", "Farmácia"));
		em.persist(accountLevel3.addChildAccount("5", "Locomoção"));
		em.persist(accountLevel3.addChildAccount("6", "Assinaturas / Anuidade"));
		em.persist(accountLevel3.addChildAccount("7", "Viagens"));
		em.persist(accountLevel3.addChildAccount("8", "Estadia"));
		em.persist(accountLevel3.addChildAccount("9", "Estacionamento"));
		em.persist(accountLevel3.addChildAccount("10",
				"Locação de Máquinas e Equipamentos"));
		em.persist(accountLevel3.addChildAccount("11", "Marcas e Patentes"));
		em.persist(accountLevel3.addChildAccount("12", "Recursos / Processos"));
		em.persist(accountLevel3.addChildAccount("13", "Fretes"));
		em.persist(accountLevel3.addChildAccount("14", "Seleção e Treinamento"));
		em.persist(accountLevel3.addChildAccount("15", "Doações"));
		em.persist(accountLevel3.addChildAccount("16", "Pagamento de Royaltes"));
		em.persist(accountLevel3
				.addChildAccount("17", "Aluguéis e Condomínios"));

		em.getTransaction().commit();
		em.getTransaction().begin();

		accountLevel3 = accountLevel2.addChildAccount("3", "Manutenção");
		em.persist(accountLevel3);
		em.persist(accountLevel3
				.addChildAccount("1", "Máquinas e Equipamentos"));
		em.persist(accountLevel3.addChildAccount("2", "Manutenção Predial"));
		em.persist(accountLevel3.addChildAccount("3",
				"Mão-de-obra p/ Manutenções"));

		accountLevel3 = accountLevel2.addChildAccount("4",
				"Serviços de Terceiros");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Consultoria"));
		em.persist(accountLevel3.addChildAccount("2", "Contador"));
		em.persist(accountLevel3.addChildAccount("3", "Advogado"));
		em.persist(accountLevel3.addChildAccount("4", "Informática"));
		em.persist(accountLevel3.addChildAccount("5", "Marketing"));
		em.persist(accountLevel3.addChildAccount("6", "Coleta de Lixo"));
		em.persist(accountLevel3.addChildAccount("7", "Segurança"));
		em.persist(accountLevel3.addChildAccount("8", "Dedetização"));
		em.persist(accountLevel3.addChildAccount("9", "Delivery"));
		em.persist(accountLevel3.addChildAccount("10", "Lavanderia"));

		accountLevel3 = accountLevel2.addChildAccount("5", "Pessoal");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Salários"));
		em.persist(accountLevel3.addChildAccount("2", "Adiantamentos"));
		em.persist(accountLevel3.addChildAccount("3", "Pró-Labore"));
		em.persist(accountLevel3.addChildAccount("4", "Extras (dobras)"));
		em.persist(accountLevel3.addChildAccount("5", "Gratificações"));
		em.persist(accountLevel3.addChildAccount("6", "Férias"));
		em.persist(accountLevel3.addChildAccount("7", "13o Salário"));
		em.persist(accountLevel3.addChildAccount("8", "Prov. 13o Salário"));
		em.persist(accountLevel3.addChildAccount("9", "Prov. Férias"));
		em.persist(accountLevel3.addChildAccount("10", "Rescisões"));
		em.persist(accountLevel3.addChildAccount("11",
				"Exame Admissional e Demissional"));
		em.persist(accountLevel3
				.addChildAccount("12", "Complemento de salário"));

		em.getTransaction().commit();
		em.getTransaction().begin();

		accountLevel3 = accountLevel2.addChildAccount("6", "Encargos");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "FGTS"));
		em.persist(accountLevel3
				.addChildAccount("2", "GPS Empresa + Terceiros"));
		em.persist(accountLevel3.addChildAccount("3", "GPS Empregados"));
		em.persist(accountLevel3.addChildAccount("4", "GPS Sobre Férias"));
		em.persist(accountLevel3.addChildAccount("5", "IR Funcionários"));
		em.persist(accountLevel3.addChildAccount("6", "Provisões"));
		em.persist(accountLevel3.addChildAccount("7", "Vale Funcionário"));

		accountLevel3 = accountLevel2.addChildAccount("7", "Impostos e Taxas");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "IPTU"));
		em.persist(accountLevel3.addChildAccount("2", "ECAD"));
		em.persist(accountLevel3.addChildAccount("3", "Taxa de Incêndio"));
		em.persist(accountLevel3.addChildAccount("4", "Multas"));
		em.persist(accountLevel3.addChildAccount("5", "Contribuições"));
		em.persist(accountLevel3.addChildAccount("6", "Sindicatos"));
		em.persist(accountLevel3.addChildAccount("7", "Taxas Diversas"));

		accountLevel3 = accountLevel2.addChildAccount("8",
				"Despesas Bancárias (financeiras)");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Tarifas Bancárias"));
		em.persist(accountLevel3.addChildAccount("2", "IOF"));
		em.persist(accountLevel3.addChildAccount("3", "CPMF"));
		em.persist(accountLevel3.addChildAccount("4", "Juros"));

		em.getTransaction().commit();
		em.getTransaction().begin();

		accountLevel3 = accountLevel2.addChildAccount("9", "Contas de Consumo");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Luz"));
		em.persist(accountLevel3.addChildAccount("2", "Água"));
		em.persist(accountLevel3.addChildAccount("3", "Telefone Fixo"));
		em.persist(accountLevel3.addChildAccount("4", "Telefone Móvel"));
		em.persist(accountLevel3.addChildAccount("5", "Internet"));
		em.persist(accountLevel3.addChildAccount("6", "TV por Assinatura"));

		accountLevel3 = accountLevel2.addChildAccount("10", "Eventos");
		em.persist(accountLevel3);
		em.persist(accountLevel3.addChildAccount("1", "Insumos/ Eventos"));
		em.persist(accountLevel3.addChildAccount("2", "Despesas Diversas"));

		accountLevel2 = accountLevel1.addChildAccount("3",
				"Despesas Não Operacionais");
		em.persist(accountLevel2);
		em.persist(accountLevel2.addChildAccount("1", "Empréstimos"));
		em.persist(accountLevel2.addChildAccount("2", "Transferência"));
		em.persist(accountLevel2.addChildAccount("3", "Reembolsos Diversos"));

		// Restaurant
		em.persist(mockRestaurant());

		em.getTransaction().commit();
		em.getTransaction().begin();

		// User
		Restaurant r = em.find(Restaurant.class, 1);

		User u = new User(r, "290", "TIMOSHENKO", "503");
		em.persist(u);
		u = new User(r, "503", "JULIANA", "290");
		em.persist(u);
		u = new User(r, "111", "Garcom", "111");
		em.persist(u);

		// RestaurantTable
		RestaurantTable rt = null;
		rt = new RestaurantTable(r, 1, "Mesa 01");
		em.persist(rt);
		rt = new RestaurantTable(r, 2, "Mesa 02");
		em.persist(rt);
		rt = new RestaurantTable(r, 3, "Mesa 03");
		em.persist(rt);
		rt = new RestaurantTable(r, 4, "Mesa 04");
		em.persist(rt);
		// rt = new RestaurantTable(r, 5, "Mesa 05");
		// em.persist(rt);
		// rt = new RestaurantTable(r, 6, "Mesa 06");
		// em.persist(rt);
		// rt = new RestaurantTable(r, 7, "Mesa 07");
		// em.persist(rt);
		// rt = new RestaurantTable(r, 8, "Mesa 08");
		// em.persist(rt);
		// rt = new RestaurantTable(r, 9, "Mesa 09");
		// em.persist(rt);
		// rt = new RestaurantTable(r, 10, "Mesa 10");
		// em.persist(rt);

		// Product
		Product p = null;
		int idFarofa, idArrozBranco, idBaiao, idMariaIzabel, idEspCarne, idEspFrango, idEspMixto, idA1, idA2, idA3 = -1;
		p = new Product("000", "Farofa", Boolean.FALSE, BigDecimal.valueOf(0.5));
		em.persist(p);
		idFarofa = p.getId();

		p = new Product("001", "Arroz Branco", Boolean.FALSE,
				BigDecimal.valueOf(2.5));
		em.persist(p);
		idArrozBranco = p.getId();

		p = new Product("002", "Baião de Dois", Boolean.FALSE,
				BigDecimal.valueOf(2.5));
		em.persist(p);
		idBaiao = p.getId();

		p = new Product("003", "Maria Izabel", Boolean.FALSE,
				BigDecimal.valueOf(2.5));
		em.persist(p);
		idMariaIzabel = p.getId();

		p = new Product("004", "Espeto de Carne", Boolean.FALSE,
				BigDecimal.valueOf(2.5));
		em.persist(p);
		idEspCarne = p.getId();

		p = new Product("005", "Espeto de Frango", Boolean.FALSE,
				BigDecimal.valueOf(2.5));
		em.persist(p);
		idEspFrango = p.getId();

		p = new Product("006", "Espeto de Toscana", Boolean.FALSE,
				BigDecimal.valueOf(2.5));
		em.persist(p);
		idEspMixto = p.getId();

		p = new Product("007", "Coca-cola(300)", Boolean.FALSE,
				BigDecimal.valueOf(1.5));
		em.persist(p);
		p = new Product("008", "Coca-cola(1L)", Boolean.FALSE,
				BigDecimal.valueOf(3.0));
		em.persist(p);

		p = new Product("010", "Arrumadinho de Carne", Boolean.FALSE,
				BigDecimal.valueOf(5.0));
		em.persist(p);
		idA1 = p.getId();
		p = new Product("011", "Arrumadinho de Frango", Boolean.FALSE,
				BigDecimal.valueOf(5.0));
		em.persist(p);
		idA2 = p.getId();
		p = new Product("012", "Arrumadinho de Toscana", Boolean.FALSE,
				BigDecimal.valueOf(5.0));
		em.persist(p);
		idA3 = p.getId();

		em.getTransaction().commit();
		em.getTransaction().begin();

		// Composition
		Composition comp = null;
		comp = new Composition("001");
		conn.getEntityManager().persist(comp);

		p = conn.getEntityManager().find(Product.class, idA1);
		p.setComposition(comp);
		conn.getEntityManager().persist(p);

		conn.getEntityManager().persist(
				comp.addProduct(Boolean.TRUE, BigDecimal.ZERO, conn
						.getEntityManager().find(Product.class, idFarofa)));

		em.persist(comp.addProduct(Boolean.FALSE, BigDecimal.valueOf(2.5),
				em.find(Product.class, idArrozBranco)));

		em.persist(comp.addProduct(Boolean.FALSE, BigDecimal.ZERO, conn
				.getEntityManager().find(Product.class, idEspCarne)));

		comp = new Composition("002");
		em.persist(comp);

		p = em.find(Product.class, idA2);
		p.setComposition(comp);
		em.persist(p);

		em.persist(comp.addProduct(Boolean.TRUE, BigDecimal.ZERO, conn
				.getEntityManager().find(Product.class, idFarofa)));

		em.persist(comp.addProduct(Boolean.FALSE, BigDecimal.valueOf(2.5), conn
				.getEntityManager().find(Product.class, idBaiao)));

		em.persist(comp.addProduct(Boolean.FALSE, BigDecimal.ZERO, conn
				.getEntityManager().find(Product.class, idEspFrango)));

		em.getTransaction().commit();

		// contabil

		conn.close();

	}
	// private static void fakeDataBase() {
	// SCDBaseConnection conn = SCDBaseConnection.getInstance();
	//
	// conn.getEntityManager().getTransaction().begin();
	//
	// // Restaurant
	// conn.getEntityManager().persist(mockRestaurant());
	//
	// conn.getEntityManager().getTransaction().commit();
	// conn.getEntityManager().getTransaction().begin();
	//
	// // User
	// Restaurant r = conn.getEntityManager().find(Restaurant.class, 1);
	// for (User u : mockUsers(r, 20)) {
	// conn.getEntityManager().persist(u);
	// }
	//
	// for (RestaurantTable rt : mockRestaurantTable(r, 10)) {
	// conn.getEntityManager().persist(rt);
	// }
	//
	// // PaymentType
	// for (PaymentType pt : mockPaymentType(10)) {
	// conn.getEntityManager().persist(pt);
	// }
	//
	// // Product
	// for (Product p : mockProducts(50)) {
	// conn.getEntityManager().persist(p);
	// }
	//
	// conn.getEntityManager().getTransaction().commit();
	// conn.getEntityManager().getTransaction().begin();
	//
	// // Composition
	// int count = 10;
	// List<Composition> compositions = mockCompositions(count);
	// for (int i = 0; i < count; i++) {
	// Product p = conn.getEntityManager()
	// .find(Product.class, (i + 1) * 2);
	// compositions.get(i).addProduct(
	// (i % 2 == 0),
	// (i % 2 == 0 ? p.getPriceTag() : p.getPriceTag().add(
	// new BigDecimal(2.6))), p);
	// }
	// for (Composition c : compositions) {
	// conn.getEntityManager().persist(c);
	// }
	// conn.getEntityManager().getTransaction().commit();
	// conn.getEntityManager().getTransaction().begin();
	//
	// compositions = conn.getEntityManager()
	// .createQuery("from Composition c").getResultList();
	// for (int i = 0; i < count; i++) {
	// Product p = conn.getEntityManager()
	// .find(Product.class, (i + 1) * 3);
	// compositions.get(i).addProduct(
	// (i % 2 == 0),
	// (i % 2 == 0 ? p.getPriceTag() : p.getPriceTag().add(
	// new BigDecimal(2.6))), p);
	// }
	// // for (int i = 0; i < count; i++) {
	// // Product p = conn.getEntityManager().find(Product.class,
	// // (i + 5) * 20);
	// // compositions.get(i).addProduct(
	// // (i % 2 == 0),
	// // (i % 2 == 0 ? p.getPriceTag() : p.getPriceTag().add(
	// // new BigDecimal(2.6))), p);
	// // }
	//
	// // for (int i = 0; i < count; i++) {
	// // Product p = conn.getEntityManager().find(Product.class,
	// // (i + 7) * 20);
	// // compositions.get(i).addProduct(
	// // (i % 2 == 0),
	// // (i % 2 == 0 ? p.getPriceTag() : p.getPriceTag().add(
	// // new BigDecimal(2.6))), p);
	// // }
	//
	// for (Composition c : compositions) {
	// conn.getEntityManager().persist(c);
	// }
	//
	// conn.getEntityManager().getTransaction().commit();
	// conn.close();
	//
	// }

}
