import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

class KeyCompDate implements Comparator<String> {
    public int compare(String o1, String o2) {
        // right order:
        Date do1 = Bill.makeDate(o1);
        Date do2 = Bill.makeDate(o2);
        return do1.compareTo(do2);
    }
}

class KeyCompDateReverse implements Comparator<String> {
    public int compare(String o1, String o2) {
        // right order:
        Date do1 = Bill.makeDate(o1);
        Date do2 = Bill.makeDate(o2);
        return do2.compareTo(do1);
    }
}

class KeyCompInt implements Comparator<String> {
    public int compare(String o1, String o2) {
        // right order:
        Integer io1 = Integer.parseInt(o1);
        Integer io2 = Integer.parseInt(o2);
        return io1.compareTo(io2);
    }
}

class KeyCompIntReverse implements Comparator<String> {
    public int compare(String o1, String o2) {
        // right order:
        Integer io1 = Integer.parseInt(o1);
        Integer io2 = Integer.parseInt(o2);
        return io2.compareTo(io1);
    }
}

class KeyComp implements Comparator<String> {
    public int compare(String o1, String o2) {
        // right order:	
        return o1.compareTo(o2);
    }
}

class KeyCompReverse implements Comparator<String> {
    public int compare(String o1, String o2) {
        // reverse order:
        return o2.compareTo(o1);
    }
}

interface IndexBase {
    String[] getKeys(Comparator<String> comp);

    void put(String key, long value);

    boolean contains(String key);

    long[] get(String key);
}

class IndexOne2One implements Serializable, IndexBase {
    // Unique keys
    // class release version:
    private static final long serialVersionUID = 1L;

    private TreeMap<String, Long> map;

    public IndexOne2One() {
        map = new TreeMap<String, Long>();
    }

    public String[] getKeys(Comparator<String> comp) {
        String[] result = map.keySet().toArray(new String[0]);
        Arrays.sort(result, comp);
        return result;
    }

    public void put(String key, long value) {
        map.put(key, new Long(value));
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public long[] get(String key) {
        long pos = map.get(key).longValue();
        return new long[]{pos};
    }
}

class KeyNotUniqueException extends Exception {
    // class release version:
    private static final long serialVersionUID = 1L;

    public KeyNotUniqueException(String key) {
        super(new String("Key is not unique: " + key));
    }
}

public class Index implements Serializable, Closeable {
    // class release version:
    private static final long serialVersionUID = 1L;

    public static long[] InsertValue(long[] arr, long value) {
        int length = (arr == null) ? 0 : arr.length;
        long[] result = new long[length + 1];
        for (int i = 0; i < length; i++)
            result[i] = arr[i];
        result[length] = value;
        return result;
    }

    IndexOne2One houseNums;
    IndexOne2One flatNums;
    IndexOne2One names;
    IndexOne2One dates;

    public void test(Bill bill) throws KeyNotUniqueException {
        assert (bill != null);
        if (houseNums.contains("" + bill.houseNum)) {
            throw new KeyNotUniqueException("" + bill.houseNum);
        }
        if (names.contains(bill.name)) {
            throw new KeyNotUniqueException(bill.name);
        }
    }

    public void put(Bill bill, long value) throws KeyNotUniqueException {
        test(bill);
        houseNums.put("" + bill.houseNum, value);
        flatNums.put("" + bill.houseNum, value);
        dates.put(bill.payDate, value);
        names.put(bill.name, value);
    }

    public Index() {
        houseNums = new IndexOne2One();
        flatNums = new IndexOne2One();
        dates = new IndexOne2One();
        names = new IndexOne2One();
    }

    public static Index load(String name) throws IOException, ClassNotFoundException {
        Index obj = null;
        try {
            FileInputStream file = new FileInputStream(name);
            try (ZipInputStream zis = new ZipInputStream(file)) {
                ZipEntry zen = zis.getNextEntry();
                if (zen.getName().equals(Buffer.zipEntryName) == false) {
                    throw new IOException("Invalid block format");
                }
                try (ObjectInputStream ois = new ObjectInputStream(zis)) {
                    obj = (Index) ois.readObject();
                }
            }
        } catch (FileNotFoundException e) {
            obj = new Index();
        }
        if (obj != null) {
            obj.save(name);
        }
        return obj;
    }

    private transient String filename = null;

    public void save(String name) {
        filename = name;
    }

    public void saveAs(String name) throws IOException {
        FileOutputStream file = new FileOutputStream(name);
        try (ZipOutputStream zos = new ZipOutputStream(file)) {
            zos.putNextEntry(new ZipEntry(Buffer.zipEntryName));
            zos.setLevel(ZipOutputStream.DEFLATED);
            try (ObjectOutputStream oos = new ObjectOutputStream(zos)) {
                oos.writeObject(this);
                oos.flush();
                zos.closeEntry();
                zos.flush();
            }
        }
    }

    public void close() throws IOException {
        saveAs(filename);
    }
}
