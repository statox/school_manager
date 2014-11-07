package scolarite;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Note implements java.io.Serializable {

	private static final long serialVersionUID = -684740689150819642L;
	private Double TP, DE, CE, moyenne;

	public Note(Double tP, Double dE, Double cE) {
		super();
		TP = tP;
		DE = dE;
		CE = cE;
		
		moyenne = 0.5*DE + 0.3*CE + 0.2*TP;
	}
	public Note(){
		super();
		TP = null;
		DE = null;
		CE = null;
		moyenne = null;
	}

	public Double getTP() {
		return TP;
	}

	public void setTP(Double tP) {
		TP = tP;
	}

	public Double getDE() {
		return DE;
	}

	public void setDE(Double dE) {
		DE = dE;
	}

	public Double getCE() {
		return CE;
	}

	public void setCE(Double cE) {
		CE = cE;
	}
	
	public Double getMoyenne(){
		return moyenne;
	}
	
	public void setMoyenne(){
		moyenne = 0.5*DE + 0.3*CE + 0.2*TP;
	}

	@Override
	public String toString() {
		
		String moyenneString = String.format("%.2f", moyenne);
		return "moyenne=" + moyenneString + "\n\tTP=" + TP + ",\tDE=" + DE + ",\tCE=" + CE;
	}

	
	
	

}
