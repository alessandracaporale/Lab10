/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.*;
import it.polito.tdp.rivers.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleEndDate(ActionEvent event) {

    }

    @FXML
    void handleFMed(ActionEvent event) {

    }

    @FXML
    void handleK(ActionEvent event) {

    }

    @FXML
    void handleNumMeasurements(ActionEvent event) {

    }

    @FXML
    void handleRiver(ActionEvent event) {
    	this.txtResult.setText("");
    	River river = this.boxRiver.getValue();
    	if (river == null) {
    		this.txtStartDate.setText("");
    		this.txtEndDate.setText("");
    		this.txtNumMeasurements.setText("");
    		this.txtFMed.setText("");
    		this.txtResult.setText("Selezionare un fiume!");
    	}
    	String startDate = this.model.getStartDate(river).toString();
    	this.txtStartDate.setText(startDate);
    	String endDate = this.model.getEndDate(river).toString();
    	this.txtEndDate.setText(endDate);
    	String numMeasurements = String.valueOf(this.model.getNumMeasurements(river));
    	this.txtNumMeasurements.setText(numMeasurements);
    	String fMed = String.valueOf(this.model.getFMed(river));
    	this.txtFMed.setText(fMed);
    }

    @FXML
    void handleStartDate(ActionEvent event) {

    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.setText("");
    	River river = this.boxRiver.getValue();
    	List<Flow> flows = this.model.getFlowsByRiver(river);
    	double k=0;
    	try {
			k = Double.parseDouble(this.txtK.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	double fMed = this.model.getFMed(river);
    	if (k<=0) {
    		this.txtResult.setText("Inserire un valore di k positivo!");
    		return;
    	}
    	double Q = k * fMed * 30;
    	double fOutMin = 0.8 * fMed;
    	double c = Q /2;
    	int numGiorni = 0;
    	
    	System.out.println("Q = "+Q
    			+"\n" + "c0 = "+c);
    	
    	
    	List<Double> lista = new ArrayList<>();
    	lista.add(c);
    	
    	for (Flow f : flows) {
    		double fIn = f.getFlow();
    		c = c + fIn;
    		double fOut=0;
    		
    		if (c > Q) {
    			c = Q;
    			fOut = Q - c;
    		}
    		else {
    			double i = Math.random()*100;
	    		if (i<=5) {
	    			fOut = fOut + 10*fOutMin;
	    		}
    		}
    		
    		
    		if(fIn > fOut) {
    			c = c - fOut;
    		}
    		else {
    			fOut = c;
    			c=0;	
    		}
    		
    		lista.add(c);
    		
    		if (fOut < fOutMin) {
    			numGiorni++;
    		}
    		System.out.println(c + " - fOut=" +fOut + " - fIn=" +fIn);
    	}
    	
    	double cMed = 0;
    	for (Double d : lista) {
    		cMed = cMed + d;
    	}
    	cMed = cMed / lista.size();
    	
    	this.txtResult.setText("Il numero di giorni in cui non si è potuta garantire "
    			+ "l'erogazione minima è: \n" + numGiorni
    			+ "\n"
    			+ "L'occupazione media Cmed è: \n" + cMed);
    }
    
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
   
        
    
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	ObservableList<River> data = FXCollections.observableArrayList();
        for (River r : this.model.getAllRivers()) {
        	data.add(r);
        }
        this.boxRiver.setItems(data);
    }
    
    
}
