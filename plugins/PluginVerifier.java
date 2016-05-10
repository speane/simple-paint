package plugins;

import signer.JarSigner;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * Created by Evgeny Shilov on 10.05.2016.
 */
public class PluginVerifier {
    public boolean verify(File file) throws ParseException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        JarFile jarFile = new JarFile(file);
        Object value = jarFile.getManifest().getMainAttributes().get(new Attributes.Name("Sign"));
        if (value == null) {
            return false;
        }
        String signing = (String) value;
        value = jarFile.getManifest().getMainAttributes().get(new Attributes.Name("Date"));
        if (value == null) {
            return false;
        }
        String date = (String) value;

        String newSigning = new JarSigner().getSigning(file, dateFormat.parse(date));
        if (newSigning.equals(signing)) {
            if (dateFormat.parse("2016/04/24 00:00:00").before(dateFormat.parse(date))) {
                return true;
            }
        }
        return false;
    }
}
