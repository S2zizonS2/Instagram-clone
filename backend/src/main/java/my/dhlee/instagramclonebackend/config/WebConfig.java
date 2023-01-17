package my.dhlee.instagramclonebackend.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final List<String> allowOriginUrlPatterns;

    public WebConfig(@Value("${cors.allow-origin.urls}") final List<String> allowOriginUrlPatterns) {
        this.allowOriginUrlPatterns = allowOriginUrlPatterns;
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PATCH", "DELETE")
            .allowedOriginPatterns(allowOriginUrlPatterns.toArray(String[]::new));
    }
}
