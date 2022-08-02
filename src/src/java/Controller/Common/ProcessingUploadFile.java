/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author ADMIN
 */
public class ProcessingUploadFile {
// Upload_DIR: Name of folder want to update
// lastIndex: index of images make name of image be unique => resolve duplicate name when update file
// Save image into directory and change image name

    public ArrayList<String> processAndGetImagesName(String upload_DIR, HttpServletRequest request, int lastIndex) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileExtension = "", filename = "";
        try {
            ArrayList<String> listNames = new ArrayList<>();
            Collection<Part> parts = request.getParts();
// Create folder if folder is not exist:
            File folder = new File(request.getServletContext().getRealPath("/").replace("\\build", "") + "uploads" + File.separator + upload_DIR);
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (Part part : parts) {
                if (part.getSubmittedFileName() != null && part.getName().equalsIgnoreCase(("images"))) {
                    filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    if (!filename.equalsIgnoreCase("")) {
                        fileExtension = filename.split("[.]")[1];
                        if (!filename.equalsIgnoreCase("")) {
                            File outputFilePath = new File(request.getServletContext().getRealPath("/").replace("\\build", "") + "uploads" + File.separator + upload_DIR + File.separator + "fb" + lastIndex + "." + fileExtension);
                            inputStream = part.getInputStream();
                            outputStream = new FileOutputStream(outputFilePath);
                            int read = 0;
                            final byte[] bytes = new byte[1024];
                            while ((read = inputStream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, read);
                            }
                            listNames.add("fb" + lastIndex + "." + fileExtension);
                            lastIndex += 1;
                        }
                    }
                }
            }
            return listNames;
        } catch (IOException ex) {
            Logger.getLogger(ProcessingUploadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ProcessingUploadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
