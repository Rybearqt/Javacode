package homeloan;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class CLienttask {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextPane textPane_1; 
	Socket jsocket ;
	String clientname;
	String propertyaddress;
	String offeredprice = "";
	String marketvalue ="";
	String from_server;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CLienttask window = new CLienttask();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CLienttask() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
	
	
		
		/////////////////new MyDnDDemo();/////////////////////////
		//public static void main(String args[]{
		
		
		class DnDcode extends JFrame implements DropTargetListener {
			  /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
			DropTarget dt;
			  JTextArea mytext = new JTextArea();
			  public DnDcode() {
			    super("DnD GUI");
			    setSize(650, 480);
			    getContentPane().add(new JLabel("Please drag a file and drop it here:"), BorderLayout.NORTH); // or JtextAarea
			    
			    
			    getContentPane().add(mytext, BorderLayout.CENTER);
			    dt = new DropTarget(mytext, this);
			    // set the textfield as the drop target
			    setVisible(true);
			  }

			  public void dragEnter(DropTargetDragEvent dtde) {
			    // Drag Enter
			  }

			  public void dragExit(DropTargetEvent dte) {
			   //Drag Exit
			  }

			  public void dragOver(DropTargetDragEvent dtde) {
			    //Drag Over
			  }

			  public void dropActionChanged(DropTargetDragEvent dtde) {
			    // When Drop Action is changed;
			  }
			 
			  
			  public void drop(DropTargetDropEvent dtde) {
			    try {
			      Transferable tr = dtde.getTransferable();
			      DataFlavor[] flavors = tr.getTransferDataFlavors();
			      // put data flavors in a array, consider the following data flavors:
			      
			      for (int i = 0; i < flavors.length; i++) {
			    	  
			        /*if (flavors[i].isFlavorJavaFileListType()) {
			          dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			          List list = (List) tr.getTransferData(flavors[i]);
			          for (int j = 0; j < list.size(); j++) {
			        	  mytext.setText("You dropped a file:"+"\n\n");
			        	  mytext.append(((Object) list).get(j) + "\n");
			        	  
			          }
			          dtde.dropComplete(true);
			          return;
			          
			        } else */
			    	  
			        if (flavors[i].isFlavorTextType()) {
			            dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			            mytext.setText("You dropped a text:"+"\n\n");
			            String mystring = (String)tr.getTransferData(flavors[i]);
			            mytext.append(mystring+"\n\n");
			            dtde.dropComplete(true);
			            return;
			          }

			         else if (flavors[i].isFlavorSerializedObjectType()) {
			          dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			          Object obj = tr.getTransferData(flavors[i]);
			          mytext.setText("You dropped an Object"+"\n\n");
			          mytext.append("Object: " + obj);
			          dtde.dropComplete(true);
			          return;
			          
			        } else if (flavors[i].isRepresentationClassInputStream()) {
			          dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			          mytext.read(new InputStreamReader((InputStream) tr.getTransferData(flavors[i])),
			              "You dropped something from the clipboard"+"\n\n");
			          dtde.dropComplete(true);
			          return;
			        }
			      }
			      dtde.rejectDrop();
			    } catch (Exception ex) {
			      ex.printStackTrace();
			      
			      dtde.rejectDrop();
			    }
			  }
		 
		}
		
		/////////////////////////////////////////////////////////
		JButton btnNewButton = new JButton("Credit check");
		btnNewButton.setActionCommand("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(12, 183, 142, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("House Inspect");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				////////////////////////////////////////////

				new DnDcode();
				////////////////////////////////////////////
				
				
			}
		});
		btnNewButton_1.setBounds(12, 297, 142, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton("Apprisal");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				////////////////////////////////////////////////
				clientname = textField.getText();
				propertyaddress = textField_1.getText();
				offeredprice = textField_2.getText();
				marketvalue = textField_3.getText();
				
				System.out.println("data entered");
				
				
				
				try {
					jsocket = new Socket("127.0.0.1",9994);
					
					PrintWriter writer = new PrintWriter(jsocket.getOutputStream(),true);
							
					System.out.println("Sending a message sent to the server");
					
					writer.println("this a message sent to the server");
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(jsocket.getInputStream()));
					String from_server = reader.readLine();
					
					System.out.println("Recieved a message from the server:"+from_server);
					
		
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				
				
				
				/////////////////////////////////////////////////
			}
		});
		
		btnNewButton_2.setBounds(210, 297, 154, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Approve");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////////
				
			String clientname = null;
			textPane_1.setText("Approval has been confirmed for client:"+clientname);
				
				
				///////////////////////////
			}
		});
		btnNewButton_3.setBounds(12, 463, 117, 25);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Decline");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////
				textPane_1.setText("Approval has been declined for client");
				
				//////////////////////////
			}
		});
		btnNewButton_4.setBounds(197, 463, 117, 25);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Help");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop d = Desktop.getDesktop();
				try {
					URI URL = new URI("https://docs.google.com/document/d/e/2PACX-1vToG3Dbb01RpIBHNZH6H6omw2D5YC7dhzDM6zreGldisG1FyNR-t8ERJcIRebR2kX74PvRu3R6vcqnP/pub");
				d.browse(URL);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_5.setBounds(361, 463, 117, 25);
		frame.getContentPane().add(btnNewButton_5);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(12, 336, 546, 115);
		frame.getContentPane().add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(12, 497, 546, 52);
		frame.getContentPane().add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(12, 220, 546, 58);
		frame.getContentPane().add(textPane_2);
		
		textField = new JTextField();
		textField.setBounds(135, 120, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(135, 58, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Clientname");
		lblNewLabel.setBounds(0, 59, 129, 17);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOfferedPrice = new JLabel("Propertyaddress");
		lblOfferedPrice.setBounds(12, 120, 93, 19);
		frame.getContentPane().add(lblOfferedPrice);
		
		JLabel lblHomeloanApplicationDecision = new JLabel("Homeloan Application Decision");
		lblHomeloanApplicationDecision.setBounds(101, 12, 228, 25);
		frame.getContentPane().add(lblHomeloanApplicationDecision);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(412, 58, 114, 19);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(412, 120, 114, 19);
		frame.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_1 = new JLabel("Applicant name");
		lblNewLabel_1.setBounds(278, 60, 129, 17);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblOfferedPrice_1 = new JLabel("Offered price");
		lblOfferedPrice_1.setBounds(301, 122, 93, 19);
		frame.getContentPane().add(lblOfferedPrice_1);
	}
}
