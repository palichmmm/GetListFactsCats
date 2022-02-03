import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static final String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My Test Service")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // false - не следовать редиректу в ответе
                        .build())
                .build();) {
            // Создание объекта запроса с произвольными заголовками
            HttpGet request = new HttpGet(URL);
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

            //   Отправка запроса
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                List<ListFactsCats> listFactsCats = mapper.readValue(
                        response.getEntity().getContent(),
                        new TypeReference<>() {
                        });

                List<ListFactsCats> stream = listFactsCats.stream()
                        .filter(val -> (val.getUpvotes() > 0))
                        .sorted(Comparator.comparing(ListFactsCats::getUser))
                        .collect(Collectors.toList());
                stream.forEach(System.out::println);
            }
        }
    }
}
