package _ReadWriteFile;

import java.io.*;
import java.util.ArrayList;

public class IOFile<E> {

    public void writerFileData(ArrayList<E> arrayData, File pathname) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((pathname)));
            objectOutputStream.writeObject(arrayData);
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.println("lỗi ghi file");
        }
    }


    public ArrayList<E> readFileData(File pathname) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathname));
            return (ArrayList<E>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("lỗi đọc file");
        }
        return null;
    }
}
