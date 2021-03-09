import javax.swing.*;
import java.awt.event.*;

public class DN12 implements ActionListener{
    JFrame okno;
    JTextField op1, ope, op2, rez;
    JLabel enak;
    JButton b;

    DN12(){
        okno = new JFrame("Racunalnik");
        op1 = new JTextField("");
        ope = new JTextField("");
        op2 = new JTextField("");
        enak = new JLabel("=");
        rez = new JTextField("");
        b = new JButton("Izracunaj");
        op1.setBounds(5,5,100,20);
        ope.setBounds(110,5,20,20);
        op2.setBounds(135,5,100,20);
        enak.setBounds(240,5,20,20);
        rez.setBounds(260,5,100,20);
        b.setBounds(5,30,355,20);
        okno.add(op1);
        okno.add(ope);
        okno.add(op2);
        okno.add(enak);
        okno.add(rez);
        okno.add(b);
        okno.setLayout(null);
        okno.setVisible(true);
        okno.setSize(370,85);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setResizable(false);

        b.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            switch (ope.getText()) {
                case "+":
                    rez.setText((Double.parseDouble(op1.getText()) + Double.parseDouble(op2.getText())) + "");
                    break;
                case "-":
                    rez.setText((Double.parseDouble(op1.getText()) - Double.parseDouble(op2.getText())) + "");
                    break;
                case "/":
                    rez.setText((Double.parseDouble(op1.getText()) / Double.parseDouble(op2.getText())) + "");
                    break;
                case "*":
                    rez.setText((Double.parseDouble(op1.getText()) * Double.parseDouble(op2.getText())) + "");
                    break;
                default:
                    rez.setText("Napacni podatki");
                    break;
            }
        }
    }

    public static void main(String[] args){
        new DN12();
    }
}
