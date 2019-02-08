package application;

import view.ComputationPage;

public class CompApp {

	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComputationPage().setVisible(true);
            }
        });
        
	}

}
