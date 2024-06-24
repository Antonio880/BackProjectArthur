package com.prototype.ProjectArthur.DTO;

import java.util.List;

public class ResultDTO {

    private Long provaId;
    private Long alunoId;
    private List<Resposta> respostas;
    private int acertos;

    public Long getProvaId() {
        return provaId;
    }

    public void setProvaId(Long provaId) {
        this.provaId = provaId;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public static class Resposta {
        private Long questaoId;
        private String resposta;
        private boolean correta;

        public Long getQuestaoId() {
            return questaoId;
        }

        public void setQuestaoId(Long questaoId) {
            this.questaoId = questaoId;
        }

        public String getResposta() {
            return resposta;
        }

        public void setResposta(String resposta) {
            this.resposta = resposta;
        }

        public boolean isCorreta() {
            return correta;
        }

        public void setCorreta(boolean correta) {
            this.correta = correta;
        }
    }
}
