/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionarios;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
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
public class FuncionariosDao {

    private Connection con;

    public FuncionariosDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarFuncionario(Funcionarios obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "insert into tb_funcionarios(nome,cpf,senha,cargo,nivel_acesso,celular,cidade)" + "values(?,?,?,?,?,?,?)";

            // Conectar o banco
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getSenha());
            stmt.setString(4, obj.getCargo());
            stmt.setString(5, obj.getNivel_acesso());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCidade());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public void alterarFuncionario(Funcionarios obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "update tb_funcionarios set nome=?,cpf=?,senha=?,cargo=?,nivel_acesso=?,celular=?,cidade=? where id=?";
            // Conectar o banco
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getSenha());
            stmt.setString(4, obj.getCargo());
            stmt.setString(5, obj.getNivel_acesso());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCidade());
            stmt.setInt(8, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro de SQL: " + error);
        }
    }

    public void excluirFuncionario(Funcionarios obj) {
        try {
            // Criar o comando que vai pro sql
            String sql = "delete from tb_funcionarios where id=?";

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

    public List<Funcionarios> listarFuncionarios() {
        try {
            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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

    public List<Funcionarios> buscaFuncionarionome(String nome) {
        try {
            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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

    public Funcionarios consultarNome(String nome) {
        try {
            String sql = "select * from tb_funcionarios where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setCelular(rs.getString("celular"));
                obj.setCidade(rs.getString("cidade"));
            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }
    // Login

    public void logar(String nome, String senha) {

        try {
            String sql = "select * from tb_funcionarios where nome = ? and senha = ?";
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Logado com Sucesso
                JOptionPane.showMessageDialog(null, "Seja Bem vindo ao Sistema Capital!");
                FrmMenu tela = new FrmMenu();
                tela.usuariologado = rs.getString("nome");
                tela.setVisible(true);

            } else {
                // Usuário não encontrado
                JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos!");
                new FrmLogin().setVisible(true);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + erro);
        }
    }

    public static void main(String args[]) {
        // TODO code application logic here
    }
}
