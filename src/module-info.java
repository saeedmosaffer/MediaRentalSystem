module FinalProjectPhase2 {
	requires javafx.graphics; 
	requires javafx.controls;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
