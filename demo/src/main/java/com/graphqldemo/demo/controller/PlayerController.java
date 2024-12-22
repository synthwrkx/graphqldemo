package com.graphqldemo.demo.controller;

import com.graphqldemo.demo.model.Player;
import com.graphqldemo.demo.model.Team;
import com.graphqldemo.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @QueryMapping
    public List<Player> getPlayerList() {
        return playerService.getPlayerList();
    }

    @QueryMapping
    public Optional<Player> findPlayer(@Argument int id) {
        return playerService.findPlayer(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument int id, @Argument String name, @Argument Team team) {
        return playerService.createPlayer(id, name, team);
    }

    @MutationMapping
    public Player deletePlayer(@Argument int id) {
        return playerService.deletePlayer(id);
    }

    @MutationMapping
    public Player updatePlayer(@Argument int id, @Argument String name, @Argument Team team) {
        return playerService.updatePlayer(id, name, team);
    }
}
