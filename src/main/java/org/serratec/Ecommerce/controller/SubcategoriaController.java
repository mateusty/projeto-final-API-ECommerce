
import org.serratec.Ecommerce.service.SubcategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/subcategorias")

public class SubcategoriaController {
    public final SubcategoriaService subcategoriaService;

    public SubcategoriaController (SubcategoriaService subcategoriaService){
        this.subcategoriaService = subcategoriaService;
    }

}
