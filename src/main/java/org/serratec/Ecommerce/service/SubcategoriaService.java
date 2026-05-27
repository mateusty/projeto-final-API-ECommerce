package org.serratec.Ecommerce.service;


import org.serratec.Ecommerce.repository.SubcategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class SubcategoriaService {

    private final SubcategoriaRepository subcategoriaRepository;

    public SubcategoriaService (SubcategoriaRepository  subcategoriaRepository){
        this.subcategoriaRepository =subcategoriaRepository;
    }

}
