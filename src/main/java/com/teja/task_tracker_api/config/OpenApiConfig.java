package com.teja.task_tracker_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Task Tracker API",
                version = "1.0",
                description = """
                        **How to Use This API**
                        
                        - **Authenticate** using `/api/authenticate`:
                          ```
                          {
                            "username": "user",
                            "password": "pass"
                          }
                          ```
                        - Copy the JWT token from the response.
                        - Click the **Authorize** button above(top right side).
                        - Paste the received token and click **Authorize**.
                        - You can now access protected endpoints like `/projects`, `/tasks`.

                        Explore freely — this is a demo project!
                        """
        )
)
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}