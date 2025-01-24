// 
// Decompiled by Procyon v0.5.36
// 

package contactmanager;

public class Contact
{
    private String name;
    private int mobile;
    private String address;
    
    public Contact() {
    }
    
    public Contact(final String name, final int mobile, final String address) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return "Contact [name=" + this.name + ", mobile=" + this.mobile + ", address=" + this.address + "]";
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public int getMobile() {
        return this.mobile;
    }
    
    public void setMobile(final int mobile) {
        this.mobile = mobile;
    }
}
