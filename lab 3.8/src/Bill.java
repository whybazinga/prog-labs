import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bill implements Serializable {
    // class release version:
    private static final long serialVersionUID = 1L;
    // areas with prompts:
    String payDate;
    static final String P_payDate = "Payment date";
    String name;
    static final String P_name = "Name";
    String address;
    static final String P_address = "Address";
    int houseNum;
    static final String P_house = "House number";
    int flatNum;
    static final String P_flat = "Flat number";
    int paySumm;
    static final String P_paySumm = "Payment summ";
    int penalty;
    static final String P_penalty = "Penalty";
    int overdue;
    static final String P_overdue = "Days overdue";


    // validation methods:
    private static GregorianCalendar curCalendar = new GregorianCalendar();

    static Date makeDate(String str) {
        StringTokenizer temp = new StringTokenizer(str, ".");
        int day = Integer.parseInt(temp.nextToken());
        int month = Integer.parseInt(temp.nextToken());
        int year = Integer.parseInt(temp.nextToken());
        year-=1900;
        Date date = new Date(year, month, day);
        return date;
    }

    static Boolean validDate(String str) {
        if (curCalendar.getTime().compareTo(makeDate(str)) < 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean nextRead(Scanner fin, PrintStream out) {
        return nextRead(P_name, fin, out);
    }

    static Boolean nextRead(final String prompt, Scanner fin, PrintStream out) {
        out.print(prompt);
        out.print(": ");
        return fin.hasNextLine();
    }

    public static Bill read(Scanner fin, PrintStream out) throws IOException {
        String str;
        Bill bill = new Bill();
        if (!nextRead(P_name, fin, out)) return null;
        bill.name = fin.nextLine();
        if (!nextRead(P_house, fin, out)) return null;
        bill.houseNum = Integer.parseInt(fin.nextLine());
        if (!nextRead(P_flat, fin, out)) return null;
        bill.flatNum = Integer.parseInt(fin.nextLine());
        if (!nextRead(P_payDate, fin, out)) return null;
        str = fin.nextLine();
        if (Bill.validDate(str) == false) {
            throw new IOException("Invalid Bill.payDate value");
        }
        bill.payDate = str;
        if (!nextRead(P_address, fin, out)) return null;
        bill.address = fin.nextLine();
        if (!nextRead(P_paySumm, fin, out)) return null;
        bill.paySumm = Integer.parseInt(fin.nextLine());
        if (!nextRead(P_penalty, fin, out)) return null;
        bill.penalty = Integer.parseInt(fin.nextLine());
        if (!nextRead(P_overdue, fin, out)) return null;
        bill.overdue = Integer.parseInt(fin.nextLine());
        return bill;
    }

    public Bill() {
    }

    public static final String areaDel = "\n";

    public String toString() {
        return //new String (
                address + areaDel +
                        houseNum + areaDel +
                        flatNum + areaDel +
                        name + areaDel +
                        payDate + areaDel +
                        paySumm + areaDel +
                        overdue + areaDel +
                        penalty
                //)
                ;
    }
}
