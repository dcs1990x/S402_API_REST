package cat.itacademy.s04.s02.n01.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Fruit Store Manager API")
                        .version("v1.0")
                        .description("Online fruit inventory manager API. ")
                        .contact(new Contact()
                                .name("Daniel Caldito Serrano")
                                .email("dcs1990x@gmail.com")));
    }
}