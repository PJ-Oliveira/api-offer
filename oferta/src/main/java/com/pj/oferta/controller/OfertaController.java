package com.pj.oferta.controller;

import com.pj.oferta.domain.Oferta;
import com.pj.oferta.domain.dto.OfertaDTO;
import com.pj.oferta.domain.form.OfertaFORM;
import com.pj.oferta.service.OfertaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/oferta")
@Api(tags = {"Oferta"}, value = "Controller Offer")
@CrossOrigin(origins = "*")
public class OfertaController {
    private final OfertaService OS;

    public OfertaController(OfertaService os) {
        this.OS = os;
    }


    @GetMapping
    @ApiOperation(httpMethod = "GET", nickname = "getAllOferta", notes = "Lista todas as ofertas",tags = {"Listagem"}, value="Veja todas as ofertas")
    public ResponseEntity<List<OfertaDTO>> findAll()

    {
        List<Oferta> findList = OS.findAllOferta();
        OfertaDTO dto = new OfertaDTO();
        return ResponseEntity.ok().body(dto.convertDTO(findList));
    }

    @GetMapping("{id}")
    @ApiOperation(httpMethod = "GET", nickname = "getByIdOferta", notes = "Busque a oferta pelo seu respectivo ID",tags = {"Busque pelo ID"}, value="Encontre oferta por ID")
    public ResponseEntity<?> findOneOferta(@PathVariable long id){
        try {
            Oferta oferta = OS.getById(id);
            OfertaDTO dto = new OfertaDTO();
            return ResponseEntity.ok().body(dto.convertDTO(oferta));
        }
        catch (EntityNotFoundException x){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID " + id + " não existe.");
        }
    }



    @PostMapping("/add")
    @ApiOperation(httpMethod = "POST", nickname = "cadastraOferta", notes = "O formato da data deve ser cadastrado assim: dd/MM/aaaa", tags = {"Cadastro"}, value="Cadastro de Ofertas")
    public ResponseEntity<?> addCliente(@Valid @RequestBody OfertaFORM FORM, UriComponentsBuilder uriBuilder){

        try{
            Oferta oferta = FORM.converterFORM(OS);
            URI uri = uriBuilder.path("{id}").buildAndExpand(oferta.getId()).toUri();
            return ResponseEntity.created(uri).body(new OfertaDTO().convertDTO(oferta));
        } catch (DataIntegrityViolationException SQL){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oferta já cadastrada");
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(httpMethod = "DELETE", nickname = "deleteOferta", notes = "Delete ofertas pelo seus respectivos IDs",tags = {"Delete"}, value="Delete Ofertas")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Oferta> optional = OS.findOfertaById(id);
        if (optional.isPresent()) {
            OS.deleteOferta(id);
            return ResponseEntity.ok().body("Deletado com sucesso");
        }
        return ResponseEntity.ok().body("Essa oferta não existe");
    }

    @ApiOperation(httpMethod = "PUT", nickname = "atualizeOferta", notes = "Atualize uma oferta especificando seu respectivo ID",tags = {"Atualização"}, value = "Atualizar oferta pelo ID.")
    @PutMapping
    public ResponseEntity<?> AlterOferta(@RequestParam Long id, OfertaFORM FORM) {
        try {
            Oferta oferta = OS.getById(id);
            if (oferta != null) {
                oferta.setId_Produto(oferta.getId_Produto());
                oferta.setInicio(oferta.getInicio());
                oferta.setFim(oferta.getFim());
                oferta.setDescricao(oferta.getDescricao());
                oferta.setStatus(oferta.getStatus());
                OS.addOferta(oferta);
                OfertaDTO DTO = new OfertaDTO();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(DTO.convertDTO(oferta));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oferta não encontrada");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}
