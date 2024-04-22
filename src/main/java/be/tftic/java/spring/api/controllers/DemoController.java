package be.tftic.java.spring.api.controllers;

import be.tftic.java.spring.api.models.forms.OperationForm;
import be.tftic.java.spring.bll.DemoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    // GET - http://localhost:8080/hello
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }

    // GET - http://localhost:8080/calcul/add?a=...&b=...
    @GetMapping("/calcul/add")
    public int addition(
            @RequestParam("a") int membreA,
            @RequestParam("b") int membreB
    ){
        return membreA + membreB;
    }

    @GetMapping("/print/params")
    public void printParams(@RequestParam Map<String, Object> params){
        for (String key : params.keySet()) {
            System.out.println(key + " -> " + params.get(key));
        }
    }

    @GetMapping("/calcul")
    public int calcul(@RequestBody OperationForm form){
        return switch (form.getOperation()) {
            case '+' -> form.getA() + form.getB();
            case '-' -> form.getA() - form.getB();
            case '*' -> form.getA() * form.getB();
            case '/' -> form.getA() / form.getB();
            case '%' -> form.getA() % form.getB();
            default -> throw new RuntimeException("bad operation");
        };
    }

    @GetMapping("/print/headers/{headerName}")
    public void printHeaders(
            @RequestHeader HttpHeaders headers,
            @PathVariable String headerName
    ) {
        System.out.println("general headers");
        System.out.println(headerName +" -> "+ headers.get(headerName));
    }

    // Si on va sur /print/headers/accept, c'est bien cette ressource qui est choisi par
    // le dispatcher
    // Resource inutile car la resource juste au dessus fait deja ce travail et plus encore
    @GetMapping("/print/headers/accept")
    public void printAcceptHeader(@RequestHeader("Accept") List<String> accept){
        System.out.println("only accept header");
        System.out.println("accept -> "+accept);
    }

    @GetMapping("/print/request")
    public void printRequest(HttpServletRequest request){
        System.out.println("cookies -> " +request.getCookies());
        System.out.println("uri -> " +request.getRequestURI());
        System.out.println("url -> " +request.getRequestURL());
        System.out.println("method -> " +request.getMethod());
        System.out.println("attributes -> " +request.getAttributeNames());
    }

    // Ajouter un nouveau nom
    // POST - http://localhost:8080/??
//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({"/name/add", "/name"})
    public ResponseEntity<Void> addName(@RequestParam String name){
        demoService.add(name);
        HttpStatus code = HttpStatus.CREATED;
        HttpHeaders headers = new HttpHeaders();
        headers.add("mon-header", "v1,v2");
//        return new ResponseEntity<>(headers, code);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .header("2eHeader", "v3")
                .build();
    }


    // Récupérer un nom par rapport à son index
    @GetMapping("/name/get/{index:^\\d+$}")
    public ResponseEntity<String> getOneName(@PathVariable int index){
        String name = demoService.getOne(index);
        return ResponseEntity.ok(name);
    }


}
