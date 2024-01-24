package br.com.danielfreitassc.crud.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.com.danielfreitassc.crud.dtos.CrudDto;
import br.com.danielfreitassc.crud.models.CrudEntity;
import br.com.danielfreitassc.crud.repositories.CrudRepository;
import jakarta.validation.Valid;

@RestController
public class CrudController {

    @Autowired
    CrudRepository crudRepository;

    /*
    Este endpoint recebe um objeto CrudEntity 
    diretamente no corpo da requisição e salva no banco de dados. 
    É uma abordagem direta e simples, mas pode não ser a melhor
    prática se você precisar de mais flexibilidade ou manipulação 
    antes de salvar no banco de dados.
    */

    @PostMapping("/crud/supersimples")
    public CrudEntity createCrud(@RequestBody CrudEntity crudEntity){
        return crudRepository.save(crudEntity);
    }
    /*
     Este endpoint também recebe um objeto CrudEntity 
     no corpo da requisição, mas retorna uma resposta 
     mais elaborada usando ResponseEntity. 
     Ele permite configurar o status HTTP da resposta 
     e incluir cabeçalhos adicionais. É uma boa prática 
     para endpoints de criação.
    */
    @PostMapping("/crud/simples")
        public ResponseEntity<CrudEntity> saveCrud(@RequestBody CrudEntity crudEntity){
            return ResponseEntity.status(HttpStatus.CREATED).body(crudRepository.save(crudEntity));
    }
    /*
     Este endpoint recebe um objeto CrudDto no corpo da requisição, 
     converte-o para um objeto CrudEntity usando BeanUtils.copyProperties e, 
     em seguida, salva no banco de dados. Esta abordagem é útil quando você tem
     DTOs (Data Transfer Objects) específicos para a camada de apresentação e 
     entidades para a camada de persistência. Isso pode ajudar a manter uma separação 
     clara entre a camada de apresentação e a camada de persistência.
    */
    @PostMapping("/crud/dto")
        public ResponseEntity<CrudEntity> saveCrud(@RequestBody  @Valid CrudDto crudDto){
        var crudEntity = new CrudEntity();
        BeanUtils.copyProperties(crudDto, crudEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(crudRepository.save(crudEntity));
    }
    /**
    * Endpoint responsável por criar um novo recurso CrudEntity no sistema, realizando verificações
    * de segurança, validações personalizadas e persistência no banco de dados.
    *
    * param crudDto Objeto DTO (Data Transfer Object) contendo os dados para criar o CrudEntity.
    * return ResponseEntity contendo a resposta da criação, podendo incluir o novo CrudEntity ou
    * mensagens de erro em caso de falhas de segurança ou validação.
    */
    @PostMapping("/crud/completo")
    public ResponseEntity<?> saveCompleteCrud(@RequestBody @Valid CrudDto crudDto) {
        // Realizar verificações de segurança ou validações personalizadas
        if (!isUserAuthorized()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso proibido");
        }

        // Mapear o DTO para a entidade
        var crudEntity = new CrudEntity();
        BeanUtils.copyProperties(crudDto, crudEntity);

        // Realizar validações adicionais, se necessário
        if (!isValidCrudEntity(crudEntity)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
        }

        // Salvar no banco de dados
        CrudEntity savedEntity = crudRepository.save(crudEntity);

        // Retornar resposta de sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    // Métodos de segurança ou validações personalizadas
    private boolean isUserAuthorized() {
        // Implementar lógica de autenticação ou autorização conforme necessário
        // Exemplo: verificar se o usuário tem as permissões adequadas
        return true;
    }

    private boolean isValidCrudEntity(CrudEntity crudEntity) {
        // Implementar lógica de validação adicional conforme necessário
        // Exemplo: verificar se os campos obrigatórios estão preenchidos corretamente
        return crudEntity.getName() != null && !crudEntity.getName().isEmpty();
    }
    
    /*
    Endpoint responsável por criar uma entidade no banco de dados ou verificar se uma entidade com o mesmo nome já existe.
    param crudEntity Objeto JSON enviado no corpo da requisição, representando a entidade a ser criada.
    return ResponseEntity contendo o resultado da operação. Se a entidade já existir, retorna um erro 400 (Bad Request),
    caso contrário, retorna 201 (Created) com os detalhes da entidade criada.
    */
    
    @PostMapping("/crud/verificacao")
    public ResponseEntity<?> createVerify(@RequestBody CrudEntity crudEntity) {
        var crud = this.crudRepository.findByName(crudEntity.getName());

        if (crud != null) {
            System.out.println("Usuário já existe");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        var crudCreated = this.crudRepository.save(crudEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(crudCreated);
    }
    /*Exempo de POST mais comum em projetos legados*/
    // @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE) // Produz e consome JSON
    // public CrudEntity create(@RequestBody CrudEntity crudEntity) {
    //    return crudRepository.save(crudEntity);
    //}
    
    /* End point de busca de dados em base de dados um exemplo bem simples usando recursos que repository nos da como findAll()*/
    @GetMapping("/crud")
    public ResponseEntity<List<CrudEntity>> getAllCrud(){
        return ResponseEntity.status(HttpStatus.OK).body(crudRepository.findAll());
    }
    /* Este endpoint está aplicando o usdo de HATEOS que nada mais e que link que são criados para cada endpoint unitario lembrando para uso de  hateos precisamos que te o getOneCrud pronto */
    @GetMapping("/crud/hateos")
    public ResponseEntity<List<CrudEntity>>  getAllProducts(){
        List<CrudEntity> crudList = crudRepository.findAll(); 
        if(!crudList.isEmpty()){ 
            for(CrudEntity crud : crudList) {
                Long id = crud.getId();
                crud.add(linkTo(methodOn(CrudController.class).getOneCrud(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(crudList); 
    }

    /* End point get que retorna um produto por id com um método do repository o findById() ou diz que o produto não foi encontrado */
    @GetMapping("/crud/{id}")
    public ResponseEntity<Object> getOneCrud(@PathVariable(value = "id") Long id){
        Optional<CrudEntity> crudOne = crudRepository.findById(id);
        if(crudOne.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(crudOne.get());
    }
    /* Endpoint com hateos Aqui adicionei está linha para fazer link para a lista de todos os produtos */
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id){
        Optional<CrudEntity> crudOne = crudRepository.findById(id);
        if(crudOne.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Crud não encontrado");
        }
        crudOne.get().add(linkTo(methodOn(CrudController.class).getAllProducts()).withRel("Lista de Crud"));
        return ResponseEntity.status(HttpStatus.OK).body(crudOne.get());
    }

    /* Endoint de atualização*/
    @PutMapping("/crud/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id, @RequestBody @Valid CrudDto productRecordDTO) {
        Optional<CrudEntity> productOne = crudRepository.findById(id);
        if(productOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado");
        }
        var crudEntity = productOne.get();
        BeanUtils.copyProperties(productRecordDTO, crudEntity);
        return ResponseEntity.status(HttpStatus.OK).body(crudRepository.save(crudEntity));
    }

    /* Endpoint de deleção */
    @DeleteMapping("/crud/{id}")
    public ResponseEntity<Object> deleteCrud(@PathVariable(value = "id") Long id) {
        Optional<CrudEntity> productOne = crudRepository.findById(id);
        if(productOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrao");
        }
        crudRepository.delete(productOne.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto removido com sucesso");
    }
    
}
 