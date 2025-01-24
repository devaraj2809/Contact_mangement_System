// 
// Decompiled by Procyon v0.5.36
// 

package contactmanager;

import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class ContactManagement implements FileManagement<Contact>
{
    public static final String DEL_CH = "**";
    static HashMap<String, Integer> hashIndexing;
    Scanner scan;
    
    static {
        ContactManagement.hashIndexing = new HashMap<String, Integer>();
    }
    
    public ContactManagement() {
        this.scan = new Scanner(System.in);
    }
    
    public static void main(final String[] args) throws IOException {
        final ContactManagement CM = new ContactManagement();
        int key = 5;
        while (true) {
            System.out.println("Operation Window");
            System.out.println("Input Press 1 ");
            System.out.println("Indexing Press 2");
            System.out.println("Delete 3");
            System.out.println("Search 4");
            key = CM.scan.nextInt();
            switch (key) {
                case 1: {
                    CM.Write(CM.pack(CM.input()));
                    continue;
                }
                case 2: {
                    CM.creatIndexing();
                    continue;
                }
                case 3: {
                    CM.creatIndexing();
                    System.out.println("Enter the contact name to be delete");
                    final String deleteString = CM.scan.next();
                    try {
                        final int index = ContactManagement.hashIndexing.get(deleteString);
                        CM.remove(index);
                        ContactManagement.hashIndexing.remove(deleteString);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Name not found");
                    }
                    continue;
                }
                case 4: {
                    CM.creatIndexing();
                    System.out.println("Enter the contact name to be search");
                    final String searchString = CM.scan.next();
                    try {
                        final int index2 = ContactManagement.hashIndexing.get(searchString);
                        CM.search(index2);
                    }
                    catch (NullPointerException e2) {
                        System.out.println("Name not found");
                    }
                    continue;
                }
                default: {
                    System.exit(0);
                    continue;
                }
            }
        }
    }
    
    @Override
    public Contact input() {
        final Contact contact = new Contact();
        System.out.print("Enter the Contact Name : ");
        contact.setName(this.scan.next());
        System.out.print("Enter the Mobile Number : ");
        contact.setMobile(this.scan.nextInt());
        System.out.print("Enter the Adress : ");
        contact.setAddress(this.scan.next());
        return contact;
    }
    
    @Override
    public void creatIndexing() {
        ContactManagement.hashIndexing.clear();
        try {
            final BufferedReader reader = new BufferedReader(new FileReader("index.txt"));
            int index = -1;
            Contact contact = null;
            String line = reader.readLine();
            while (line != null) {
                if (line.contains("*")) {
                    line = reader.readLine();
                    ++index;
                }
                else {
                    contact = this.unPack(line);
                    ContactManagement.hashIndexing.put(contact.getName(), ++index);
                    line = reader.readLine();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ContactManagement.hashIndexing.toString());
    }
    
    @Override
    public Contact search(final int index) throws IOException {
        Contact contact = null;
        Throwable t = null;
        try {
            final Stream<String> lines = Files.lines(Paths.get("index.txt", new String[0]));
            try {
                contact = this.unPack((String)lines.skip(index).findFirst().get());
            }
            finally {
                if (lines != null) {
                    lines.close();
                }
            }
        }
        finally {
            if (t == null) {
                final Throwable exception;
                t = exception;
            }
            else {
                final Throwable exception;
                if (t != exception) {
                    t.addSuppressed(exception);
                }
            }
        }
        System.out.println(contact);
        return contact;
    }
    
    @Override
    public void remove(final int index) {
        try {
            final RandomAccessFile file = new RandomAccessFile("index.txt", "rw");
            for (int i = 0; i < index; ++i) {
                file.readLine();
            }
            file.writeBytes("**");
            System.out.println("**Record deleted**");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void Write(final String buffer) {
        try {
            final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("index.txt", true)));
            printWriter.println(buffer);
            printWriter.close();
            this.updateIndexing();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String pack(final Contact contact) {
        return new StringBuffer().append(contact.getName()).append("|").append(contact.getMobile()).append("|").append(contact.getAddress()).toString();
    }
    
    @Override
    public Contact unPack(final String s) {
        Contact contact = new Contact();
        try {
            final String[] args = s.split("\\|");
            contact.setName(args[0]);
            contact.setMobile(Integer.parseInt(args[1]));
            contact.setAddress(args[2]);
        }
        catch (Exception e) {
            contact = null;
        }
        return contact;
    }
    
    @Override
    public void updateIndexing() {
        System.out.println("Updating Indexing");
        this.creatIndexing();
        System.out.println("Updating Updated");
    }
}
