// 
// Decompiled by Procyon v0.5.36
// 

package contactmanager;

import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Gui
{
    public static void main(final String[] args) {
        final JFrame frame = new JFrame("Contact Manager");
        frame.setDefaultCloseOperation(3);
        frame.setSize(500, 275);
        final JButton btnSave = new JButton("Save");
        final JButton btnCancel = new JButton("Cancel");
        final JTextArea ta = new JTextArea();
        final ContactManagement contactManagement = new ContactManagement();
        contactManagement.creatIndexing();
        final JPanel addContactCaptionPanel = new JPanel();
        final JPanel addContactNamePanel = new JPanel();
        final JPanel addContactMobilePanel = new JPanel();
        final JPanel addContactButtonPanel = new JPanel();
        final JPanel addContactPanel = new JPanel();
        final JLabel contactCaption = new JLabel("Creating New Contact");
        contactCaption.setBounds(100, 30, 400, 30);
        addContactCaptionPanel.add(contactCaption);
        final JLabel contactName = new JLabel("Name");
        final JTextField contactNameValue = new JTextField("", 10);
        addContactNamePanel.setBounds(80, 70, 200, 30);
        addContactNamePanel.add(contactName);
        addContactNamePanel.add(contactNameValue);
        final JLabel contactMobile = new JLabel("Mobile");
        final JTextField contactMobileValue = new JTextField("", 10);
        addContactMobilePanel.setBounds(80, 110, 200, 30);
        addContactMobilePanel.add(contactMobile);
        addContactMobilePanel.add(contactMobileValue);
        final JLabel contactAddress = new JLabel("Address");
        final JTextField contactAddressValue = new JTextField("", 10);
        final JPanel addContactAddressPanel = new JPanel();
        addContactAddressPanel.add(contactAddress);
        addContactAddressPanel.add(contactAddressValue);
        addContactButtonPanel.add(btnSave);
        addContactButtonPanel.add(btnCancel);
        final JMenuBar mb = new JMenuBar();
        final JMenu m1 = new JMenu("FILE");
        final JMenu m2 = new JMenu("About Us");
        mb.add(m1);
        mb.add(m2);
        final JMenuItem m3 = new JMenuItem("Add Contact");
        m1.add(m3);
        m3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.getContentPane().remove(ta);
                addContactPanel.add(addContactCaptionPanel);
                addContactPanel.add(addContactNamePanel);
                addContactPanel.add(addContactMobilePanel);
                addContactPanel.add(addContactAddressPanel);
                addContactPanel.add(addContactButtonPanel);
                addContactPanel.setLayout(new BoxLayout(addContactPanel, 1));
                frame.add(addContactPanel);
                frame.revalidate();
                frame.repaint();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    contactManagement.Write(contactManagement.pack(new Contact(contactNameValue.getText(), Integer.parseInt(contactMobileValue.getText()), contactAddressValue.getText())));
                }
                catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Mobile Number", "InfoBox: Creating Contact ", 0);
                    return;
                }
                contactNameValue.setText(null);
                contactMobileValue.setText(null);
                contactAddressValue.setText(null);
                frame.remove(addContactPanel);
                frame.revalidate();
                frame.repaint();
                JOptionPane.showMessageDialog(null, "Contact Added Phone Records.", "InfoBox: Search ", 1);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                contactNameValue.setText(null);
                contactMobileValue.setText(null);
                contactAddressValue.setText(null);
                frame.remove(addContactPanel);
                frame.revalidate();
                frame.repaint();
            }
        });
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel("Enter the Name");
        final JTextField tf = new JTextField(10);
        final JButton search = new JButton("Search");
        final JButton remove = new JButton("Remove");
        panel.add(label);
        panel.add(label);
        panel.add(tf);
        panel.add(search);
        panel.add(remove);
        frame.getContentPane().add("South", panel);
        frame.getContentPane().add("North", mb);
        frame.setVisible(true);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final int index = ContactManagement.hashIndexing.get(tf.getText());
                    frame.getContentPane().add("Center", ta);
                    final Contact contact = contactManagement.search(index);
                    ta.setText(contact.toString());
                }
                catch (NullPointerException e2) {
                    JOptionPane.showMessageDialog(null, "Search not found \"" + tf.getText() + "\"", "InfoBox: Search ", 1);
                }
                catch (IOException e3) {
                    JOptionPane.showMessageDialog(null, "File not found", "InfoBox: Search ", 1);
                }
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final int index = ContactManagement.hashIndexing.get(tf.getText());
                    contactManagement.remove(index);
                    contactManagement.creatIndexing();
                }
                catch (NullPointerException e2) {
                    JOptionPane.showMessageDialog(null, "Contact not found for \"" + tf.getText() + "\"", "InfoBox: Search ", 1);
                }
            }
        });
    }
}
