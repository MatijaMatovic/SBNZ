package com.sbnz.template;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import com.sbnz.physio.facts.Diagnosis;
import com.sbnz.physio.facts.Pain;
import com.sbnz.physio.facts.Pain.Symptoms;
import com.sbnz.physio.facts.Therapy;
import com.sbnz.physio.facts.Therapy.TherapyType;

public class CSVReader {
    public static final String delimiter = ",";
    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
//                for (String tempStr: tempArr) {
//                    System.out.print(tempStr + " ");
//                }
//                System.out.println();
            }
            br.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static List<TemplateModel> readTemplateData(String csvFile) {
        List<TemplateModel> data = new ArrayList<>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                String[] symp = tempArr[2].split(";");
                String[] prec = tempArr[3].split(";");
                ArrayList<String> sympthoms = new ArrayList<>();
                ArrayList<String> precursors = new ArrayList<>();
                System.out.println(tempArr[2]);
                System.out.println(symp[0]);
                for (String s : symp) {
                	System.out.println(s);
                	sympthoms.add("Symptoms." + Pain.Symptoms.valueOf(s));
				}
                
                for (String p : prec) {
                	precursors.add("Precursors." + Pain.Precursors.valueOf(p));
				}
                
                TemplateModel tm = new TemplateModel("PainType." + Pain.PainType.valueOf(tempArr[0]), 
                		"PainLocalization." + Pain.PainLocalization.valueOf(tempArr[1]),
                		sympthoms, precursors, "Illness." + Diagnosis.Illness.valueOf(tempArr[4]));
                System.out.println("--------------------------------------------");
                System.out.println(tm);
                System.out.println("--------------------------------------------");
                
                data.add(tm);
            }
            br.close();
            return data;
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
    
    public static List<TherapyTemplateModel> readTherapyTemplateData(String csvFile) {
        List<TherapyTemplateModel> data = new ArrayList<>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                String[] ther = tempArr[0].split(";");
                String[] pain = tempArr[1].split(";");
                ArrayList<Therapy> therapy = new ArrayList<>();
                ArrayList<String> painIntensities = new ArrayList<>();
               
                for (String t : ther) {
                	String[] therData = t.split(":");
                	therapy.add(new Therapy(TherapyType.valueOf(therData[0]), Integer.valueOf(therData[1]),
                			therData[2]));
				}
                
                for (String p : pain) {
                	painIntensities.add("PainIntensity." + Diagnosis.PainIntensity.valueOf(p));
				}
                
                String tr = therapy.toString();
                TherapyTemplateModel tm = new TherapyTemplateModel(tr.substring(1, tr.length() - 1), painIntensities,
                		"Illness."+Diagnosis.Illness.valueOf(tempArr[2]));
                System.out.println("--------------------------------------------");
                System.out.println(tm);
                System.out.println("--------------------------------------------");
                
                data.add(tm);
            }
            br.close();
            return data;
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
