package com.prototype.ProjectArthur.controller;

import com.prototype.ProjectArthur.DTO.QuestionDTO;
import com.prototype.ProjectArthur.DTO.ResultDTO;
import com.prototype.ProjectArthur.services.QuestaoService;
import com.prototype.ProjectArthur.services.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provas")
public class ProvaController {

    @Autowired
    private QuestaoService questaoService;

    @Autowired
    private ResultadoService resultadoService;

    @GetMapping("/gerar")
    public QuestionDTO[] gerarProva(@RequestParam String categoria, @RequestParam int limite) {
        return questaoService.getQuestions(categoria, limite);
    }

    @PostMapping("/responder")
    public ResultDTO responderProva(@RequestBody ResultDTO result) {
        return resultadoService.calcularResultado(result);
    }
}
