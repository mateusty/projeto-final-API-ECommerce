package org.serratec.Ecommerce.service;

import org.serratec.Ecommerce.entity.Produto;
import org.serratec.Ecommerce.exception.InvalidDataException;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.ProdutoRequest;
import org.serratec.Ecommerce.model.ProdutoResponse;
import org.serratec.Ecommerce.model.ProdutoUpdateRequest;
import org.serratec.Ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarProduto(ProdutoRequest dto) {
        if (this.produtoRepository.existsByNomeProduto(dto.getNomeProduto())) {
            throw new InvalidDataException("Falha ao cadastrar: Produto já cadastrado.");
        }
        Produto produto = new Produto(dto);
        this.produtoRepository.save(produto);
    }

    public void atualizarProduto(UUID id, ProdutoUpdateRequest dto) {
        Produto produto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Falha ao atualizar: Produto não encontrado."));

        if (dto.getNomeProduto() != null) {
            produto.setNomeProduto(dto.getNomeProduto());
        }
        if (dto.getPreco() != null) {
            produto.setPreco(dto.getPreco());
        }
//        if (dto.getCategoria() != null) {
//            produto.setCategoria(dto.getCategoria());
//        }
//        if (dto.getIdSubcategoria() != null) {
//            produto.setSubcategoria(dto.getSubcategoria());
//        }

        this.produtoRepository.save(produto);
    }

    public List<ProdutoResponse> buscarProduto(String nomeProduto, Categoria categoria, Subcategoria subcategoria) {
        List<Produto> produtos;

        if (nomeProduto != null) {
            produtos = this.produtoRepository.findByNomeProdutoContainingIgnoreCase(nomeProduto);
        } else if (categoria != null) {
            produtos = this.produtoRepository.findByCategoria(categoria);
        } else if (subcategoria != null) {
            produtos = this.produtoRepository.findBySubcategoria(subcategoria);
        } else {
            produtos = this.produtoRepository.findAll();
        }

        List<ProdutoResponse> resultado = new ArrayList<>();
        for (Produto produto : produtos) {
            resultado.add(new ProdutoResponse(produto));
        }
        return resultado;
    }

        public void removerProduto(UUID id) {
            if (!this.produtoRepository.existsById(id)) {
                throw new NotFoundException("Falha ao deletar: Produto não encontrado");
            }
            this.produtoRepository.deleteById(id);
    }
}
