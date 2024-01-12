/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Pratos;
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
public class PratosDao {

    private Connection con;

    public PratosDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarPrato(Pratos obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "insert into tb_pratos(nome,preco)" + "values(?,?)";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setFloat(2, obj.getPreco());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Prato cadastrado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public void alterarPrato(Pratos obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "update tb_pratos set nome=?,preco=? where id=?";
            // Conectar o banco
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setFloat(2, obj.getPreco());
            stmt.setInt(3, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Prato alterado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public void excluirPrato(Pratos obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "delete from tb_pratos where id=?";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Prato excluido com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public List<Pratos> listarProdutos() {
        try {
            List<Pratos> lista = new ArrayList<>();

            String sql = "select p.id, p.nome,p.preco from tb_produtos as p";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pratos obj = new Pratos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setNome(rs.getString("p.nome"));
                obj.setPreco(rs.getFloat("p.preco"));


                lista.add(obj);
            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public List<Pratos> listarpornome(String nome) {
        try {
            List<Pratos> lista = new ArrayList<>();

            String sql = "select p.id, p.nome ,p.preco as p where p.nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pratos obj = new Pratos();

                obj.setId(rs.getInt("p.id"));
                obj.setNome(rs.getString("p.nome"));
                obj.setPreco(rs.getFloat("p.preco"));
                
                lista.add(obj);
            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public Pratos consultaporId(int id) {
        try {

            String sql = "select * from tb_pratos where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Pratos obj = new Pratos();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("p.nome"));
                obj.setPreco(rs.getFloat("p.preco"));

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }

    public Pratos consultaNome(String nome) {
        try {
            List<Pratos> lista = new ArrayList<>();

            String sql = "select p.id, p.nome,p.preco from tb_produtos as p";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Pratos obj = new Pratos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("p.nome"));
                obj.setPreco(rs.getFloat("p.preco"));

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }

}
