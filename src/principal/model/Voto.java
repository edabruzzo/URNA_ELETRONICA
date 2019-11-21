/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.model;

/**
 *
 * @author Emm
 */
public class Voto {
    
    private int id_voto;
    private int id_eleicao;
    private int id_candidato;
    private int id_eleitor;

    public int getId_eleicao() {
        return id_eleicao;
    }

    public void setId_eleicao(int id_eleicao) {
        this.id_eleicao = id_eleicao;
    }

    public int getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(int id_candidato) {
        this.id_candidato = id_candidato;
    }

    public int getId_eleitor() {
        return id_eleitor;
    }

    public void setId_eleitor(int id_eleitor) {
        this.id_eleitor = id_eleitor;
    }

    public int getId_voto() {
        return id_voto;
    }

    public void setId_voto(int id_voto) {
        this.id_voto = id_voto;
    }
    
    
}
