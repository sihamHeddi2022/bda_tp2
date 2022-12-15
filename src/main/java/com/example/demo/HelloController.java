package com.example.demo;

import db.Database;
import db.Pays;
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

    @FXML
    private Connection con = null;
    private Statement statement=null;
    private ResultSet resultSet=null;



    @FXML
    private Label nbP;
    @FXML
    private Label langue;
    @FXML
    private Label moy;


          /*
            ==========================================================
                                 TableView country
           ===========================================================
       */

        @FXML
        private TableView<Pays> country;

        @FXML
        private TableColumn<Pays,String> cona;

       @FXML
       private TableColumn<Pays,String> can;

       @FXML
       private TableColumn<Pays,String> l;







      /*
            ==========================================================
                                 TableView villeA
           ===========================================================
       */

    @FXML
    private TableView<Ville> villeA;


    @FXML
    private TableColumn<Ville,Integer> ai;
    @FXML
    private TableColumn<Ville,String> an;

    @FXML
    private TableColumn<Ville,String> ccn;
    @FXML
    private TableColumn<Ville,String> ad;
    @FXML
    private TableColumn<Ville,Integer> ap;







      /*
            ==========================================================
                                 TableView moineVille
           ===========================================================
       */



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

    // ============================================================


    @FXML
    private ListView exp50;
    @FXML
    private ListView pCg;
    @FXML
    private ListView villeAsia;


    private void  getNameCityNotCapitalAsia(){

        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("select Name from world.city where CountryCode in (SELECT code FROM world.country where Continent = \"Asia\") and id not in (SELECT Capital FROM world.country where Continent = \"Asia\");");
            while (this.resultSet.next()){
                villeAsia.getItems().add(this.resultSet.getString("Name"));
            }

        }catch (Exception exception){
            System.out.println(exception);
        }


    }



    private void nomPaysDuGranCont(){
        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT Name FROM world.country where continent = 'Africa'");
            while (this.resultSet.next()){
                pCg.getItems().add(this.resultSet.getString("Name"));
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    private void toutPays(){

        cona.setCellValueFactory(new PropertyValueFactory<Pays,String>("cona"));
        can.setCellValueFactory(new PropertyValueFactory<Pays,String>("can"));
        l.setCellValueFactory(new PropertyValueFactory<Pays,String>("l"));

        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT country.Name as cn ,city.Name as capital,Language FROM world.country,world.city,world.countrylanguage where country.Capital=city.ID and countrylanguage.CountryCode=country.Code;");
            while (this.resultSet.next()){
                country.getItems().add(new Pays(this.resultSet.getString("cn"),this.resultSet.getString("capital"),this.resultSet.getString("Language")));

            }

        }catch (Exception exception){
            System.out.println(exception);
        }
    }



    private void villesPaysAlg(){

        ai.setCellValueFactory(new PropertyValueFactory<Ville,Integer>("ID"));
        an.setCellValueFactory(new PropertyValueFactory<Ville,String>("Name"));
        ccn.setCellValueFactory(new PropertyValueFactory<Ville,String>("CountryCode"));
        ad.setCellValueFactory(new PropertyValueFactory<Ville,String>("District"));
        ap.setCellValueFactory(new PropertyValueFactory<Ville,Integer>("Population"));

        try {
            this.statement = this.con.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM world.city where CountryCode in (select Code from world.country where Name = \"Algeria\");");
            while (this.resultSet.next()){
                villeA.getItems().add(new Ville(this.resultSet.getInt("ID"),this.resultSet.getString("Name"),this.resultSet.getString("CountryCode"),this.resultSet.getString("District"),this.resultSet.getInt("Population")));

            }

        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    private void  getVilleMoineAlgeria(){
        idV.setCellValueFactory(new PropertyValueFactory<Ville,Integer>("ID"));
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
        this.con = Database.getConnection();
             /*
                    ====================================================================================
                                               Le nombre du pays
                    ====================================================================================
         */

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

             /*
                    ====================================================================================
                                                  Toutes les villes du pays “ Algeria “
                    ====================================================================================
            */

              villesPaysAlg();

               /*
                    ====================================================================================
                                   Les noms du pays , leurs noms de capitales, et leurs langues
                    ====================================================================================
            */


                   toutPays();

                   /*
                                    ======================================================================
                                       Les noms du pays du continent qui a le plus grand nombre du pays
                                    ======================================================================
                  */



                      nomPaysDuGranCont();


                    /*
                                    ======================================================================
                                               Les noms des villes qui ne sont pas des capitales et qui
                                                       appartiennent  au continent asiatique
                                    ======================================================================
                     */

                      getNameCityNotCapitalAsia();


    }



}