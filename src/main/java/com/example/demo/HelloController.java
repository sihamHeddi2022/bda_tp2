package com.example.demo;

import db.Database;
import db.Ville;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController{

    private Connection con = null;
    private Statement statement=null;
    private ResultSet resultSet=null;



    @FXML
    private Label nbP;
    @FXML
    private Label langue;
    @FXML
    private Label moy;

    @FXML
    private TableView<Ville> moineVille;
  @FXML
    private TableColumn<Ville,Integer> idV;
    @FXML
    private TableColumn<Ville,String> nameV;

    @FXML
    private TableColumn<Ville,String> ccV;
    @FXML
    private TableColumn<Ville,String> dV;
    @FXML
    private TableColumn<Ville,Integer> pV;

    @FXML
    private ListView exp50;

    private void  getVilleMoineAlgeria(){
        idV.setCellValueFactory(new PropertyValueFactory<Ville,Integer>("iD"));
        nameV.setCellValueFactory(new PropertyValueFactory<Ville,String>("Name"));
        ccV.setCellValueFactory(new PropertyValueFactory<Ville,String>("CountryCode"));
        dV.setCellValueFactory(new PropertyValueFactory<Ville,String>("District"));
        pV.setCellValueFactory(new PropertyValueFactory<Ville,Integer>("Population"));


        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM world.city where CountryCode in (select Code from world.country where Name = \"Algeria\" ) order by Population asc limit 1;");
            this.resultSet.next();
            System.out.println(this.resultSet.getInt("ID"));
            moineVille.getItems().add(new Ville(this.resultSet.getInt("ID"),this.resultSet.getString("Name"),this.resultSet.getString("CountryCode"),this.resultSet.getString("District"),this.resultSet.getInt("Population")));


        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    private void nomPayExp50(){
        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT Name FROM world.country where LifeExpectancy < 50;");
            while (this.resultSet.next()){
                exp50.getItems().add(this.resultSet.getString("Name"));
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
    }



    private int getCountCountry(){
        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT count(*) as nb FROM world.country");
            this.resultSet.next();
            return this.resultSet.getInt("nb");
        }catch (Exception exception){
            System.out.println(exception);
        }
        return 0;
    }
    private String getLangue(){
        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT count(*) as nbr,Language FROM world.countrylanguage group by Language  order by nbr desc limit 1");
            this.resultSet.next();
            return this.resultSet.getString("Language");
        }catch (Exception exception){
            System.out.println(exception);
        }
        return"";
    }

    private Double getMoy(){
        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("select avg(Population) as moy from world.city where CountryCode in (select code FROM world.country where continent = \"Europe\")");
            this.resultSet.next();
            return this.resultSet.getDouble("moy");
        }catch (Exception exception){
            System.out.println(exception);
        }
        return 0.0;
    }






    @FXML
    public void initialize() {
        this.con= Database.getConnection();
             /*
                    ====================================================================================
                                               Le nombre du pays
                    ====================================================================================
         */

        //System.out.println("bzzz "+Integer.toString(this.getCountCountry()));
        nbP.setText(Integer.toString(this.getCountCountry()));

                  /*
                    ====================================================================================
                                               La langue la plus parlée dans le monde
                    ====================================================================================
                  */
        langue.setText(this.getLangue());
      /*
                    ====================================================================================
                                            La moyenne de la population du continent européen
                    ====================================================================================
                  */
     moy.setText(Double.toString(this.getMoy()));

           /*
                    ====================================================================================
                                            La ville la moine peuplée du pays  << Algeria >>
                    ====================================================================================
           */
       getVilleMoineAlgeria();
             /*
                    ====================================================================================
                                 Les noms du pays ayant une expérience de vie inférieure à 50 ans
                    ====================================================================================
           */
        nomPayExp50();
    }



}