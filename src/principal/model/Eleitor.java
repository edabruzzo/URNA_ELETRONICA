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
public class Eleitor {
    
    private int id_Eleitor;
    private String nome;
    private int tituloEleitor;
    private int RG;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getId_Eleitor() {
        return id_Eleitor;
    }

    public void setId_Eleitor(int id_Eleitor) {
        this.id_Eleitor = id_Eleitor;
    }

    public int getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(int tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public int getRG() {
        return RG;
    }

    public void setRG(int RG) {
        this.RG = RG;
    }
    
}
