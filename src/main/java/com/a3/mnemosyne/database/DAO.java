package com.a3.mnemosyne.database;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

    Connection con = DBConnection.getConnection();

    public void createBook(String nome, double preco, String desc, String autor,String pag,String tipo){
        try{
            PreparedStatement checkDuplicates = con.prepareStatement("SELECT * FROM tb_livros where nome = ? and autor = ? and tipo = ?");
            checkDuplicates.setString(1, nome);
            checkDuplicates.setString(2, autor);
            checkDuplicates.setString(3, tipo);
            ResultSet rs = checkDuplicates.executeQuery();
            if(!rs.next()){
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tb_livro(tb_livro_nome, tb_livro_preco, tb_livro_desc, tb_livro_autor, tb_livro_pag, tb_livro_tipo) VALUES(?,?,?,?,?,?)");
                preparedStatement.setString(1, nome);
                preparedStatement.setDouble(2, preco);
                preparedStatement.setString(3, desc);
                preparedStatement.setString(4, autor);
                preparedStatement.setString(5, pag);
                preparedStatement.setString(6, tipo);
                preparedStatement.execute();
            }else{
                //TODO
                //Exibir no front end mensagem falando que um livro com esses parametros ja esta cadastrado
            }
            con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void getBooks(){

    }
    public void deleteBook(){

    }
    public void modifyBook(){

    }
    public void deleteClient(){

    }
    public void modifyClient(){

    }
    public void createClient(String nome,
                             String email,
                             String senha,
                             String cpf){
        String senhaHash = DigestUtils.sha256Hex(senha);
        try {

            PreparedStatement pstCheckDuplicateQuery = con.prepareStatement("select * from tb_cliente where tb_cliente_cpf = ?");
            pstCheckDuplicateQuery.setString(1, cpf);
            ResultSet rs = pstCheckDuplicateQuery.executeQuery();
            if(!rs.next()){
                PreparedStatement pst1 = con.prepareStatement("INSERT INTO tb_carrinho values()");
                if(!pst1.execute()){
                    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tb_cliente(tb_cliente_nome, tb_cliente_email, tb_cliente_senha, tb_cliente_cpf, tb_carrinho_idtb_carrinho) VALUES(?, ?, ?, ?, (SELECT idtb_carirnho FROM tb_carrinho ORDER BY idtb_carirnho DESC LIMIT 1))");
                    preparedStatement.setString(1, nome);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, senhaHash);
                    preparedStatement.setString(4, cpf);
                    boolean resultado = preparedStatement.execute();
                }
            }else{
                //TODO
                //Criar aviso de CPF ja cadastrado
                System.out.println("CPF ja cadastrado");
            }
            con.close() ;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
