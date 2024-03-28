package com.webapp.escola_xyz_b.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


import com.webapp.escola_xyz_b.Model.Administrador;
import com.webapp.escola_xyz_b.Repository.AdministradorRepository;
import com.webapp.escola_xyz_b.Repository.VerificaCadastroAdmRepository;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class AdministradorController {

    @Autowired
    AdministradorRepository ar;

    @Autowired
    VerificaCadastroAdmRepository vcar;
    
    @PostMapping("cadastrar-adm")
    public String cadastrarAdmBD(Administrador adm) {
        boolean verificaCpf = vcar.existsById(adm.getCpf());
        if(verificaCpf){
            ar.save(adm);
            System.out.println("Cadastro Realizado com Sucesso");
        }else{
            System.out.println("Falha ao Cadastrar");

        }
        
        return "/login/login-adm";
    }
    @PostMapping("acesso-adm")
    public String postMethodName(@RequestBody String entity) {
        //metodo para verificar acesso
        
        
        return entity;
    }
    
    
}
