package com.webapp.escola_xyz_b.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.escola_xyz_b.Model.Administrador;
import com.webapp.escola_xyz_b.Repository.AdministradorRepository;
import com.webapp.escola_xyz_b.Repository.VerificaCadastroAdmRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdministradorController {

    @Autowired
    AdministradorRepository ar;

    @Autowired
    VerificaCadastroAdmRepository vcar;

    boolean acessoAdm = false;

    @PostMapping("cadastrar-adm")
    public String cadastrarAdmBD(Administrador adm) {
        boolean verificaCpf = vcar.existsById(adm.getCpf());
        if (verificaCpf) {
            ar.save(adm);
            System.out.println("Cadastro Realizado com Sucesso");
        } else {
            System.out.println("Falha ao Cadastrar");

        }
        return "/login/login-adm";
    }

    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        String vaiPara = "";
        if (acessoAdm) {
            vaiPara = "interna/interna-adm";
        } else {
            vaiPara = "login/login-adm";
        }
        return vaiPara;
    }

    @PostMapping("acesso-adm")
    public ModelAndView acessoAdm(@RequestParam String cpf,
            @RequestParam String senha,
            RedirectAttributes redirectAttributes) {
        String url = "";
        ModelAndView mv = new ModelAndView();
        try {
            boolean verificaCpf = ar.existsById(cpf);
            boolean verificaSenha = ar.findByCpf(cpf).getSenha().equals(senha);

            if (verificaCpf && verificaSenha) {
                acessoAdm = true;

                url = "redirect:/interna-adm";
                mv.setViewName(url);
            } else {
                String mensagem = "Login Não Efetuado: CPF ou senha incorretos";
                System.out.println(mensagem);
                redirectAttributes.addFlashAttribute("msg", mensagem);
                redirectAttributes.addFlashAttribute("classe", "vermelho");
                url = "redirect:/login-adm";
                mv.setViewName(url);
            }
            return mv;

        } catch (Exception e) {
            String mensagem = "Login Não Efetuado: CPF ou senha incorretos";
            System.out.println(mensagem);
            redirectAttributes.addFlashAttribute("msg", mensagem);
            redirectAttributes.addFlashAttribute("classe", "vermelho");
            url = "redirect:/login-adm";
            return mv;
        }

    }

}
