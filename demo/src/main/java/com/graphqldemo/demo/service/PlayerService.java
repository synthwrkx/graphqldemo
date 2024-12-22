package com.graphqldemo.demo.service;

import com.graphqldemo.demo.model.Player;
import com.graphqldemo.demo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> playerList = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Optional<Player> findPlayer(int id) {
        Optional<Player> player = playerList.stream()
                .filter(tempPlayer -> tempPlayer.id() == id)
                .findFirst();

        return player;
    }

    public Player createPlayer(int id, String name, Team team) {
        Player player = new Player(id, name, team);
        playerList.add(player);

        return player;
    }

    public Player deletePlayer(int id) {
        Player player = playerList.stream()
                .filter(tempPlayer -> tempPlayer.id() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        playerList.remove(player);

        return player;
    }

    public Player updatePlayer(int id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);

        Optional<Player> player = playerList.stream()
                .filter(tempPlayer -> tempPlayer.id() == id)
                .findFirst();

        if(player.isPresent()) {
            Player oldPlayer = player.get();
            int index = playerList.indexOf(oldPlayer);
            playerList.set(index, updatedPlayer);
        } else {
            throw new IllegalArgumentException("Invalid player");
        }

        return updatedPlayer;
    }

    @PostConstruct
    public void init() {
        playerList.add(new Player(1, "David Raya", Team.ARSENAL));
        playerList.add(new Player(2, "William Saliba", Team.ARSENAL));
        playerList.add(new Player(3, "Joe Gauci", Team.ASTON_VILLA));
        playerList.add(new Player(4, "Tom Heaton", Team.MANCHESTER_UNITED));
        playerList.add(new Player(5, "Robert Sanchez", Team.CHELSEA));
    }
}
