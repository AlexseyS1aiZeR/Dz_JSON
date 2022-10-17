import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


public class disk_work {

    public static void main(String[] args) {

        System.out.println("File system roots returned byFileSystemView.getFileSystemView():");
        FileSystemView fsv = FileSystemView.getFileSystemView();

        System.out.println("Home directory: " + fsv.getHomeDirectory());

        System.out.println("File system roots returned by File.listRoots():");
        File[] f = File.listRoots();
        for (int i = 0; i < f.length; i++) {
            System.out.println("");
            System.out.println("Drive: " + f[i]);
            System.out.println("Display name: " + fsv.getSystemDisplayName(f[i]));
            System.out.println(fsv.getFileSystemView());
            System.out.println("Total space: " + fsv.isFileSystemRoot(f[i]));
            System.out.println("Total space: " + f[i].getTotalSpace());
            System.out.println("Usable space: " + f[i].getUsableSpace());


        }
        fileWork f1 = new fileWork();
        f1.string();
    }
}

class  fileWork {

    void string() {

        BufferedReader br = null;
        try {
            File file = new File("myFile.txt");

            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            System.out.println("");
            System.out.println("Введите строку для записи в файл");
            Scanner console = new Scanner(System.in);
            pw.println(console.nextLine());

            pw.close();

            br = new BufferedReader(new FileReader("myFile.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);

            }
            file.delete();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }

        }


    }

}