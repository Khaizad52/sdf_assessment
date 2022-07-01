package task01;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Merge {

    public static void main(String[] args) {
        String csv = null;
        String temp = null;

        LinkedList<String> attributes = new LinkedList<>();


        if(args.length == 2){
            csv = args[0];
            temp = args[1];
        }else{
            System.out.println("Input file error");
        }

        LinkedList<String> people = new LinkedList<>();
        try{
            File file = new File("/Users/user/Desktop/visa/SDFAssesment/src/main/java/task01/files/"+ csv);
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                people.add(scan.nextLine());
            }
        }catch(Exception e){
            System.out.println("CSV File does not exist");
        }

        String[] split = people.get(0).split(",");
        people.remove(0);
        for(String var: split){
            attributes.add(var);
        }

        LinkedList<String> template = new LinkedList<>();
        try{
            File file = new File("/Users/user/Desktop/visa/SDFAssesment/src/main/java/task01/files/"+ temp);
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                template.add(scan.nextLine());
            }
        }catch(Exception e){
            System.out.println("Template File does not exist");
        }

        for(String person: people){
            String[] personDetail = person.split(",");
            LinkedList<String> personalMail = new LinkedList<>();
            for(String line: template){
                for(int x=0; x<attributes.size(); x++){
                    if(line.contains("__"+attributes.get(x)+"__"))
                        line = line.replace(("__"+attributes.get(x)+"__"), personDetail[x]);
                }
                personalMail.add(line);
            }
            for(String print: personalMail){
                System.out.println(print);
            }
        }


    }
    
}
