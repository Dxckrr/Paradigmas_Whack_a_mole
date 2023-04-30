module Whack_A_Mole_1 {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	requires java.xml;
	
	opens application to javafx.graphics, javafx.fxml;
}
