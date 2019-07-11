package com.fitnet.sveucilisna.services;

import com.fitnet.sveucilisna.models.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by User on 26.01.2018..
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findByTeam_Id(Long teamId);
}
