package io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Arto
 */
public class FileWriter implements IFilewriter {

    Path file;

    @Override
    public boolean write(String address, List<String> lines) throws Exception {
        file = Paths.get(address);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
            return true;
        } catch (IOException e) {
            throw new Exception("tiedoston kirjoitus virhe.");
        } catch (SecurityException e) {
            throw new Exception("tiedosto on kirjoitussuojattu.");
        }
    }

}
