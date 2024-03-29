package com.ceica.restcountriesfx;

import com.ceica.restcountriesfx.Models.CountryDTO;
import com.ceica.restcountriesfx.Services.FakeRestCountriesService;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RestCountriesController {
    @FXML
    protected ComboBox comboRegions;
    @FXML
    protected TableView<CountryDTO> tblCountries;
    @FXML
    protected TableColumn <CountryDTO,String> countryNameColumn;
    @FXML
    protected ImageView imgFlag;
    @FXML
    protected TextField txtCountryName;
    @FXML
    protected TextField txtCountryPopulation;
    @FXML
    protected TextField txtCountryCapital;
    @FXML
    protected TextField txtCountryCoin;
    private   ObservableList<CountryDTO> observableList= FXCollections.observableArrayList();


    public void initialize() {
        FakeRestCountriesService fakeRestCountriesService = new FakeRestCountriesService();
        comboRegions.getItems().addAll(fakeRestCountriesService.getRegions());
        comboRegions.setOnAction(e -> {
            if (comboRegions.getSelectionModel().getSelectedItem() != null) {
                String region = comboRegions.getSelectionModel().getSelectedItem().toString();
                observableList.clear();
                observableList.addAll(fakeRestCountriesService.getCountriesByRegion(region));
                tblCountries.setItems(observableList);
            }
        });
        tblCountries.setOnMouseClicked(e->{
            String countryName=tblCountries.getSelectionModel().getSelectedItem().getName();
            CountryDTO countryDTO= fakeRestCountriesService.getCountryByName(countryName);
            txtCountryName.setText(countryDTO.getName());
            txtCountryCapital.setText(countryDTO.getCapital());
            txtCountryCoin.setText(countryDTO.getCoin());
            txtCountryPopulation.setText(String.valueOf(countryDTO.getPopulation()));
            Image image= new Image(countryDTO.getFlag());
            imgFlag.setImage(image);
        });
            countryNameColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getName()));
        }
        @FXML
        public void btnClear (ActionEvent actionEvent){
            observableList.clear();
            tblCountries.refresh();
            comboRegions.getSelectionModel().clearSelection();
            txtCountryName.setText("");
            txtCountryCapital.setText("");
            txtCountryCoin.setText("");
            txtCountryPopulation.setText("");
            imgFlag.setImage(null);


        }
    }