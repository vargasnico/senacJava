/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import dao.DAOFactory;
import dao.PessoaDAO;
import java.util.ArrayList;
import model.Pessoa;

/**
 *
 * @author jbferraz
 */
public class PessoaServicos {
    
    public void cadastroPessoa(Pessoa pVO){
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        pDAO.cadastrarPessoaDAO(pVO);
    }
    
    public ArrayList<Pessoa> getPessoas(){
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.getPessoas();
    }
    
    public Pessoa getPessoaByDoc(String cpf){
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.getPessoaByDoc(cpf);
    }
    
    public void atualizarPessoa(Pessoa pVO){
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        pDAO.atualizarPessoaDAO(pVO);
    }
    
    public void deletarPessoa(String cpf){
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        pDAO.deletarPessoaDAO(cpf);
    }
}
