package com.webapp.escola_xyz_b.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.escola_xyz_b.Model.Administrador;
import com.webapp.escola_xyz_b.Repository.AdministradorRepository;
import com.webapp.escola_xyz_b.Repository.VerificaCadastroAdmRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdministradorController {

    @Autowired
    AdministradorRepository ar;

    @Autowired
    VerificaCadastroAdmRepository vcar;

    boolean acessoAdm = false;

    @PostMapping("cadastrar-adm")
    public ModelAndView cadastrarAdmBD(Administrador adm, RedirectAttributes attributes) {
        boolean verificaCpf = vcar.existsById(adm.getCpf());
        ModelAndView mv = new ModelAndView("redirect:/login-adm");
        if (verificaCpf) {
            ar.save(adm);
            String mensagem = "Cadastro Realizado com Sucesso";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "verde");
        } else {
            String mensagem = "Cadastro não Permitido";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");

        }
        return mv;
    }

    @GetMapping("/interna-adm")
    public ModelAndView acessoPageInternaAdm(RedirectAttributes attributes) {
        String vaiPara = "";
        ModelAndView mv = new ModelAndView(vaiPara);
        if (acessoAdm) {
            vaiPara = "/interna/interna-adm";
        } else {
            String mensagem = "Cadastro não Permitido";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem);
            attributes.addFlashAttribute("classe", "vermelho");
            vaiPara = "redirect:/login-adm";
        }
        return mv;
    }

    @PostMapping("acesso-adm")
    public ModelAndView acessoAdm(@RequestParam String cpf,
            @RequestParam String senha,
            RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/interna-adm");
        try {
            boolean verificaCpf = ar.existsById(cpf);
            boolean verificaSenha = ar.findByCpf(cpf).getSenha().equals(senha);

            if (verificaCpf && verificaSenha) {
                acessoAdm = true;
            } else {
                String mensagem = "Login Não Efetuado: CPF ou senha incorretos";
                System.out.println(mensagem);
                redirectAttributes.addFlashAttribute("msg", mensagem);
                redirectAttributes.addFlashAttribute("classe", "vermelho");
                mv.setViewName("redirect:/login-adm");
            }
            return mv;

        } catch (Exception e) {
            String mensagem = "Login Não Efetuado: CPF ou senha incorretos";
            System.out.println(mensagem);
            redirectAttributes.addFlashAttribute("msg", mensagem);
            redirectAttributes.addFlashAttribute("classe", "vermelho");
            mv.setViewName("redirect:/login-adm");
            return mv;
        }

    }

}
