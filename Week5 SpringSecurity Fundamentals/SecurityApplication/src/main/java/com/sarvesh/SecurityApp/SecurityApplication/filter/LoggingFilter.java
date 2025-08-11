package com.sarvesh.SecurityApp.SecurityApplication.filter;

import com.sarvesh.SecurityApp.SecurityApplication.config.WebSecurityConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;


@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(requestWrapper,responseWrapper);

        Charset requestCharset = Optional.ofNullable(requestWrapper.getCharacterEncoding())
                .map(Charset::forName)
                .orElse(StandardCharsets.UTF_8);

        Charset responseCharset = Optional.ofNullable(responseWrapper.getCharacterEncoding())
                .map(Charset::forName)
                .orElse(StandardCharsets.UTF_8);

        String requestBody = new String(requestWrapper.getContentAsByteArray(), requestCharset);
        String responseBody = new String(responseWrapper.getContentAsByteArray(), responseCharset);

        log.info("Request Body: {}", requestBody);
        log.info("Response Body: {}", responseBody);


        /*

        🧠 Why This Is Necessary
            HTTP bodies are transmitted as byte streams, and to convert them into text you must know the correct character encoding.

            If you use the wrong charset, the output may look garbled or incorrect (especially with non-ASCII characters).
         */

        responseWrapper.copyBodyToResponse();
        /*
            ❌ Without this line:
                The client gets an empty or broken response.

                Even if your controller returned proper JSON or HTML, it won’t be delivered.

                You’ll see status 200 OK with empty response body.
        */
    }
}


//Read
       /*
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        log.info("Incoming: {} {}{}",
                method,
                uri,
                query !=null ? "?"+query : ""
                );
        filterChain.doFilter(request,response);
        int status = response.getStatus();
        log.info("Outgoing: HTTP {}",status);

        ❌ Problem Example: Reading Request Body Too Early
            Let’s say your client sends:


            POST /login
            Content-Type: application/json

            {
              "email": "test@example.com",
              "password": "secret"
            }

            Now, your Spring filter looks like this:


            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {

                // Read request body
                String body = request.getReader().lines().collect(Collectors.joining());

                System.out.println("Logged in filter: " + body);  // ✅ You see the body here

                filterChain.doFilter(request, response); // ❌ controller gets an empty body
            }

            What Happens:
            The filter reads the request.getReader() and consumes the stream.

            Then filterChain.doFilter() sends the request to your controller.

            But the controller tries to read the same stream again (e.g., to map it to a DTO).

            The stream is already used, so your controller gets an empty body, causing:

            JSON parsing errors

            Null values in DTOs

            400 Bad Request, etc.
        */