package AuthorshipAnalysis;


import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author Scott Dykstra
 * @version August 31,2013
 * 
 * Java.zip from http://www.javaworld.com/community/node/8362
 * 
 */
public class EpubUnZip {

    ArrayList<String> fileList = new ArrayList();
    
    /**
     * 
     * @param newZipFile
     * @param unZippedFileFolder
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public ArrayList<String> unZipEpub(String newZipFile, String unZippedFileFolder) throws FileNotFoundException, IOException {
        
      
        String INPUT_ZIP_FILE = newZipFile;
        String OUTPUT_FOLDER = unZippedFileFolder;
        byte[] buffer = new byte[1024];
        
        try {
            File folder = new File(OUTPUT_FOLDER);
            if (!folder.exists()) {
                folder.mkdir();
            }
            
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(INPUT_ZIP_FILE));
            
            ZipFile zipFile = new ZipFile(INPUT_ZIP_FILE);
            Enumeration zEntries = zipFile.entries();
            
            while (zEntries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) zEntries.nextElement();
                
                String fileName = zipEntry.getName();
                
                int size = fileName.length();
                CharSequence tempHTM = fileName.subSequence(size - 3, size);
                CharSequence temp = fileName.subSequence(size - 4, size);
                CharSequence notTemp = fileName.subSequence(size - 5, size);
                
                
                if (("htm".equals(tempHTM.toString()) || "html".equals(temp.toString())) && !"xhtml".equals(notTemp.toString())) {
                    fileList.add(fileName);

                    createUnzipFile(zipFile.getInputStream(zipEntry),
                            new BufferedOutputStream(new FileOutputStream(
                            OUTPUT_FOLDER + "\\" + zipEntry.getName())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }
        return fileList;
    }//end main

    public final void createUnzipFile(InputStream iStream,
            OutputStream oStream) {
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = iStream.read(buffer)) >= 0) {
                oStream.write(buffer, 0, len);
            }
            
            iStream.close();
            oStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}//end EpubUnZip

