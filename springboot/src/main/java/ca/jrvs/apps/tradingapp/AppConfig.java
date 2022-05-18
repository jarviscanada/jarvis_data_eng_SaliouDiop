package ca.jrvs.apps.tradingapp;

import ca.jrvs.apps.tradingapp.model.config.MarketDataConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.tradingapp.dao", "ca.jrvs.apps.tradingapp.service"})
public class AppConfig {

    @Bean
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(Dotenv.load().get("IEX_TOKEN"));
        return marketDataConfig;
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("Creating test datasource");
        String url = "jdbc:postgresql://localhost:5432/tradingapp";
        String user = "postgres";
        String password = "postgres";
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager  cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(100);
        return cm;
    }

}
