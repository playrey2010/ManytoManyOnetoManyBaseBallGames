package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        gameRepository.save(game);
        return "redirect:/";
    }

}
