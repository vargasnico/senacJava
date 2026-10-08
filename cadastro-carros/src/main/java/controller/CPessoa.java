/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Pessoa;

/**
 *
 * @author jbferraz
 */
public class CPessoa {

    ArrayList<Pessoa> pessoas = new ArrayList<>();
    int idPessoa = 1;
    
    /**
     * Autoincrement idPessoa
     * @return 
     */
    public int geraID(){
        return this.idPessoa++;
    }

    /**
     * Método addPessoa adiciona Pessoa no ArrayList pessoas
     *
     * @param p
     */
    public void addPessoa(Pessoa p) {
        this.pessoas.add(p);
    }

    /**
     * Método removePessoa remove Pessoa do ArrayList pessoas
     *
     * @param pes
     */
    public void removePessoa(Pessoa pes) {
        this.pessoas.remove(pes);
    }

    public ArrayList<Pessoa> getPessoas() {
        return this.pessoas;
    }

    /**
     * Método getPessoaCPF retorna uma Pessoa caso encontre 
     * correspondência no CPF do ArrayList pessoas, caso contrário
     * retorna null.
     * @param CPF
     * @return 
     */
    public Pessoa getPessoaCPF(String CPF) {
        Pessoa p = null;
        for (Pessoa pes : pessoas) {
            if (pes.getCpf().equals(CPF)) {
                p = pes;
                break;
            }
        }//fim foreach
        return p;
    }
    
    public void mockPessoas(){
        Pessoa p1 = new Pessoa();
        p1.setIdPessoa(geraID());
        p1.setCpf("30668518006");
        p1.setNome("Juvenal das Candongas");
        p1.setTelefone("51999889988");
        p1.setEndereco("Rua das Margaridas");
        addPessoa(p1);
        Pessoa p2 = new Pessoa();
        p2.setIdPessoa(geraID());
        p2.setCpf("85606196077");
        p2.setNome("Madalena Espirito Santo");
        p2.setTelefone("51988998899");
        p2.setEndereco("Rua sem saída");
        addPessoa(p2);
        
    }
    
}//fim classe controller
