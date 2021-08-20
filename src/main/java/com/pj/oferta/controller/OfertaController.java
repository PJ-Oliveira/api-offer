package com.pj.oferta.controller;

//import com.pj.oferta.configuration.rabbit.RabbitMQConstantes;
import com.pj.oferta.domain.Oferta;
import com.pj.oferta.domain.dto.OfertaDTO;
import com.pj.oferta.domain.form.OfertaFORM;
import com.pj.oferta.repository.OfertaRepository;
import com.pj.oferta.service.OfertaService;
//import com.pj.oferta.service.RabbitMQService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final OfertaService ofertaService;

    @Autowired
    private OfertaRepository ofertaRepository;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET", nickname = "getAllOferta", notes = "Lista todas as ofertas",tags = {"Listagem"}, value="Veja todas as ofertas")
    public ResponseEntity<List<OfertaDTO>> findAll()

    {
        List<Oferta> findList = ofertaService.findAllOferta();
        OfertaDTO dto = new OfertaDTO();
        return ResponseEntity.ok().body(dto.convertDTO(findList));
    }

    @GetMapping("{id}")
    @ApiOperation(httpMethod = "GET", nickname = "getByIdOferta", notes = "Busque a oferta pelo seu respectivo ID",tags = {"Busque pelo ID"}, value="Encontre oferta por ID")
    public ResponseEntity<?> findOneOferta(@Valid @PathVariable long id){
        try {
            Oferta oferta = ofertaService.getById(id);
            OfertaDTO dto = new OfertaDTO();
            return ResponseEntity.ok().body(dto.convertDTO(oferta));
        }
        catch (EntityNotFoundException x){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID " + id + " não existe.");
        }
    }

    @PostMapping("/addOferta")
    @Transactional
    @ApiOperation(httpMethod = "POST", nickname = "cadastraOferta", notes = "O formato da data deve ser cadastrado assim: yyyy-MM-dd HH:mm:ss", tags = {"Cadastro"}, value="Cadastro de Ofertas")
    public ResponseEntity<?> addOferta(@Valid @RequestBody OfertaFORM FORM, UriComponentsBuilder uriBuilder){
        try{
            Oferta oferta = FORM.converterFORM(ofertaService);
            URI uri = uriBuilder.path("{id}").buildAndExpand(oferta.getId()).toUri();
            return ResponseEntity.created(uri).body(new OfertaDTO().convertDTO(oferta));
        } catch (DataIntegrityViolationException SQL){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oferta já cadastrada");
        }
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(httpMethod = "DELETE", nickname = "deleteOferta", notes = "Delete ofertas pelo seus respectivos IDs",tags = {"Delete"}, value="Delete Ofertas")
    @Transactional
    public ResponseEntity<?> deleteOferta(@Valid @PathVariable Long id) {
        Optional<Oferta> optional = ofertaService.findOfertaById(id);
        if (optional.isPresent()) {
            ofertaService.deleteOferta(id);
            return ResponseEntity.ok().body("Deletado com sucesso");
        }
        return ResponseEntity.ok().body("Essa oferta não existe");
    }

    @PutMapping("{id}")
    @ApiOperation(httpMethod = "PUT", nickname = "atualizeOferta", notes = "Atualize uma oferta especificando seu respectivo ID",tags = {"Atualização"}, value = "Atualizar")
    public ResponseEntity<?> updateOferta(@Valid @RequestBody OfertaFORM FORM, UriComponentsBuilder uriBuilder){

        try{
            Oferta oferta = FORM.converterFORM(ofertaService);
            URI uri = uriBuilder.path("{id}").buildAndExpand(oferta.getId()).toUri();
            //this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_OFERTA, oferta);
            return ResponseEntity.created(uri).body(new OfertaDTO().convertDTO(oferta));
        } catch (DataIntegrityViolationException SQL){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Digite novamente");
        }
    }


}
