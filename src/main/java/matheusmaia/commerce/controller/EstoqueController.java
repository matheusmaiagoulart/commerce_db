package matheusmaia.commerce.controller;

import jakarta.validation.Valid;
import matheusmaia.commerce.domain.Estoque.CadastrarEstoqueDTO;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.services.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/criarEstoque/{id}")
    public ResponseEntity criarEstoque(@PathVariable UUID id, @Valid @RequestBody CadastrarEstoqueDTO DTO){
        return estoqueService.criarEstoque(DTO, id);
    }
}


