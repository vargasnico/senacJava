/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pessoa;

/**
 *
 * @author jbferraz
 */
public class PessoaDAO {

    public void cadastrarPessoaDAO(Pessoa pVO) {
        String sql = "insert into pessoas values (null, ?,?,?,?)";
        // try-with-resources fecha a conexão e o statement mesmo se ocorrer erro.
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, pVO.getNome());
            pst.setString(2, pVO.getCpf());
            pst.setString(3, pVO.getEndereco());
            pst.setString(4, pVO.getTelefone());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Pessoa.\n"
                    + e.getMessage());
        }
    }//fim cadastroPessoa

    public ArrayList<Pessoa> getPessoas() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement("select * from pessoas");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                pessoas.add(montarPessoa(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pessoas.\n"
                    + e.getMessage());
        }
        return pessoas;
    }

    public Pessoa getPessoaByDoc(String cpf) {
        Pessoa p = new Pessoa();
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement("select * from pessoas where cpf = ?")) {
            pst.setString(1, cpf);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    p = montarPessoa(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar CPF.\n"
                    + e.getMessage());
        }
        return p;
    }

    public void atualizarPessoaDAO(Pessoa pVO) {
        String sql = "update pessoas set nome = ?, endereco = ?, telefone = ?"
                + " where cpf = ?";
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, pVO.getNome());
            pst.setString(2, pVO.getEndereco());
            pst.setString(3, pVO.getTelefone());
            pst.setString(4, pVO.getCpf());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Pessoa.\n"
                    + e.getMessage());
        }
    }

    public void deletarPessoaDAO(String cpf) {
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement("delete from pessoas where cpf = ?")) {
            pst.setString(1, cpf);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Pessoa.\n"
                    + e.getMessage());
        }
    }

    private Pessoa montarPessoa(ResultSet rs) throws SQLException {
        // lado do java |x| lado do banco
        Pessoa p = new Pessoa();
        p.setIdPessoa(rs.getInt("idPessoa"));
        p.setNome(rs.getString("nome"));
        p.setCpf(rs.getString("cpf"));
        p.setEndereco(rs.getString("endereco"));
        p.setTelefone(rs.getString("telefone"));
        return p;
    }

}
