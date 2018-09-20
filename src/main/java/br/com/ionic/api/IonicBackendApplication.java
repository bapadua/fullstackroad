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
	public static void main(String[] args) {
		SpringApplication.run(IonicBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}
}
