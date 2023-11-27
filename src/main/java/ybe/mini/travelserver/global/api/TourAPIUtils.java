package ybe.mini.travelserver.global.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ybe.mini.travelserver.global.api.dto.AccommodationTourAPIResponse;
import ybe.mini.travelserver.global.api.dto.RoomTourAPIResponse;
import ybe.mini.travelserver.global.exception.api.WrongCallBackException;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static ybe.mini.travelserver.global.api.TourAPIProperties.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TourAPIUtils {
    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        restTemplate.setUriTemplateHandler(factory);
    }

    private static StringBuilder buildCommonUrl(String endpoint) {
        StringBuilder url = new StringBuilder(BASE_URL + endpoint);
        url.append("?MobileOS=").append(MOBILE_OS);
        url.append("&MobileApp=").append(MOBILE_APP);
        url.append("&_type=").append(RENDER_TYPE);
        url.append("&contentTypeId=").append(CONTENT_TYPE_ID);
        url.append("&serviceKey=").append(KEY_ENCODED);

        return url;
    }

    private static <T> T fetchDataFromAPI(
            String url,
            Class<T> responseType
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        log.warn("Tour API 요청 URL : {}", url);

        return restTemplate.execute(
                url,
                HttpMethod.GET,
                requestCallback -> {
                },
                clientHttpResponse -> {
                    MediaType contentType = clientHttpResponse.getHeaders().getContentType();
                    if (contentType != null && contentType.includes(MediaType.TEXT_XML)) {
                        String body = StreamUtils.copyToString(clientHttpResponse.getBody(), Charset.defaultCharset());
                        log.error("공공포텅 오류 XML 반환 : {}", body);
                        throw new WrongCallBackException();
                    }

                    return new HttpMessageConverterExtractor<>(responseType, restTemplate.getMessageConverters())
                            .extractData(clientHttpResponse);
                },
                entity
        );
    }

    public static RoomTourAPIResponse bringRoom(long contentId) {
        StringBuilder url = buildCommonUrl(DETAIL_INFO);
        url.append("&contentId=").append(contentId);

        return fetchDataFromAPI(
                url.toString(),
                RoomTourAPIResponse.class
        );
    }


    public static AccommodationTourAPIResponse bringAccommodations(
            int pageNo,
            int numOfRows,
            String keyword,
            String code
    ) {
        StringBuilder url = buildCommonUrl(SEARCH_KEYWORD);
        url.append("&keyword=").append(keyword == null ? "_" : URLEncoder.encode(keyword, StandardCharsets.UTF_8));

        if (pageNo != 0 && numOfRows != 0) {
            url.append("&pageNo=").append(pageNo);
            url.append("&numOfRows=").append(numOfRows);
        }


        if (!Objects.isNull(code)) {
            url.append("&areaCode=").append(code);
        }

        url.append("&arrange=").append("R");

        return fetchDataFromAPI(
                url.toString(),
                AccommodationTourAPIResponse.class
        );
    }

    public static AccommodationTourAPIResponse bringAccommodation(String keyword, String code) {
        return bringAccommodations(1, 1, keyword, code);
    }
}