package br.com.diogotb.apirest_senai.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogotb.apirest_senai.Model.Ambiente;
import br.com.diogotb.apirest_senai.Repository.AmbienteRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/ambientes")
public class AmbienteController {
    
    @Autowired
    private AmbienteRepository repository;

    @GetMapping
    public List<Ambiente> getAllAmbientes() {
        return (List<Ambiente>) repository.findAll();
    }

    @PostMapping
    public Ambiente createAmbiente(@RequestBody Ambiente ambiente) {
        return repository.save(ambiente);
    }
    @GetMapping("/{id}")
    public Optional<Ambiente> getMethodName(@PathVariable Long id) {
        return repository.findById(id);
    }
    
}
