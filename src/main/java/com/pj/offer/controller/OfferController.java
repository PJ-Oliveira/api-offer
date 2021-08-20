package com.pj.offer.controller;

//import com.pj.oferta.configuration.rabbit.RabbitMQConstantes;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.dto.OfferDTO;
import com.pj.offer.domain.form.OfferFORM;
import com.pj.offer.repository.OfferRepository;
import com.pj.offer.service.OfferService;
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
public class OfferController {

    private final OfferService offerService;

    @Autowired
    private OfferRepository offerRepository;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET", notes = "Lista todas as ofertas",tags = {"Listagem"}, value="Veja todas as ofertas")
    public ResponseEntity<List<OfferDTO>> findAll()

    {
        List<Offer> findList = offerService.findAllOferta();
        OfferDTO dto = new OfferDTO();
        return ResponseEntity.ok().body(dto.convertDTO(findList));
    }

    @GetMapping("{id}")
    @ApiOperation(httpMethod = "GET", notes = "Busque a oferta pelo seu respectivo ID",tags = {"Busque pelo ID"}, value="Encontre oferta por ID")
    public ResponseEntity<?> findOneOffer(@Valid @PathVariable long id){
        try {
            Offer offer = offerService.getById(id);
            OfferDTO dto = new OfferDTO();
            return ResponseEntity.ok().body(dto.convertDTO(offer));
        }
        catch (EntityNotFoundException x){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID " + id + " não existe.");
        }
    }

    @PostMapping("/addOffer")
    @Transactional
    @ApiOperation(httpMethod = "POST", notes = "O formato da data deve seguir esse modelo: 2021-08-25 01:01:01", tags = {"Cadastro"}, value="Cadastro de Ofertas")
    public ResponseEntity<?> addOffer(@Valid @RequestBody OfferFORM FORM, UriComponentsBuilder uriBuilder){
        try{
            Offer offer = FORM.converterFORM(offerService);
            URI uri = uriBuilder.path("{id}").buildAndExpand(offer.getId()).toUri();
            return ResponseEntity.created(uri).body(new OfferDTO().convertDTO(offer));
        } catch (DataIntegrityViolationException SQL){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oferta já cadastrada");
        }
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(httpMethod = "DELETE", notes = "Delete ofertas pelo seus respectivos IDs",tags = {"Delete"}, value="Delete Ofertas")
    @Transactional
    public ResponseEntity<?> deleteOffer(@Valid @PathVariable Long id) {
        Optional<Offer> optional = offerService.findOfferById(id);
        if (optional.isPresent()) {
            offerService.deleteOffer(id);
            return ResponseEntity.ok().body("Deletado com sucesso");
        }
        return ResponseEntity.ok().body("Essa oferta não existe");
    }

    @PutMapping("{id}")
    @ApiOperation(httpMethod = "PUT", notes = "Atualize uma oferta especificando seu respectivo ID",tags = {"Atualização"}, value = "Atualizar")
    public ResponseEntity<?> updateOffer(@Valid @RequestBody OfferFORM FORM, UriComponentsBuilder uriBuilder){

        try{
            Offer offer = FORM.converterFORM(offerService);
            URI uri = uriBuilder.path("{id}").buildAndExpand(offer.getId()).toUri();
            return ResponseEntity.created(uri).body(new OfferDTO().convertDTO(offer));
        } catch (DataIntegrityViolationException SQL){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Digite novamente");
        }
    }


}
