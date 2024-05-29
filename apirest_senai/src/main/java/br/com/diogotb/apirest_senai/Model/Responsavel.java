package br.com.diogotb.apirest_senai.Model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
public class Responsavel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(mappedBy = "responsavel")
    private Ambiente ambiente;
    
}
