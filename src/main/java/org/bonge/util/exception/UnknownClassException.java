package org.bonge.util.exception;

import java.io.File;
import java.io.IOException;

public class UnknownClassException extends IOException {

    private String unknownClassPath;
    private File fromFile;

    public UnknownClassException(String unknownClassPath, File from, Throwable e){
        super("Could not find class file of " + unknownClassPath + " from " + from.getPath(), e);
        this.unknownClassPath = unknownClassPath;
        this.fromFile = from;
    }

    public String getUnknownClassPath(){
        return this.unknownClassPath;
    }

    public File getFromFile(){
        return this.fromFile;
    }

}
