package com.gerenciapedidos.herbert.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gerenciapedidos.herbert.dto.AtualizaPedidoDTO;
import com.gerenciapedidos.herbert.dto.ClienteDTO;
import com.gerenciapedidos.herbert.dto.EnderecoDTO;
import com.gerenciapedidos.herbert.dto.InserePedidoDTO;
import com.gerenciapedidos.herbert.dto.ListaPedidoDTO;
import com.gerenciapedidos.herbert.entity.Cliente;
import com.gerenciapedidos.herbert.entity.ClienteRepository;
import com.gerenciapedidos.herbert.entity.Endereco;
import com.gerenciapedidos.herbert.entity.Pedido;
import com.gerenciapedidos.herbert.entity.PedidoRepository;
import com.gerenciapedidos.herbert.entity.Produto;
import com.gerenciapedidos.herbert.entity.ProdutoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/order")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repositoryPedido;
	
	@Autowired
	private ClienteRepository repositoryCliente;
	
//	@Autowired
//	private EnderecoRepository repositoryEndereco;
	
	@Autowired
	private ProdutoRepository repositoryProduto;
		
	@PostMapping
	@Transactional
	public ResponseEntity<ListaPedidoDTO> inserir(@RequestBody @Valid InserePedidoDTO pedidoDTO, UriComponentsBuilder uriComponentsBuilder) {
		var pedido = new Pedido(pedidoDTO);
		var endereco = new Endereco(new EnderecoDTO(
				pedidoDTO.cliente().getEndereco().getCep(),
				pedidoDTO.cliente().getEndereco().getRua(),
				pedidoDTO.cliente().getEndereco().getBairro(),
				pedidoDTO.cliente().getEndereco().getCidade(),
				pedidoDTO.cliente().getEndereco().getUf()));
//		repositoryEndereco.save(endereco);
		
		var cliente = new Cliente(new ClienteDTO(pedidoDTO.cliente().getNome(), pedidoDTO.cliente().getEmail(), pedidoDTO.cliente().getEndereco()));
		
		List<Cliente> listClientes = repositoryCliente.findByEmail(pedido.getCliente().getEmail());
		
        if (listClientes == null || listClientes.size() == 0) {
    		cliente.setEndereco(endereco);
        } else {
        	cliente = listClientes.get(0);
        }
		
		int sequencia = 1;
		double valorTotal = 0;
		List<Produto> listProdutos;
		for(Produto produto : pedidoDTO.produtos()) {
			listProdutos = repositoryProduto.findByCodigo(produto.getCodigo());
			
            if (listProdutos == null || listProdutos.size() == 0) {
            	produto = repositoryProduto.save(produto);
            } else {
            	produto = listProdutos.get(0);
            }
			
			pedido.adicionarItem(produto, sequencia, produto.getValor());
			valorTotal += produto.getValor();
			sequencia++;
		}
		
		pedido.setCliente(cliente);
		pedido.setValor_produtos(valorTotal);
		pedido.setValor_total(valorTotal);
		repositoryPedido.save(pedido);
		
		var uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ListaPedidoDTO(pedido));
	}
	
	@GetMapping
	public ResponseEntity<List<ListaPedidoDTO>> listar() {
		var lista = repositoryPedido.findAllByAtivoTrue().stream().map(ListaPedidoDTO::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	
	@PutMapping
	@Transactional
	public ResponseEntity<ListaPedidoDTO> alterar(@RequestBody @Valid AtualizaPedidoDTO atualizaPedidoDTO) {
		//TODO: process PUT request
		var pedido = repositoryPedido.getReferenceById(atualizaPedidoDTO.id());
		pedido.alterar(atualizaPedidoDTO);
		
		return ResponseEntity.ok(new ListaPedidoDTO(pedido));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repositoryPedido.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var pedido = repositoryPedido.getReferenceById(id);
		pedido.inativar();
		
		return ResponseEntity.noContent().build();
	}	
	
	@PutMapping("ativar/{id}")
	@Transactional
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		var pedido = repositoryPedido.getReferenceById(id);
		pedido.ativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ListaPedidoDTO> buscar(@PathVariable Long id) {
		var pedido = repositoryPedido.getReferenceById(id);

		return ResponseEntity.ok(new ListaPedidoDTO(pedido));
	}
	
	@GetMapping("emissao/{dtInicio}/{dtFim}")
	public ResponseEntity<List<ListaPedidoDTO>> buscarPorEmissao(@PathVariable LocalDate dtInicio, @PathVariable LocalDate dtFim) {
		Pageable pageable = PageRequest.of(0, 200, Sort.by("id"));
		var lista = repositoryPedido.findByEmissaoBetween(dtInicio, dtFim, pageable).stream().map(ListaPedidoDTO::new).toList();
		
		return ResponseEntity.ok(lista);
	}

}
