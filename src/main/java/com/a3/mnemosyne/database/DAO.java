package com.a3.mnemosyne.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

    Connection con = DBConnection.getConnection();

    public void createBook(){

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
                    preparedStatement.setString(3, senha);
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
