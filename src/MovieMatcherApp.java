import service.MovieMatcher;
import util.ApplicationConf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MovieMatcherApp {

    public static void main(String[] args) throws Exception {

        ApplicationConf applicationConf = getApplicationConf();

        MovieMatcher movieMatcher = new MovieMatcher();
        movieMatcher.LaunchMatching(applicationConf.movieFilePathName);

    }

    private static ApplicationConf getApplicationConf() throws IOException {
        Properties prop = new Properties();
        FileInputStream in = new FileInputStream("application.properties");
        prop.load(in);
        in.close();


        ApplicationConf applicationConf = new ApplicationConf();
        applicationConf.movieFilePathName = prop.getProperty("movieFilePathName");

        return applicationConf;
    }
}
