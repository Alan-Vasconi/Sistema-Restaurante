/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alans
 */
public class FornecedoresDao {

    private Connection con;

    public FornecedoresDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarFuncionario(Fornecedores obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "insert into tb_fornecedores(nome,cnpj,celular,cidade)" + "values(?,?,?,?)";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCNPJ());
            stmt.setString(3, obj.getCelular());
            stmt.setString(4, obj.getCidade());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public void alterarFuncionario(Fornecedores obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "update tb_fornecedores set nome=?,cnpj=?,celular=?,cidade=? where id=?";
            // Conectar o banco
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCNPJ());
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

    public void excluirFuncionario(Fornecedores obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "delete from tb_fornecedores where id=?";

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

    public List<Fornecedores> listarFornecedores() {
        try {
            List<Fornecedores> lista = new ArrayList<>();

            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCNPJ(rs.getString("cnpj"));
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

    public List<Fornecedores> buscaFornecedornome(String nome) {
        try {
            List<Fornecedores> lista = new ArrayList<>();

            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCNPJ(rs.getString("cnpj"));
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

    public Fornecedores consultarNome(String nome) {
        try {
            String sql = "select * from tb_fornecedores where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCNPJ(rs.getString("cnpj"));
                obj.setCelular(rs.getString("celular"));
                obj.setCidade(rs.getString("cidade"));
            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }

}
