
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress gp1 = new GameProgress(1, 1, 1, 1);
        GameProgress gp2 = new GameProgress(2, 2, 2, 2);
        GameProgress gp3 = new GameProgress(3, 3, 3, 3);
        ArrayList<String> gps = new ArrayList<>();
        gps.add("D://Games/savegames/save1.dat");
        gps.add("D://Games/savegames/save2.dat");
        gps.add("D://Games/savegames/save3.dat");
        saveGame("D://Games/savegames/save1.dat", gp1);
        saveGame("D://Games/savegames/save2.dat", gp2);
        saveGame("D://Games/savegames/save3.dat", gp3);
        zipFiles("D://Games/savegames/zip.zip", gps);
        File file1 = new File("D://Games/savegames", "save1.dat");
        File file2 = new File("D://Games/savegames", "save2.dat");
        File file3 = new File("D://Games/savegames", "save3.dat");
        file1.delete();
        file2.delete();
        file3.delete();

    }

    public static void saveGame(String path, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, ArrayList<String> gps) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String gp : gps) {
                try (FileInputStream fis = new FileInputStream(gp)) {
                    String[] nms = gp.split("/");
                    String nm = nms[nms.length - 1];
                    ZipEntry entry = new ZipEntry(nm);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}



