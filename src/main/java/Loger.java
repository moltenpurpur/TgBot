import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Loger {
    BufferedWriter bw;

    Loger(String name) throws IOException {
        var file = new File(name);
        if(!file.exists())
            file.createNewFile();
        bw = new BufferedWriter(new FileWriter(file,true));
    }

    public void Add(Exception e) {
        try {
            bw.write("DateTime: "+new Date().toString() +" Exception:"+e.toString()+"\n");
            bw.flush();
            System.out.println(e.toString());
            System.out.println("sfd");
        } catch (IOException ioException) {
            System.out.println(ioException.toString());
        }
    }
}
