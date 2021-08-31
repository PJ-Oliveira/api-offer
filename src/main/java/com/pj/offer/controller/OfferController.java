package com.pj.offer.controller;
import com.pj.offer.domain.dto.OfferDto;
import com.pj.offer.domain.form.OfferForm;
import com.pj.offer.service.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/oferta")
@Api(tags = {"Oferta"}, value = "Controller Offer")
@CrossOrigin(origins = "*")
public class OfferController {

    @Autowired
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET", notes = "Lista todas as ofertas",tags = {"Listagem"}, value="Veja todas as ofertas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<List<OfferDto>> getAllPage(@PageableDefault(page = 0, size = 5)Pageable pageable){
        List<OfferDto> offerDTO = offerService.findAll(pageable);
        return ResponseEntity.ok().body(offerDTO);
    }

    @GetMapping("{id}")
    @ApiOperation(httpMethod = "GET", notes = "Busque a oferta pelo seu respectivo ID",tags = {"Busque pelo ID"}, value="Encontre oferta por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<OfferDto> findOneOffer(@Valid @PathVariable long id){
        OfferDto offerDTO = offerService.getById(id);
        return ResponseEntity.ok().body(offerDTO);
    }

    @PostMapping("/addOffer")
    @ApiOperation(httpMethod = "POST", notes = "O desconto deve ser de no mínimo 1% e no máximo 50%. O formato da data deve seguir esse modelo: 2021-08-25 01:01:01", tags = {"Cadastro"}, value="Cadastro de Ofertas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<OfferDto> create(@Valid @RequestBody OfferForm offerForm){
        OfferDto offerDto = offerService.save(offerForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(offerDto.getId()).toUri();
        return ResponseEntity.created(uri).body(offerDto);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(httpMethod = "DELETE", notes = "Delete ofertas pelo seus respectivos IDs",tags = {"Delete"}, value="Delete Ofertas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<?> deleteOffer(@Valid @PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @ApiOperation(httpMethod = "PUT", notes = "Atualize uma oferta especificando seu respectivo ID",tags = {"Atualização"}, value = "Atualizar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<OfferDto> updateOffer(@Valid @PathVariable Long id, @RequestBody OfferForm offerForm)
    {
        OfferDto offerDto = offerService.updateOffer(id, offerForm);
        return ResponseEntity.ok().body(offerDto);

    }


    @GetMapping("fim/{id}")
    @ApiOperation(tags = {"Busque pelo ID apenas a data final da promoção"}, value="Mostre apenas a data final")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<Date> fim(@PathVariable @Valid long id){
        OfferDto offerDTO = offerService.showJustDataFim(id);
        return ResponseEntity.ok().body(offerDTO.getFim());
    }

    @GetMapping("desconto/{id}")
    @ApiOperation(tags = {"Busque pelo ID apenas o valor do desconto de uma promoção"}, value="Mostre apenas o valor do desconto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<BigDecimal> desconto(@PathVariable @Valid long id){
        OfferDto offerDTO = offerService.showJustDesconto(id);
        return ResponseEntity.ok().body(offerDTO.getDesconto());
    }

    @GetMapping("existOrNot/{id}")
    @ApiOperation(tags = {"Verifique se a offer existe"}, value="A offer existe ou não?")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição bem sucedida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Sistema Indisponível")
    })
    public ResponseEntity<Long> exist(@PathVariable @Valid long id){
        OfferDto offerDTO = offerService.getById(id);
        return ResponseEntity.ok().body(offerDTO.getId());
    }





}
