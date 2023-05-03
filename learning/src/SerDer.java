import java.io.*;

public class SerDer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        FileOutputStream file = new FileOutputStream("./file.ser");
//        ObjectOutputStream ObjectOut = new ObjectOutputStream(file);
//        ObjectOut.writeObject(new User());
        FileInputStream file = new FileInputStream("./file.ser");
        ObjectInputStream ObjectIn = new ObjectInputStream(file);
        User user = (User) ObjectIn.readObject();
        System.out.println(user.name);
    }
}
