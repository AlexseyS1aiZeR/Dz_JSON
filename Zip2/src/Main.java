import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.*;
import java.util.Scanner;
import java.util.zip.*;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String x = null;
        Scanner console = new Scanner(System.in);

        int y = 100;
        while (y != 0) {
            System.out.println("Нажмите 1 чтобы создать архив");
            System.out.println("Нажмите 2 чтобы прочитать архив");
            System.out.println("Нажмите 3 чтобы достать фалы из архива");
            System.out.println("Нажмите 4 чтобы удалить архив");
            System.out.println("Нажмите 0 чтобы закончить");
            x = console.nextLine();
            y = Integer.parseInt(x);
            switch (y) {
                case 1:
                    createNewArch();
                    break;
                case 2:
                    readArch();
                    break;
                case 3:
                    String zipFilePath = "Arch.zip";
                    String destDirectory = "Arch";
                    UnzipUtility unzipper = new UnzipUtility();
                    try {
                        unzipper.unzip(zipFilePath, destDirectory);
                    } catch (Exception ex) {
                        // some errors occurred
                        ex.printStackTrace();
                    }
                    break;
                case 4:
                    deleteArch(console.nextLine());
                    break;
                case 0:
                    break;

            }
        }
    }

    public static void createNewArch() throws IOException {
        //Создаётся архив
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("Arch.zip"));
        Scanner console = new Scanner(System.in);
        String num = "1";
        int z = 0;
        while (z == 0) {
            System.out.println("Введите название Файла который хотите заархивировать");

            String wayToFile = console.nextLine();
            ZipEntry entry = new ZipEntry(wayToFile);
            zout.putNextEntry(entry);
            FileInputStream fis = new FileInputStream(wayToFile);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            System.out.println("Добавить ещё файл?Если нет, нажмите 1.Если да,нажмите 0");
            num = console.nextLine();
            z = Integer.parseInt(num);

        }
        zout.closeEntry();
        zout.close();
    }

    public static void readArch() throws IOException {

        ZipFile zipFile = new ZipFile("Arch.zip");
        Enumeration zipEnum = zipFile.entries();
        while (zipEnum.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) zipEnum.nextElement();
            System.out.println("Размер файла в байтах");
            System.out.println(zipEntry.getSize());
            System.out.println("Имя файла");
            System.out.println(zipEntry.getName());


        }

    }

    public static void deleteArch(String nameArch) throws IOException {


        File file = new File(nameArch);
        file.delete();
        return;
    }


}