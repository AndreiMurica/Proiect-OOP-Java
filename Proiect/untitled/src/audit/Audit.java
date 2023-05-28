package audit;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Audit {
    FileWriter fileWriter;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public void logAction(String actionName) throws IOException {

        fileWriter.append(actionName + " " + dtf.format(java.time.LocalDateTime.now()) + "\n");
        fileWriter.flush();

    }

    public Audit()  {
        try {
            fileWriter = new FileWriter("untitled/data/audit.csv", true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}