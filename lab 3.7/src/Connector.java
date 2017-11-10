import java.io.*;

public class Connector {

    private String filename;

    public Connector(String filename) {
        this.filename = filename;
    }

    public void write(Bureau bureau) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(bureau.currentTask);
            oos.writeInt(bureau.employees.size());
            for (int i = 0; i < bureau.employees.size(); i++) {
                oos.writeObject(bureau.employees.get(i));
            }
            oos.writeInt(bureau.busy.size());
            for (int i = 0; i < bureau.busy.size(); i++) {
                oos.writeObject(bureau.busy.get(i));
            }
            oos.writeObject(bureau.planner);
            oos.flush();
        }
    }

    public Bureau read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try (ObjectInputStream oin = new ObjectInputStream(fis)) {
            Task temp = (Task) oin.readObject();
            Bureau result = new Bureau();
            int length1 = oin.readInt();
            for (int i = 0; i < length1; i++) {
                result.employees.add((Worker) oin.readObject());
            }
            int length2 = oin.readInt();
            for (int i = 0; i < length2; i++) {
                result.busy.add((Worker) oin.readObject());
            }
            result.planner = (Planner) oin.readObject();
            return result;
        }
    }

}
