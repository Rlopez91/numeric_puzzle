package puzzle;

import javax.swing.*;
import javax.swing.*;

public class Puzzle_main {

	private JFrame ventana;
	private JButton btn_piezas[];
	JLabel lbl_titulo1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Puzzle_main();

	}
	
	Puzzle_main(){
		
		ventana = new JFrame("ROMPECABEZAS");
		ventana.setBounds(20,20,1000,900);
		ventana.setLayout(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbl_titulo1 = new JLabel("<html>UNIVERSIDAD TECNOLOGICA DE PANANA<br>FACULTAD DE INGENIERIA EN SISTEMAS COMPUTACIONALES<br><br>LIC. DESARROLLO DE SOFTWARE<br><br>DESARROLLO DE SFOTWARE III<br><br>PROFESOR<br>RICARDO CHAN<br><br>ESTUDIANTE<br>RICARDO LOPEZ<br>N-21-267<br><br>GRUPO 1LS221<br><br>28 de junio 2020</html>");
		lbl_titulo1.setBounds(50,50,350,280);
		ventana.add(lbl_titulo1);
		
		//creacion de botones
		btn_piezas = new JButton[16];
		for(int i=0; i<16; i++) {
			btn_piezas[i] = new JButton(String.valueOf(i+1));
			btn_piezas[i].setBounds(600+80*(i%4),150+80*(i/4),80,80);
			ventana.add(btn_piezas[i]);
		}
		btn_piezas[15].setVisible(false); //ultimo boton invisible para controlar las posiciones
		
		
		ventana.setVisible(true);

	}

}
