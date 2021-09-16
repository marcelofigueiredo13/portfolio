package harwriter;

import harreader.model.HarLog;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

import java.io.File;
import java.io.IOException;

public class HarFileWriter {
    public HarFileWriter() {
    }

    public void writeHarFile(HarLog log, File file) throws IOException {
        JsonFactory f = new JsonFactory();
        JsonGenerator g = f.createJsonGenerator(file, JsonEncoding.UTF8);
        g.useDefaultPrettyPrinter();
        g.writeStartObject();
        log.writeHar(g);
        g.writeEndObject();
        g.close();
    }
}
