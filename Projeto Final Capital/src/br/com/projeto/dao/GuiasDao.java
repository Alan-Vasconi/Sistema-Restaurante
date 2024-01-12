/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.guias;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alans
 */
public class GuiasDao {

    private Connection con;

    public GuiasDao() {
        this.con = new ConnectionFactory().getConnection();

    }

    // Metodo CadastrarGuia
    public void cadastrarGuia(guias obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "insert into tb_guias(id,nome,cpf,celular,cidade)" + "values(?,?,?,?,?)";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getCelular());
            stmt.setString(5, obj.getCidade());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    // Metodo Alterar Guia
    public void alterarGuia(guias obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "update tb_guias set nome=?,cpf=?,celular=?,cidade=? where id=?";
            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getCelular());
            stmt.setString(4, obj.getCidade());
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    // Metodo Excluir Guia
    public void excluirGuia(guias obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "delete from tb_guias where id=?";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public List<guias> listarGuias() {
        try {
            List<guias> lista = new ArrayList<>();

            String sql = "select * from tb_guias";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                guias obj = new guias();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setCelular(rs.getString("celular"));
                obj.setCidade(rs.getString("cidade"));

                lista.add(obj);
            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public guias consultarNome(String nome) {
        try {
            String sql = "select * from tb_guias where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            guias obj = new guias();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setCelular(rs.getString("celular"));
                obj.setCidade(rs.getString("cidade"));
            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }

    public List<guias> buscaGuianome(String nome) {
        try {
            List<guias> lista = new ArrayList<>();

            String sql = "select * from tb_guias where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                guias obj = new guias();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setCelular(rs.getString("celular"));
                obj.setCidade(rs.getString("cidade"));

                lista.add(obj);
            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
}
