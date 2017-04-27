package io;

import java.util.List;

/*
Rajapinta tiedostoon tulostamiseen
 */
public interface IFilewriter {

    public boolean write(String address, List<String> lines) throws Exception;

}
