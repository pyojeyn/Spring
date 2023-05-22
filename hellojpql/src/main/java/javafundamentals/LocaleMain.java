package javafundamentals;
import org.hibernate.annotations.common.util.impl.LoggerFactory;

import java.util.Locale;

public class LocaleMain {
    private static final org.jboss.logging.Logger log = LoggerFactory.logger(LocaleMain.class);
    public static void main(String[] args) {

        String country = Locale.KOREA.getCountry();
        String languageCode = Locale.KOREAN.getLanguage();

        log.info("country");
        log.info(country); // KR
        log.info("===========================");
        log.info("languageCode");
        log.info(languageCode); // ko

    }
}
