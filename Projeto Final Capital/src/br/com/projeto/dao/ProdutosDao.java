/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Produtos;
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
public class ProdutosDao {

    private Connection con;

    public ProdutosDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarProduto(Produtos obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "insert into tb_produtos(descricao,preco,qtd_estoque,for_id)" + "values(?,?,?,?)";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public void alterarProduto(Produtos obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";
            // Conectar o banco
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public void excluirProduto(Produtos obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "delete from tb_produtos where id=?";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluido com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public List<Produtos> listarProdutos() {
        try {
            List<Produtos> lista = new ArrayList<>();

            String sql = "select p.id, p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getFloat("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedores(f);

                lista.add(obj);
            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public List<Produtos> listarpornome(String nome) {
        try {
            List<Produtos> lista = new ArrayList<>();

            String sql = "select p.id, p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getFloat("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedores(f);

                lista.add(obj);
            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public Produtos consultaporId(int id) {
        try {

            String sql = "select * from tb_produtos where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getFloat("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }

    public Produtos consultaNome(String nome) {
        try {
            List<Produtos> lista = new ArrayList<>();

            String sql = "select p.id, p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getFloat("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedores(f);
            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }

}
