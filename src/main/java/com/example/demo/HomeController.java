package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("players", playerRepository.findAll());
        return "index";
    }

    @GetMapping("/addTeam")
    public String addTeam(Model model){
        model.addAttribute("team", new Team());
        return "teamform";
    }

    @PostMapping("/processTeam")
    public String processTeam(@ModelAttribute Team team){
        teamRepository.save(team);
        return "redirect:/";
    }

    @GetMapping("/addPlayer")
    public String addPlayer(Model model){
        model.addAttribute("teams",teamRepository.findAll());
        model.addAttribute("player", new Player());
        return "playerform";
    }

    @PostMapping("/processPlayer")
    public String processPlayer(@ModelAttribute Player player){
        playerRepository.save(player);
        return "redirect:/";
    }

    @GetMapping("/addGame")
    public String addGame(Model model){
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("game", new Game());
        return "gameform";
    }

    @PostMapping("/processGame")
    public String processGame(@ModelAttribute Game game){
        String idArray = game.getTeamIds();
        Set<Team> temp = new HashSet<>();
        String[] ids = idArray.split(",");
        for (String s: ids){
            long id = Long.parseLong(s);
            Team team  = teamRepository.findById(id).get();
            temp.add(team);
        }
        game.setMatchup(temp);

        gameRepository.save(game);
        return "redirect:/";
    }

    @RequestMapping("/updateGame/{id}")
    public String updateGame(@PathVariable("id") long gameid, Model model){
        Game game = gameRepository.findById(gameid).get();
        Set<Long> ids = new HashSet<>();
        for (Team team : game.getMatchup()){
            ids.add(team.getTeamid());
        }
        model.addAttribute("teamIds",ids);
        model.addAttribute("game", gameRepository.findById(gameid).get());
        model.addAttribute("teams", teamRepository.findAll());
        return "gameform";
    }

}
