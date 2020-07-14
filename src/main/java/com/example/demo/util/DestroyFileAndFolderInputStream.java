package com.example.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DestroyFileAndFolderInputStream extends FileInputStream {

    protected File f = null;

    public DestroyFileAndFolderInputStream(File file) throws FileNotFoundException {
        super(file);
        this.f = file;
    }

    public void close() throws IOException {
        super.close();
        File parentFile = f.getParentFile();
        this.f.delete();
        parentFile.delete();
    }
}
