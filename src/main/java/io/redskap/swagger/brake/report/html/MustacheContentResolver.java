package io.redskap.swagger.brake.report.html;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.stereotype.Component;

@Component
public class MustacheContentResolver {
    public String resolve(String mustacheFile, Map<String, ?> paramMap) {
        try {
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache m = mf.compile(mustacheFile);
            StringWriter sw = new StringWriter();
            m.execute(sw, paramMap).flush();
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error while resolving mustache content", e);
        }
    }
}
