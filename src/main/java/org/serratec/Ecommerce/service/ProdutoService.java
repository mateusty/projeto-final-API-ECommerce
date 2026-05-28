package org.serratec.Ecommerce.service;

import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.Produto;
import org.serratec.Ecommerce.entity.Subcategoria;
import org.serratec.Ecommerce.exception.InvalidDataException;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.ProdutoRequest;
import org.serratec.Ecommerce.model.ProdutoResponse;
import org.serratec.Ecommerce.model.ProdutoUpdateRequest;
import org.serratec.Ecommerce.repository.CategoriaRepository;
import org.serratec.Ecommerce.repository.ProdutoRepository;
import org.serratec.Ecommerce.repository.SubcategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository,
                          SubcategoriaRepository subcategoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.subcategoriaRepository = subcategoriaRepository;
    }

    public void cadastrarProduto(ProdutoRequest dto) {
        if (this.produtoRepository.existsByNomeProduto(dto.getNomeProduto())) {
            throw new InvalidDataException("Falha ao cadastrar: Produto já cadastrado.");
        }
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada."));
        Subcategoria subcategoria = subcategoriaRepository.findById(dto.getSubcategoriaId())
                .orElseThrow(() -> new NotFoundException("Subcategoria não encontrada."));

        Produto produto = new Produto(dto, categoria, subcategoria);
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
        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoria não encontrada."));
            produto.setCategoria(categoria);
        }
        if (dto.getSubcategoriaId() != null) {
            Subcategoria subcategoria = subcategoriaRepository.findById(dto.getSubcategoriaId())
                    .orElseThrow(() -> new NotFoundException("Subcategoria não encontrada"));
            produto.setSubcategoria(subcategoria);
        }

        this.produtoRepository.save(produto);
    }

    public List<ProdutoResponse> buscarProduto(String nomeProduto, UUID categoriaId, UUID subcategoriaId) {
        List<Produto> produtos;

        if (nomeProduto != null) {
            produtos = this.produtoRepository.findByNomeProdutoContainingIgnoreCase(nomeProduto);
        } else if (categoriaId != null) {
            produtos = this.produtoRepository.findByCategoriaId(categoriaId);
        } else if (subcategoriaId != null) {
            produtos = this.produtoRepository.findBySubcategoriaId(subcategoriaId);
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
