package com.prototype.ProjectArthur.services;

import com.prototype.ProjectArthur.DTO.ResultDTO;
import org.springframework.stereotype.Service;

@Service
public class ResultadoService {

    public ResultDTO calcularResultado(ResultDTO result) {
        int acertos = 0;
        for (ResultDTO.Resposta resposta : result.getRespostas()) {
            if (resposta.isCorreta()) {
                acertos++;
            }
        }
        result.setAcertos(acertos);
        return result;
    }
}