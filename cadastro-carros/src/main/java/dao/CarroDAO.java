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
import model.Carro;
import model.Pessoa;
import servicos.PessoaServicos;
import servicos.ServicosFactory;

/**
 *
 * @author jbferraz
 */
public class CarroDAO {

    // Traz o carro junto com os dados do proprietário em uma única consulta.
    private static final String SELECT_CARRO_COM_PROPRIETARIO
            = "select c.*, p.idPessoa, p.nome, p.cpf, p.endereco, p.telefone "
            + "from carros c join pessoas p on c.proprietario = p.idPessoa";

    public void cadastrarCarroDAO(Carro cVO) {
        String sql = "insert into carros values (null, ?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cVO.getPlaca());
            pst.setString(2, cVO.getMarca());
            pst.setString(3, cVO.getModelo());
            pst.setInt(4, cVO.getAnoFab());
            pst.setInt(5, cVO.getAnoMod());
            pst.setString(6, cVO.getCor());
            pst.setString(7, cVO.getTpCambio());
            pst.setString(8, cVO.getCombustivel());
            pst.setInt(9, cVO.getProprietario().getIdPessoa());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Carro.\n"
                    + e.getMessage());
        }
    }

    public ArrayList<Carro> getCarros() {
        ArrayList<Carro> carros = new ArrayList<>();
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement(SELECT_CARRO_COM_PROPRIETARIO);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                carros.add(montarCarro(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar Carro.\n"
                    + e.getMessage());
        }

        return carros;
    }//fim getCarros

    public Carro getCarroByDoc(String placa) {
        Carro c = new Carro();
        String sql = SELECT_CARRO_COM_PROPRIETARIO + " where placa = ?";
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, placa);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    c = montarCarro(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar placa.\n" + e.getMessage());
        }
        return c;
    }//fim getCarroByDoc

    public void atualizarCarro(Carro cVO) {
        String sql = "update carros set cor = ?, tpCambio = ?, combustivel = ?, "
                + "proprietario = ? where placa = ?";
        // Busca o proprietário antes de abrir a conexão do update.
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        int idProprietario
                = pessoaS.getPessoaByDoc(cVO.getProprietario().getCpf()).getIdPessoa();
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cVO.getCor());
            pst.setString(2, cVO.getTpCambio());
            pst.setString(3, cVO.getCombustivel());
            pst.setInt(4, idProprietario);
            pst.setString(5, cVO.getPlaca());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar placa.\n" + e.getMessage());
        }
    }//fim atualizarCarro

    public void deletarCarro(String placa) {
        String sql = "delete from carros where placa = ?";
        try (Connection con = Conexao.getConexao();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, placa);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar carro.\n" + e.getMessage());
        }
    }//fim deletarCarro

    private Carro montarCarro(ResultSet rs) throws SQLException {
        Pessoa p = new Pessoa();
        p.setIdPessoa(rs.getInt("idPessoa"));
        p.setNome(rs.getString("nome"));
        p.setCpf(rs.getString("cpf"));
        p.setEndereco(rs.getString("endereco"));
        p.setTelefone(rs.getString("telefone"));

        Carro c = new Carro();
        c.setPlaca(rs.getString("placa"));
        c.setMarca(rs.getString("marca"));
        c.setModelo(rs.getString("modelo"));
        c.setAnoFab(rs.getInt("anoFab"));
        c.setAnoMod(rs.getInt("anoMod"));
        c.setCor(rs.getString("cor"));
        c.setTpCambio(rs.getString("tpCambio"));
        c.setCombustivel(rs.getString("combustivel"));
        c.setProprietario(p);
        return c;
    }

}//fim da classe
