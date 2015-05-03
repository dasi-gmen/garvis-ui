package garvisUserChat;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Element;

public class GarvisUserChat extends JFrame {
	
	
	String userName = "pacopa";
	Integer numLineas = 0;
	JTextArea areaLenguajeP =new JTextArea("");	
	JLabel lineaColumnaAreaCodigo = new JLabel("Posición del puntero :  (0,0)");
	JLabel labelToken = new JLabel("G-ARVIS, EL SISTEMA INTELIGENTE PARA TU CASA");	
	JTextArea areaIn;
	private static final long serialVersionUID = 1L;

	public GarvisUserChat() {
		inicializarInterfaz();  
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}
		});

	}

	public void inicializarInterfaz() {
		//this.setJMenuBar(getMenuPrincipal());
		this.setContentPane(getPanelPrincipal());
		this.setTitle("PROYECTO DE DASI, Grupo G");
	}
	
	private JPanel getPanelPrincipal() {
		JPanel panelPrincipal = new JPanel();
		BorderLayout b = new BorderLayout();
		panelPrincipal.setLayout(b);
		
			JPanel panelSuperior = new JPanel(new GridLayout(1,3));			
			panelSuperior.add(construyeAreaLenguajeP());
			panelPrincipal.add("North",labelToken);
			panelPrincipal.add("Center",panelSuperior);
	
		panelPrincipal.add("South",construyeAreaInferior());
		return panelPrincipal;
	}
	
	
	public JPanel construyeAreaLenguajeP(){
		
		JPanel lenguajePPanel = new JPanel();
		
		lenguajePPanel.setBorder(new TitledBorder("Chat:"));
		BorderLayout bl = new BorderLayout();
		lenguajePPanel.setLayout(bl);		
		
		areaLenguajeP = new JTextArea();//"Aquí se muestran el historial del chat");		
		areaLenguajeP.setEditable(false);//setEnabled(false);//  enable(true);
		areaLenguajeP.setFont(new Font("Arial", Font.BOLD,15));
		JScrollPane scroll = new JScrollPane( areaLenguajeP );
		
		lenguajePPanel.add("Center", scroll);
		
		
		return lenguajePPanel;
	}
	
	
	public JPanel construyeAreaInferior(){
		JPanel areaInferior = new JPanel();		
		BorderLayout bl = new BorderLayout();
		areaInferior.setLayout(bl);		
		
		JPanel panelBotones = new JPanel();
		//panelBotones.setSize(5, 5);
		
		
		final JButton enviarButton = new JButton("Envia2r");			
			panelBotones.add(enviarButton);						
		areaInferior.add("Center", construyeAreaIn());
		areaInferior.add("East", construyeBoton());
		
		return  areaInferior;
//		return null;
	}

	
	
	public void oyenteEjecutar(final JButton ejecutarButton){
		ejecutarButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
			if(ejecutarButton==e.getSource()){			
				
				System.out.print("Hola");
				//sintactico.comenzarAnalisisSintactico();
				
					//buscarEntradas();	
							
			}
			
		}});
	}
	
	
	public JPanel construyeAreaIn(){
		JPanel outPPanel = new JPanel();
		//outPPanel.setBorder(new TitledBorder("Entrada:"));
		
		BorderLayout bl = new BorderLayout();
		outPPanel.setBorder(new TitledBorder("Entrada:"));
		outPPanel.setLayout(bl);		
		areaIn = new JTextArea();//("Aquí se escriben las entradas",2,5);
		
		areaIn.setFont(new Font("Calibri", Font.PLAIN, 15));
		
		areaIn.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar()=='\n'){
					if(areaIn.getText().length()!=0){
						String userInput = areaIn.getText().substring(0,(areaIn.getText().length())-1);
						areaLenguajeP.append(userName+":  "+userInput+"\n");
						areaIn.setText("");
						numLineas++;
					}
				}
			}
			
			public void keyReleased(KeyEvent arg0) {}
			
			public void keyPressed(KeyEvent arg0) {

			}
		});
		
		JScrollPane scroll = new JScrollPane( areaIn );
				
		outPPanel.add("Center", scroll);
		
		return outPPanel;
		
	}	

	public JPanel construyeBoton(){
		
		JPanel outPPanel = new JPanel();
		outPPanel.setBorder(new TitledBorder(" "));
		
		BorderLayout bl = new BorderLayout();
		outPPanel.setLayout(bl);		
	
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
			if(areaIn.getText().length()!=0){
				String userInput = areaIn.getText();
				areaLenguajeP.append("\n"+userName+":  "+userInput);
				areaIn.setText(null);
				numLineas++;
			}
		}});
		
		outPPanel.add("Center", btnEnviar);
		
		return outPPanel;
	}	

		
	/*void leerLineasTextArea(){
		String text = areaCodigo.getText() ;
		StringTokenizer st = new StringTokenizer(text,"\n") ;
		while (st.hasMoreTokens()) {
		     String linea = st.nextToken();		
		     String palabra[] = linea.split(" ");
		     
		     for(int i=0; i<palabra.length;i++){
		    	 labelToken.setText("Token: "+palabra[i]);
		    	 
		     }
		}
	}*/
	
		
	JMenuItem getSalirItem() {
		JMenuItem salirItem = new JMenuItem();
		salirItem.setText("Salir");
		salirItem.setMnemonic('S');
		salirItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.CTRL_MASK));		

		salirItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){								
			if(JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(getParent()),
					"¿Está seguro que quiere cerrar?","Mensaje",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){					 
				System.exit(1);
				}
			}});	

		return salirItem;		
	}

	
	// Clase para el contador linea/columna
	/*class CaretList implements CaretListener {
        
        public CaretList(JTextArea textoEntrada)
        {
        	areaCodigo = textoEntrada;
        }
       
        public void caretUpdate(CaretEvent arg0) {
            int caretPosition = areaCodigo.getCaretPosition();
            Element root = areaCodigo.getDocument().getDefaultRootElement();//areaCodigo.getDocument().getDefaultRootElement();
            
            int linea = root.getElementIndex(caretPosition);      
            int lineStart = root.getElement(linea).getStartOffset();
            int columna = (caretPosition - lineStart);            
            lineaColumnaAreaCodigo.setText("Posición del puntero :  ("+Integer.toString(linea)+","+String.valueOf(columna)+")");       
           
        }       
    }*/
	
	
	
	
	public static void main(String[] args) {
		
		GarvisUserChat interfaz = new GarvisUserChat();
		
		interfaz.setVisible(true);
		interfaz.setEnabled(true);
		interfaz.setSize(1200, 800);
		interfaz.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		
	}
	
	
	public void messageIn(String messageGarvis){
		areaLenguajeP.append("\n"+"Garvis:  "+messageGarvis);
		numLineas++;
	}

}
