package com.pj.offer.controller;

import com.pj.offer.domain.model.Offer;
import com.pj.offer.domain.dto.OfferDto;
import com.pj.offer.domain.form.OfferForm;
import com.pj.offer.service.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/offers/api/v1")
@Api(tags = {"Oferta"}, value = "Controller Offer")
@CrossOrigin(origins = "*")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/{id}")
    @ApiOperation(httpMethod = "GET", notes = "Busque a oferta pelo seu respectivo ID",
            tags = {"Busque pelo ID"}, value="Encontre oferta por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<?> getOfferByID(@PathVariable long id){
        OfferDto offerDto = offerService.findOfferByValidId(id);
        return ResponseEntity.status(HttpStatus.OK).body(offerDto);
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET", notes = "Lista todas as ofertas",
            tags = {"Listagem"}, value="Veja todas as ofertas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<List<OfferDto>> getAllOffers(@PageableDefault(page = 0, size = 5)Pageable pageable){
        List<OfferDto> offerDTO = offerService.findAll(pageable);
        return ResponseEntity.ok().body(offerDTO);
    }

    @Transactional
    @PostMapping
    @ApiOperation(httpMethod = "POST",
            notes = "O desconto deve ser de no mínimo 1% e no máximo 50%." +
                    "O formato da data deve seguir esse modelo: 2021-08-25." +
                    "Não é possível iniciar uma oferta antes do dia de hoje",
            tags = {"Cadastro"}, value="Cadastro de Ofertas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Recurso criado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<OfferDto> createNewOffer(@Valid @RequestBody OfferForm offerForm){
        OfferDto offerDto = offerService.save(offerForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(offerDto.getId()).toUri();
        return ResponseEntity.created(uri).body(offerDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(httpMethod = "DELETE", notes = "Delete ofertas pelo seus respectivos IDs",
            tags = {"Delete"}, value="Delete Ofertas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<?> deleteOffer(@Valid @PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(httpMethod = "PUT", notes = "Ative a oferta",
            tags = {"Ativar"}, value="Ative a oferta pelo IdProduct")
    public void activeOffer(@PathVariable Long id) {
        offerService.offerActivation(id, true);

    }

    @GetMapping("exist/{id}")
    public Optional<Offer> verifyIfOfferExist(@PathVariable long id){
        return offerService.getOptionalOfferByValidId(id);
    }

}
