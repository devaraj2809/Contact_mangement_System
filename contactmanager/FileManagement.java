// 
// Decompiled by Procyon v0.5.36
// 

package contactmanager;

import java.io.IOException;

public interface FileManagement<T>
{
    T input();
    
    void creatIndexing() throws IOException;
    
    void updateIndexing() throws IOException;
    
    T search(final int p0) throws IOException;
    
    void remove(final int p0);
    
    String pack(final T p0);
    
    T unPack(final String p0);
    
    void Write(final String p0) throws IOException;
}
