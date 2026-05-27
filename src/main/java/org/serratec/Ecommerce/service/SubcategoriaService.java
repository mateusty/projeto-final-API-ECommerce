package org.serratec.Ecommerce.service;


import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.Subcategoria;
import org.serratec.Ecommerce.exception.InvalidDataException;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.*;
import org.serratec.Ecommerce.repository.CategoriaRepository;
import org.serratec.Ecommerce.repository.SubcategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubcategoriaService {

    private final SubcategoriaRepository subcategoriaRepository;
    private final CategoriaRepository categoriaRepository;

    public SubcategoriaService (SubcategoriaRepository  subcategoriaRepository,CategoriaRepository categoriaRepository){
        this.subcategoriaRepository = subcategoriaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // referente ao POST
    public SubcategoriaResponse criarSubcategoria(SubcategoriaRequest request) {
        if (subcategoriaRepository.existsByNomeIgnoreCase(request.getNome())) {
            throw new InvalidDataException("Já existe uma subcategoria com esse nome");
        }
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada com esse ID."));
        Subcategoria subcategoria = new Subcategoria(request, categoria);
        subcategoriaRepository.save(subcategoria);
        return new SubcategoriaResponse(subcategoria);
    }

    //referente ao GET
    public List<SubcategoriaResponse> listarSubcategorias() {
        List<Subcategoria> subcategorias = subcategoriaRepository.findAllByOrderByNomeAsc();
        if (subcategorias.isEmpty()) {
            throw new NotFoundException(" Nenhuma subcategoria cadastrada");
        }
        return subcategorias.stream()
                .map(SubcategoriaResponse::new)
                .toList();
    }

    //buscar subcategoria pelo ID
    public SubcategoriaResponse buscarPorId (UUID id) {
        Subcategoria subcategoria = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma subcategoria encontrada com esse ID"));
        return new SubcategoriaResponse(subcategoria);
    }
    // buscar subcategoria pelo nome
    public SubcategoriaResponse buscarPorNome (String nome) {

        if (nome == null || nome.isBlank()) {
            throw new InvalidDataException(" Nome da subcategoria não foi informado");
        }
        Subcategoria subcategoria = subcategoriaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new NotFoundException("Subcategoria não foi encontrada com esse nome : " + nome));
        return new SubcategoriaResponse(subcategoria);
    }
    // listar subcategorias por categoria
    public List<SubcategoriaResponse> listarPorCategoria(UUID categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new NotFoundException("Categoria não encontrada com esse ID.");
        }
        List<Subcategoria> subcategorias = subcategoriaRepository.findByCategoriaIdOrderByNomeAsc(categoriaId);
        if (subcategorias.isEmpty()) {
            throw new NotFoundException("Nenhuma subcategoria encontrada para essa categoria.");
        }
        return subcategorias.stream()
                .map(SubcategoriaResponse::new)
                .toList();
    }

    //referente ao PUT
    public SubcategoriaResponse atualizarSubcategoria (UUID id , SubcategoriaUpdateRequest request) {
        Subcategoria subcategoria = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma subcategoria encontrada com esse ID"));

        boolean nomeExiste = request.getNome() != null && !request.getNome().isBlank();

        if (nomeExiste && subcategoriaRepository.existsByNomeIgnoreCase(request.getNome())
                && !subcategoria.getNome().equalsIgnoreCase(request.getNome())) { // valida se ja tem categoria com esse nome
            throw new InvalidDataException("Já existe uma subcategoria com esse nome.");
        }
        // permite alterar categoria desejada apenas
        if (nomeExiste) {
            subcategoria.setNome(request.getNome());
        }
        if (request.getDescricao() != null && !request.getDescricao().isBlank()) {
            subcategoria.setDescricao(request.getDescricao());
        }
        subcategoriaRepository.save(subcategoria);
        return new SubcategoriaResponse(subcategoria);
    }
    // referente ao DELETE
    public void deletarSubcategoria(UUID id) {
        Subcategoria subcategoria = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma subcategoria encontrada com esse ID"));

       /* if (produtoRepository.existsBySubcategoriaId(id)) {
            throw new InvalidDataException("Não é possível deletar uma subcategoria com produtos vinculados.");
        }*/
        subcategoriaRepository.delete(subcategoria);
    }
}
