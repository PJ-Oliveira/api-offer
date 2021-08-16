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

import javax.transaction.Transactional;
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

    @PostMapping("/add")
    @ApiOperation(tags = {"Cadastro"}, value="Cadastro de Ofertas")
    public ResponseEntity<?> addCliente(@RequestBody OfertaFORM FORM, UriComponentsBuilder uriBuilder){

        try{
            Oferta oferta = FORM.converterFORM(OS);
            URI uri = uriBuilder.path("{id}").buildAndExpand(oferta.getId()).toUri();
            return ResponseEntity.created(uri).body(new OfertaDTO().convertDTO(oferta));
        } catch (DataIntegrityViolationException SQL){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oferta já cadastrada");
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(tags = {"Delete"}, value="Delete Ofertas")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Oferta> optional = OS.findOfertaById(id);
        if (optional.isPresent()) {
            OS.deleteOferta(id);
            return ResponseEntity.ok().body("Deletado com sucesso");
        }
        return ResponseEntity.ok().body("Essa oferta não existe");
    }


}
