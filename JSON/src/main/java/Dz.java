import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;


import java.io.IOException;

import java.util.Scanner;



public class Dz {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите возраст");
        int age = Integer.parseInt(console.nextLine());
        System.out.println("Введите имя");
        String name = console.nextLine();
        System.out.println("Введите фамилию");
        String secondName=console.nextLine();

        ObjectMapper objectMapper= new ObjectMapper();
        SomeData someData= new SomeData();
        InnerObject innerObject=new InnerObject();
        innerObject.setAge(age);
        innerObject.setName(name);
        innerObject.setSecondName(secondName);
        someData.setInnerObject(innerObject);


        String result=objectMapper.writeValueAsString(someData);

        FileWriter file=new FileWriter("newJSON.json");
        file.write(result);
        file.close();

        BufferedReader br=null;



        br = new BufferedReader(new FileReader("newJSON.json"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);

        }
        br.close();

        File f =new File("newJSON.json");
        if (f.delete()) {
            System.out.println(f.getName() + " deleted");
        } else {
            System.out.println(f.getName() + " not deleted");
        }


    }
}