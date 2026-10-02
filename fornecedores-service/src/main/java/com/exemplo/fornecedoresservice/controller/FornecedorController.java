package com.exemplo.fornecedoresservice.controller;

import com.exemplo.fornecedoresservice.model.Fornecedor;
import com.exemplo.fornecedoresservice.service.FornecedorService;
import com.exemplo.fornecedoresservice.client.ProdutoClient;
import com.exemplo.fornecedoresservice.dto.ProdutoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;
    private final ProdutoClient produtoClient;

    public FornecedorController(FornecedorService service, ProdutoClient produtoClient) {
        this.service = service;
        this.produtoClient = produtoClient;
    }

    @GetMapping
    public List<Fornecedor> listarTodos() {
        return service.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = service.buscarPorId(id);
        return fornecedor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Fornecedor> criar(@RequestBody Fornecedor fornecedor) {
        Fornecedor salvo = service.salvar(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/produtos")
    public List<ProdutoDTO> listarProdutos() {
        return produtoClient.listarProdutos();
    }
}
