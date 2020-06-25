/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package top.marchand.tests.hilight.embed;

import java.io.InputStreamReader;
import java.net.URL;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

/**
 *
 * @author cmarchand
 */
public class Starter {
    public static final String JS = "js";
    public static void main(String[] args) throws Exception {
        new Starter().run();
    }
    
    public Starter() {
        super();
    }
    
    public void run() throws Exception {
        try(Context context = Context.create()) {
            URL url = getClass().getResource("/top/marchand/tests/hilight/embed/highlight.min.js");
            System.out.println("URL: "+url);
            context.eval(
                    Source.newBuilder(
                            JS, 
                            new InputStreamReader(url.openStream()),
                            "highlight.min.js").build());
            Value obj = context.getBindings(JS).getMember("hljs");
            Value function = obj.getMember("highlight");
            System.out.println(function);
            Value ret = function.execute("xml", "<test>contenu</test>");
            System.out.println(ret.getMember("value").asString());
        }
    }
}
