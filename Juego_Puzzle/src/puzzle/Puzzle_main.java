package puzzle;

import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;


public class Puzzle_main implements ActionListener {

	private JFrame ventana;
	private JButton btn_piezas[], btn_temp[];
	private JButton btn_inicio, btn_reinicio, btn_inicio2, btn_score;
	private JLabel lbl_titulo1, lbl_seg, lbl_min, lbl_hora, lbl_jugador, lbl_movimientos, lbl_instruccion;
	private JTextField tf_jugador;
	private int dir_x, dir_y, blanco_x, blanco_y, x, y, btn_temp2, cont=0, mov=0, aMovimientos[] = new int [5];
	private String aNombres[] = new String[5], aSeg[] = new String[5], aMin[] = new String [5], aHora[] = new String [5];
	
	DefaultListModel<String> listModel;
	JList<String> score_board;
	
	Timer timer = new Timer(1000, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			int seg, min, hora;
			
			seg = Integer.parseInt(lbl_seg.getText());
			min = Integer.parseInt(lbl_min.getText());
			hora = Integer.parseInt(lbl_hora.getText());
			
			if(seg<59) {
				lbl_seg.setText(String.valueOf(seg+1));
			}
			else {
				lbl_seg.setText(String.valueOf(00));
				
				if(min<59) {
					lbl_min.setText(String.valueOf(min+1));
				}
				else {
					lbl_min.setText(String.valueOf(00));
					lbl_hora.setText(String.valueOf(hora+1));
					
				}
			}
		}
	});
		
	Timer movimiento = new Timer (100, new ActionListener() { 
		public void actionPerformed(ActionEvent e) {
	
			x = btn_piezas[btn_temp2].getLocation().x;
			y = btn_piezas[btn_temp2].getLocation().y;
			
			if(x < blanco_x) {
				btn_piezas[15].setLocation(blanco_x-80, blanco_y);
				btn_piezas[btn_temp2].setLocation(x+dir_x, y);
				
				if(x == blanco_x-20) {
					movimiento.stop();
					mov +=1;
					verificar();
				}
			}
			
			else if(y < blanco_y) {
				btn_piezas[15].setLocation(blanco_x, blanco_y-80);
				btn_piezas[btn_temp2].setLocation(x, y+dir_y);
				if(y == blanco_y-20) {
					movimiento.stop();
					mov +=1;
					verificar();
				}
			}
			
			else if(x > blanco_x) {
				btn_piezas[15].setLocation(blanco_x+80, blanco_y);
				btn_piezas[btn_temp2].setLocation(x+dir_x, y);
				if(x == blanco_x+20){ 
					movimiento.stop();
					mov +=1;
					verificar();
				}
			}
			
			else if(y > blanco_y) {
				btn_piezas[15].setLocation(blanco_x, blanco_y+80);
				btn_piezas[btn_temp2].setLocation(x, y+dir_y);
				if(y == blanco_y+20) {
					movimiento.stop();
					mov +=1;
					verificar();
				}
			}
		}
		
	}); 
	
	public static void main(String[] args) {		
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
		
		lbl_seg = new JLabel("00");
		lbl_seg.setBounds(680, 100, 20, 20);
		ventana.add(lbl_seg);
		
		lbl_min = new JLabel("00");
		lbl_min.setBounds(660, 100, 20, 20);
		ventana.add(lbl_min);
		
		lbl_hora = new JLabel("00");
		lbl_hora.setBounds(640, 100, 20, 20);
		ventana.add(lbl_hora);
		
		lbl_jugador = new JLabel("Jugador:");
		lbl_jugador.setBounds(720, 100, 50, 20);
		ventana.add(lbl_jugador);
		
		tf_jugador = new JTextField();
		tf_jugador.setBounds(775, 100, 100, 20);
		ventana.add(tf_jugador);
		
		lbl_movimientos = new JLabel("00");
		lbl_movimientos.setBounds(900, 100, 40, 20);
		ventana.add(lbl_movimientos);
		
		//creacion de botones
		btn_piezas = new JButton[16];
		for(int i=0; i<btn_piezas.length; i++) {
			btn_piezas[i] = new JButton(String.valueOf(i+1));
			btn_piezas[i].setBounds(600+80*(i%4),150+80*(i/4),80,80);
			btn_piezas[i].setActionCommand(String.valueOf(i));
			btn_piezas[i].addActionListener(this);
			ventana.add(btn_piezas[i]);
		}
		btn_piezas[15].setVisible(false); //ultimo boton invisible para controlar las posiciones

		
		
		btn_inicio = new JButton("INICIAR");
		btn_inicio.setBounds(600, 510, 100, 30);
		btn_inicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mov = 0;
				lbl_seg.setText("00");
				lbl_min.setText("00");
				lbl_hora.setText("00");
				timer.start();
				iniciar1();
			}
		});
		ventana.add(btn_inicio);
		
		btn_inicio2 = new JButton("INICIAR 2");
		btn_inicio2.setBounds(700, 510, 100, 30);
		btn_inicio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mov = 0;
				lbl_seg.setText("00");
				lbl_min.setText("00");
				lbl_hora.setText("00");
				iniciar2();
			}
		});
		ventana.add(btn_inicio2);
		
		btn_reinicio = new JButton("REINICIAR");
		btn_reinicio.setBounds(600, 550, 100, 30);
		btn_reinicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mov = 0;
				timer.stop();
				reiniciar();
			}
		});
		ventana.add(btn_reinicio);
		
		btn_score = new JButton("Score Board");
		btn_score.setBounds(325, 360, 150, 30);
		btn_score.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				mostrar_score();
			}
		});
		ventana.add(btn_score);
		
		lbl_instruccion = new JLabel("<html>Presiona reiniciar y luego iniciar <br>para comenzar un nuevo juego</html>");
		lbl_instruccion.setBounds(720, 550, 200, 40);
		ventana.add(lbl_instruccion);
		
		listModel = new DefaultListModel<String>();
		score_board = new JList<String>(listModel);
		score_board.setBounds(250, 400, 300, 200);
		ventana.add(score_board);
		
		for(int i=0; i<5; i++) {
			aSeg[i] = String.valueOf(99);
			aMin[i] = String.valueOf(99);
			aHora[i] = String.valueOf(99);
		}
		
		ventana.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(!movimiento.isRunning()) {
		btn_temp2 = Integer.parseInt(e.getActionCommand());
		int xClick = btn_piezas[btn_temp2].getLocation().x;
		int yClick = btn_piezas[btn_temp2].getLocation().y;
		
		blanco_x = btn_piezas[15].getLocation().x;
		blanco_y = btn_piezas[15].getLocation().y;
		
		if((xClick == blanco_x-80) && (yClick == blanco_y)){
			dir_x = 20;
			dir_y = 0;
			movimiento.start();
		}
		
		if(xClick == blanco_x && yClick == blanco_y-80) {
			dir_x = 0;
			dir_y = 20;
			movimiento.start();
		}
		
		if(xClick == blanco_x+80 && yClick == blanco_y) {
			dir_x = -20;
			dir_y = 0;
			movimiento.start();
		}
		
		if(xClick == blanco_x && yClick == blanco_y+80) {
			dir_x = 0;
			dir_y = -20;
			movimiento.start();
		}

		}
		
	}
	
	public void iniciar1() {
		int j, x, y;
		Random rnd = new Random();
		
		for(int i=0; i<btn_piezas.length-1; i++) { 
			
			x = btn_piezas[i].getLocation().x;
			y = btn_piezas[i].getLocation().y;
			j = rnd.nextInt(15);
				
			btn_piezas[i].setLocation(btn_piezas[j].getLocation().x, btn_piezas[j].getLocation().y);
			btn_piezas[j].setLocation(x, y);	
			
		}
		
		System.out.println("espacio en blanco: "+btn_piezas[15].getLocation().x);
	}
	
	public void iniciar2() {
		int x, y;
		x = btn_piezas[15].getLocation().x;
		y = btn_piezas[15].getLocation().y;
		
		btn_piezas[15].setLocation(btn_piezas[14].getLocation().x, btn_piezas[14].getLocation().y);
		btn_piezas[14].setLocation(x, y);
		timer.start();
	}

	
	public void reiniciar() {
		for(int i=0; i<btn_piezas.length; i++) {
			btn_piezas[i].setText(String.valueOf(i+1));
			btn_piezas[i].setLocation(600+80*(i%4),150+80*(i/4));
			ventana.add(btn_piezas[i]);
		}
		btn_piezas[15].setVisible(false);
		movimiento.stop();
		
	}
	
	public void verificar() {
		
		btn_temp = new JButton[16];
		
		lbl_movimientos.setText(String.valueOf(mov));
		
		for(int i=0; i<btn_temp.length; i++) {
			btn_temp[i] = new JButton(String.valueOf(i+1));
			btn_temp[i].setBounds(600+80*(i%4),150+80*(i/4),80,80);
			
			x = btn_temp[i].getLocation().x;
			y = btn_temp[i].getLocation().y;
			
			if(((btn_piezas[i].getLocation().x) == x) && ((btn_piezas[i].getLocation().y) == y)){
				cont += 1;
			}
			else {
				cont = 0;
			}
		}
		
		if(cont==16) {
			timer.stop();
			JOptionPane.showMessageDialog(null, "Haz completado el rompecabezas!");
			System.out.println("juego terminado");
			guardarScore();
		}
		else {
			cont = 0;
		}
		
		
	}
	
	public void guardarScore() {
		aSeg[4] = lbl_seg.getText();
		aMin[4] = lbl_min.getText();
		aHora[4] = lbl_hora.getText();
		aNombres[4] = tf_jugador.getText();
		aMovimientos[4] = Integer.parseInt(lbl_movimientos.getText());

		
		for(int i=0; i<5-1; i++) {  //se ordena por horas
			for(int j=0; j<5-1; j++) {
				if(Integer.parseInt(aHora[j]) >= Integer.parseInt(aHora[j])) {
					
					int aux = Integer.parseInt(aHora[j+1]);
					aHora[j+1] = aHora[j];
					aHora[j] = String.valueOf(aux);
					
					int aux2 = Integer.parseInt(aMin[j+1]);
					aMin[j+1] = aMin[j];
					aMin[j] = String.valueOf(aux2);
					
					int aux3 = Integer.parseInt(aSeg[j+1]);
					aSeg[j+1] = aSeg[j];
					aSeg[j] = String.valueOf(aux3);
					
					String aux4 = aNombres[j+1];
					aNombres[j+1] = aNombres[j];
					aNombres[j] = aux4;
					
					int aux5 = aMovimientos[j+1];
					aMovimientos[j+1] = aMovimientos[j];
					aMovimientos[j] = aux5;
				}
			}
		}
		
		for(int i=0; i<5-1; i++) { //ordenar por minuto
			for(int j=0; j<5-1; j++) {
				if(Integer.parseInt(aMin[j]) >= Integer.parseInt(aMin[j+1]) && Integer.parseInt(aHora[j]) == Integer.parseInt(aHora[j+1])) {
					int aux = Integer.parseInt(aHora[j+1]);
					aHora[j+1] = aHora[j];
					aHora[j] = String.valueOf(aux);
					
					int aux2 = Integer.parseInt(aMin[j+1]);
					aMin[j+1] = aMin[j];
					aMin[j] = String.valueOf(aux2);
					
					int aux3 = Integer.parseInt(aSeg[j+1]);
					aSeg[j+1] = aSeg[j];
					aSeg[j] = String.valueOf(aux3);
					
					String aux4 = aNombres[j+1];
					aNombres[j+1] = aNombres[j];
					aNombres[j] = aux4;
					
					int aux5 = aMovimientos[j+1];
					aMovimientos[j+1] = aMovimientos[j];
					aMovimientos[j] = aux5;
				}
			}
		}
		
		for(int i=0; i<5-1; i++) { //ordenar por segundo
			for(int j=0; j<5-1; j++) {
				if(Integer.parseInt(aSeg[j]) >= Integer.parseInt(aSeg[j+1]) && Integer.parseInt(aMin[j]) == Integer.parseInt(aMin[j+1])) {
					
					int aux = Integer.parseInt(aHora[j+1]);
					aHora[j+1] = aHora[j];
					aHora[j] = String.valueOf(aux);
					
					int aux2 = Integer.parseInt(aMin[j+1]);
					aMin[j+1] = aMin[j];
					aMin[j] = String.valueOf(aux2);
					
					int aux3 = Integer.parseInt(aSeg[j+1]);
					aSeg[j+1] = aSeg[j];
					aSeg[j] = String.valueOf(aux3);
					
					String aux4 = aNombres[j+1];
					aNombres[j+1] = aNombres[j];
					aNombres[j] = aux4;
					
					int aux5 = aMovimientos[j+1];
					aMovimientos[j+1] = aMovimientos[j];
					aMovimientos[j] = aux5;
				}
			}
		}
		
		FileWriter score;
		try {
			//score = new FileWriter("SCORE.txt", false);//para sobreescribir el archivo
			score = new FileWriter("SCORE.txt", false);
			
			for(int i=0; i<5; i++) {	
					score.write(aNombres[i]+": "+aHora[i]+":"+aMin[i]+":"+aSeg[i]+" movimientos: "+aMovimientos[i]+"\n");
			}
			score.close();
		}catch(Exception e) {
			System.out.println("error al grabar"+e.toString());
		}
	}
	
	public void mostrar_score() {
		try {
			File f = new File("SCORE.txt");
			Scanner sc = new Scanner(f);
			String jugador[] = new String[5];
			for(int i=0; i<5; i++) {
				jugador[i] = sc.nextLine();
				listModel.addElement(jugador[i]+"\n");
			}
		}catch(Exception e) {
			System.out.println("Error de lectura"+e.toString());
		}
	}

}
