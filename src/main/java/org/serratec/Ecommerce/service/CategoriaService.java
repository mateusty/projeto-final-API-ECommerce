package org.serratec.Ecommerce.service;

import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.StatusCategoria;
import org.serratec.Ecommerce.exception.InvalidDataException;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.CategoriaRequest;
import org.serratec.Ecommerce.model.CategoriaResponse;
import org.serratec.Ecommerce.model.CategoriaUpdateRequest;
import org.serratec.Ecommerce.repository.CategoriaRepository;
import org.serratec.Ecommerce.repository.SubcategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, SubcategoriaRepository subcategoriaRepository){
        this.categoriaRepository = categoriaRepository;
        this.subcategoriaRepository = subcategoriaRepository;
    }

    // referente ao POST
    public CategoriaResponse criarCategoria (CategoriaRequest request){

        String nomeTratado = request.getNome().trim();

        if(categoriaRepository.existsByNomeIgnoreCase(nomeTratado)){
            throw new InvalidDataException("Já existe uma categoria com esse nome");
        }
        Categoria categoria = new Categoria(request);
        categoriaRepository.save(categoria);
        return new CategoriaResponse(categoria);
    }

    //referente ao GET
    // Consulta  apenas as categorias ativas e devolve em ordem alfabética
    public List<CategoriaResponse>listarCategoriasAtivas(){
        List<Categoria> categorias = categoriaRepository.findByStatusCategoria(StatusCategoria.ATIVA);
            if (categorias.isEmpty()){
               throw new NotFoundException(" Nenhuma Categoria Cadastrada");
            }
            return categorias.stream()
                    .sorted((a, b) -> a.getNome().compareToIgnoreCase(b.getNome()))
                    .map(CategoriaResponse ::new)
                    .toList();
    }

    // Consulta  apenas as categorias inativas e devolve em ordem alfabética
    public List<CategoriaResponse>listarCategoriasInativas(){
    List<Categoria> categorias = categoriaRepository.findByStatusCategoria(StatusCategoria.INATIVA);
            if (categorias.isEmpty()){
        throw new NotFoundException(" Nenhuma Categoria Inativa Cadastrada");
    }
            return categorias.stream()
                    .sorted((a, b) -> a.getNome().compareToIgnoreCase(b.getNome()))
                    .map(CategoriaResponse ::new)
                    .toList();
    }

    // Lista todas categorias ativas e inativas
    public List<CategoriaResponse>listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            throw new NotFoundException(" Nenhuma Categoria Cadastrada");
        }
        return categorias.stream()
                .sorted((a, b) -> a.getNome().compareToIgnoreCase(b.getNome()))
                .map(CategoriaResponse::new)
                .toList();
    }

    // buscar Categoria pelo nome
    public CategoriaResponse buscarPorNome (String nome){

        if(nome == null || nome.isBlank()) {
            throw new InvalidDataException(" Nome da Categoria não foi informado");
        }
        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new NotFoundException("Categoria não foi encontrada com esse nome : " + nome));
        return new CategoriaResponse(categoria);
    }

    //buscar categoria pelo ID
    public CategoriaResponse buscarPorId (UUID id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma categoria encontrada com esse ID"));
        return new CategoriaResponse(categoria);
    }

    // referente ao PUT
    public CategoriaResponse atualizarCategoria (UUID id , CategoriaUpdateRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma categoria encontrada com esse ID"));
        if (categoria.getStatusCategoria() == StatusCategoria.INATIVA) {
            throw new InvalidDataException("Não é possivel editar uma categoria Inativa");
        }
        boolean nomeExiste = request.getNome() != null && !request.getNome().isBlank();

        if (nomeExiste && categoriaRepository.existsByNomeIgnoreCase(request.getNome())
                && !categoria.getNome().equalsIgnoreCase(request.getNome())) { // valida se ja tem categoria com esse nome
            throw new InvalidDataException("Já existe uma categoria com esse nome.");
        }
        // permite alterar categoria desejada apenas
        if (nomeExiste) {
            categoria.setNome(request.getNome());
        }
        if (request.getDescricao() != null && !request.getDescricao().isBlank()) {
            categoria.setDescricao(request.getDescricao());
        }
        categoriaRepository.save(categoria);
        return new CategoriaResponse(categoria);
    }

    // PATCH ativar e inativar categoria
    public CategoriaResponse alterarStatus (UUID id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma categoria encontrada com esse ID"));
        if (categoria.getStatusCategoria() == StatusCategoria.ATIVA) {
            categoria.setStatusCategoria(StatusCategoria.INATIVA);
        } else {
            categoria.setStatusCategoria(StatusCategoria.ATIVA);
        }
        categoriaRepository.save(categoria);
        return new CategoriaResponse(categoria);
    }


    // referente ao DELETE
    public void deletarCategoria(UUID id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma categoria encontrada com esse ID"));

       /* if (produtoRepository.existsByCategoriaId(id)) {
            throw new InvalidDataException("Não é possível deletar uma categoria com produtos vinculados.");
        }*/

        if (subcategoriaRepository.existsByCategoriaId(id)) {
            throw new InvalidDataException("Não é possível deletar uma categoria com subcategorias vinculadas.");
        }

        categoriaRepository.delete(categoria);
    }

}



