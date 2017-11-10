import engine.*;
import tires.*;
import transmission.*;
import factories.*;

import java.util.ArrayList;
import java.util.Random;


public class Shop {
    public ArrayList<Engine> engineStock;
    public ArrayList<Transmission> transmissionStock;
    public ArrayList<Tires> tiresStock;
    private int capacity;
    private int[] selectionIndex;
    private AbstractFactory engineFactory;
    private AbstractFactory transFactory;
    private AbstractFactory tiresFactory;


    Shop(int cap) {
        engineStock = new ArrayList();
        transmissionStock = new ArrayList();
        tiresStock = new ArrayList();
        capacity = cap;
        selectionIndex = new int[3];
        engineFactory = FactoryProducer.getFactory("ENGINE");
        transFactory = FactoryProducer.getFactory("TrAnSmIsSiOn");
        tiresFactory = FactoryProducer.getFactory("tires");
        Random random = new Random();
        int engineAmount = random.nextInt(cap / 2) + capacity / 4;
        cap -= engineAmount;
        int transAmount = random.nextInt(cap / 2) + capacity / 4;
        cap -= transAmount;
        int tiresAmount = cap;
        for (int j = 0; j < engineAmount; j++) {
            engineStock.add(engineFactory.getEngine(countryRandomizer()));
        }
        for (int j = 0; j < transAmount; j++) {
            transmissionStock.add(transFactory.getTransmission(countryRandomizer()));
        }
        for (int j = 0; j < tiresAmount; j++) {
            tiresStock.add(tiresFactory.getTires(countryRandomizer()));
        }
        engineStock.add(engineFactory.getEngine(countryRandomizer()).makeZeroElement());
        transmissionStock.add(transFactory.getTransmission(countryRandomizer()).makeZeroElement());
        tiresStock.add(tiresFactory.getTires(countryRandomizer()).makeZeroElement());
        selectionIndex[0] = 0;
        selectionIndex[1] = 0;
        selectionIndex[2] = 0;
    }

    void makeBestSelection(double money) {
        int engineAmount = engineStock.size();
        int transmissionAmount = transmissionStock.size();
        int tiresAmount = tiresStock.size();
        try {
            if (engineAmount > 0 || transmissionAmount > 0 || tiresAmount > 0) {
                double[] bns = new double[3];
                double[] prc = new double[3];
                double maxBonus = 0;
                for (int i = 0; i < engineAmount; i++) {
                    bns[0] = engineStock.get(i).power;
                    prc[0] = engineStock.get(i).price;
                    for (int j = 0; j < transmissionAmount; j++) {
                        bns[1] = transmissionStock.get(j).durability;
                        prc[1] = transmissionStock.get(j).price;
                        for (int k = 0; k < tiresAmount; k++) {
                            bns[2] = tiresStock.get(k).grip;
                            prc[2] = tiresStock.get(k).price;
                            double choiceBonus = bns[0] * 0.6 + bns[1] * 0.4 + bns[2] * 0.2;
                            double choicePrice = prc[0] + prc[1] + prc[2];
                            if (choicePrice < money) {
                                if (choiceBonus > maxBonus || maxBonus == 0) {
                                    maxBonus = choiceBonus;
                                    selectionIndex[0] = i;
                                    selectionIndex[1] = j;
                                    selectionIndex[2] = k;
                                }
                            }
                        }
                    }
                }
            } else throw new Exception();
        } catch (Exception a) {
            System.out.println("Best selection error: shop is empty.");
        }
    }

    static String countryRandomizer() {
        Random random = new Random();
        String[] countries = {"russia", "germany", "japan"};
        return countries[random.nextInt(3)];
    }

    void makeRandomSelection(double money) {
        double choicePrice = 0;
        int engineAmount = engineStock.size();
        int transmissionAmount = transmissionStock.size();
        int tiresAmount = tiresStock.size();
        try {
            if (engineAmount > 0 || transmissionAmount > 0 || tiresAmount > 0) {
                double[] prc = new double[3];
                Random random = new Random();
                while (choicePrice > money || choicePrice == 0) {
                    selectionIndex[0] = random.nextInt(engineAmount);
                    prc[0] = engineStock.get(selectionIndex[0]).price;
                    selectionIndex[1] = random.nextInt(transmissionAmount);
                    prc[1] = transmissionStock.get(selectionIndex[1]).price;
                    selectionIndex[2] = random.nextInt(tiresAmount);
                    prc[2] = tiresStock.get(selectionIndex[2]).price;
                    choicePrice = prc[0] + prc[1] + prc[2];
                }
            } else throw new Exception();
        } catch (Exception a) {
            System.out.println("Random selection error: shop is empty.");
        }
    }

    Engine getEngine() {
        return engineStock.get(selectionIndex[0]);
    }

    Transmission getTransmission() {
        return transmissionStock.get(selectionIndex[1]);
    }

    Tires getTires() {
        return tiresStock.get(selectionIndex[2]);
    }

    void removeSelection() {
        if (selectionIndex[0] != engineStock.size() - 1) {
            engineStock.remove(selectionIndex[0]);
            selectionIndex[0] = engineStock.size() - 1;
        }
        if (selectionIndex[1] != transmissionStock.size() - 1) {
            transmissionStock.remove(selectionIndex[1]);
            selectionIndex[1] = transmissionStock.size() - 1;
        }
        if (selectionIndex[2] != tiresStock.size() - 1) {
            tiresStock.remove(selectionIndex[2]);
            selectionIndex[2] = tiresStock.size() - 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Engines:\n");
        for (int i = 0; i < engineStock.size(); i++) {
            result.append(">" + engineStock.get(i) + "\n");
        }
        result.append("Transmissions:\n");
        for (int i = 0; i < transmissionStock.size(); i++) {
            result.append(">" + transmissionStock.get(i) + "\n");
        }
        result.append("Tires:\n");
        for (int i = 0; i < tiresStock.size(); i++) {
            result.append(">" + tiresStock.get(i) + "\n");
        }
        return result.toString();
    }

}
