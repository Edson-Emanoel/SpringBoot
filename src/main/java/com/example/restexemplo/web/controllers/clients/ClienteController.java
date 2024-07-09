package com.example.restexemplo.web.controllers.clients;

// import java.util.ArrayList;
// import com.example.restexemplo.core.models.Client;
import java.util.Map;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restexemplo.core.repository.ClientRepository;
import com.example.restexemplo.web.dtos.Client.ClientForm;
import com.example.restexemplo.web.dtos.Client.ClientViewModel;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClienteController {
    
    private final ClientRepository clientRepository;

    @GetMapping // Listar(View)
    public ModelAndView index(){
    /*** Opção 2  */
        var clients = clientRepository.findAll()
        .stream()
        .map(ClientViewModel::of)
        .toList();
    
        var model = Map.of("clients", clients);
        return new ModelAndView("clients/index", model);
    }

    @GetMapping("/create") // Rota Cadastro(View) Web
    public ModelAndView create(){
        var model = Map.of("clientForm", new ClientForm());
        return new ModelAndView("clients/create", model);
    }

    @PostMapping("/create") // Rota Cadastro Cadastro(Function)
    public String create(ClientForm clientForm){
        var client = clientForm.toClient();
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        var client = clientRepository.findById(id); 
       if(!client.isPresent()){
            throw new NoSuchElementException("Cliente não encontrado");
       }
       var model = Map.of("clientForm", ClientForm.of(client.get()));
       return new ModelAndView("clients/edit", model);
    }

    @PutMapping("/edit/{id}") // Rota Cadastro Cadastro(Function)
    public String edit(@PathVariable Long id, ClientForm clientForm){
        if(!clientRepository.existsById(id)){
            throw new NoSuchElementException("Cliente não encontrado");
        }
        var client = clientForm.toClient();
        client.setId(id);
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        if(!clientRepository.existsById(id)){
            throw new NoSuchElementException("Cliente não encontrado");
        }
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }

}
