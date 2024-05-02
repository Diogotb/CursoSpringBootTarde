package br.com.diogotb.gamestore.Repository;

import org.springframework.data.repository.CrudRepository;

import br.com.diogotb.gamestore.Model.Game;

public interface GameRepository extends CrudRepository<Game,Integer>{
    
}
