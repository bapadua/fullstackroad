package br.com.ionic.api;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ionic.api.domain.Categoria;
import br.com.ionic.api.domain.Cidade;
import br.com.ionic.api.domain.Cliente;
import br.com.ionic.api.domain.Endereco;
import br.com.ionic.api.domain.Estado;
import br.com.ionic.api.domain.ItemPedido;
import br.com.ionic.api.domain.Pagamento;
import br.com.ionic.api.domain.PagamentoComBoleto;
import br.com.ionic.api.domain.PagamentoComCartao;
import br.com.ionic.api.domain.Pedido;
import br.com.ionic.api.domain.Produto;
import br.com.ionic.api.domain.enums.EstadoPagamento;
import br.com.ionic.api.domain.enums.TipoCliente;
import br.com.ionic.api.repository.CategoriaRepository;
import br.com.ionic.api.repository.CidadeRepository;
import br.com.ionic.api.repository.ClienteRepository;
import br.com.ionic.api.repository.EnderecoRepository;
import br.com.ionic.api.repository.EstadoRepository;
import br.com.ionic.api.repository.ItemPedidoRepository;
import br.com.ionic.api.repository.PagamentoRepository;
import br.com.ionic.api.repository.PedidoRepository;
import br.com.ionic.api.repository.ProdutoRepository;

@SpringBootApplication
public class IonicBackendApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(IonicBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");
		Categoria cat3 = new Categoria("Acessorios");
		Categoria cat4 = new Categoria("Eletronicos");
		Categoria cat5 = new Categoria("Celulares");
		Categoria cat6 = new Categoria("SexHot");
		Categoria cat7 = new Categoria("Shot");

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");

		Cidade c1 = new Cidade("Uberlandia", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		this.estadoRepository.saveAll(Arrays.asList(est1, est2));
		this.cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(1 ,"Marina Silva", "marina@gmail.com", "06666666", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("06622640", "86659898"));

		Endereco e1 = new Endereco("Rua Osaka", "666", "Casa B", "Cincinaty", "0660055", cli1, c1);
		Endereco e2 = new Endereco("Av. Araguaia", "151", "10a Sala3", "Alphavela", "0110055", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		this.clienteRepository.save(cli1);
		this.enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:30"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:30"), cli1, e2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null,
				sdf.parse("29/09/2017 00:00"));
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		this.pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		this.pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		this.itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
