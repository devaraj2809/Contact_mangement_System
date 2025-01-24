// 
// Decompiled by Procyon v0.5.36
// 

package contactmanager;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class ContactManager
{
    private JFrame frame;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final ContactManager window = new ContactManager();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ContactManager() {
        this.initialize();
    }
    
    private void initialize() {
        this.frame = new JFrame();
        final JFrame f = new JFrame();
        this.frame.setBounds(100, 100, 450, 300);
        this.frame.setDefaultCloseOperation(3);
        final JPanel panel = new JPanel();
        this.frame.getContentPane().add(panel, "Center");
        final JButton delete1 = new JButton("DELETE CONTACT");
        delete1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFrame frame3 = new JFrame("Deleting Contact");
                frame3.setLayout(new FlowLayout());
                frame3.setVisible(true);
                frame3.setSize(400, 400);
                final JLabel delLabel1 = new JLabel("NAME :");
                frame3.add(delLabel1);
                final JTextField j1 = new JTextField("", 15);
                frame3.add(j1);
            }
        });
        final JButton add1 = new JButton("ADD CONTACT");
        add1.setHorizontalAlignment(2);
        add1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFrame frame2 = new JFrame("Creating Contact");
                frame2.setLayout(new FlowLayout());
                frame2.setVisible(true);
                frame2.setSize(400, 400);
                JLabel addlabel1 = new JLabel("Name:");
                frame2.add(addlabel1);
                final JTextField j1 = new JTextField("", 15);
                frame2.add(j1);
                addlabel1 = new JLabel("Phone Number:");
                frame2.add(addlabel1);
            }
        });
        final JButton Search1 = new JButton("SEARCH");
        Search1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFrame frame4 = new JFrame("Searching Contact");
                frame4.setLayout(new FlowLayout());
                frame4.setVisible(true);
                frame4.setSize(400, 400);
                final JLabel Searchlabel1 = new JLabel("NAME :");
                frame4.add(Searchlabel1);
                final JTextField j1 = new JTextField("", 15);
                frame4.add(j1);
            }
        });
        panel.add(Search1);
        panel.add(add1);
        panel.add(delete1);
    }
}
