import com.gtranslate.*;
import java.io.*;
import java.lang.System;
/**
 * Created by dangh on 21.05.2017.
 */
public class ArticleTranslator {
    public static void main(String argv[]) {
        Translator translate = Translator.getInstance();
        String text = translate.translate("I am programmer", Language.ENGLISH, Language.PORTUGUESE);
        System.out.println(text);
    }
}
