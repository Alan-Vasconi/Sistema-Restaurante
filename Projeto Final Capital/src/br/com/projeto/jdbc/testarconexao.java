/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author alans
 */
public class testarconexao {

    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + erro);
        }
    }
}
