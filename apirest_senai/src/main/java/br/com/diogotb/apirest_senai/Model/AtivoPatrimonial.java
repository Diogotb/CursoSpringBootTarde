package br.com.diogotb.apirest_senai.Model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class AtivoPatrimonial implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "ambiente_id", nullable = false)
    private Ambiente ambiente;

    // Getters and Setters

}
