package ybe.mini.travelserver.global.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ybe.mini.travelserver.global.api.dto.AccommodationTourAPIResponse;
import ybe.mini.travelserver.global.api.dto.RoomTourAPIResponse;
import ybe.mini.travelserver.global.exception.api.TourAPIXMLErrorResponse;
import ybe.mini.travelserver.global.exception.api.WrongRequestException;
import ybe.mini.travelserver.global.exception.api.WrongXMLFormatException;

import java.net.URLEncoder;
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

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .build();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);

        restTemplate.getMessageConverters().add(0, converter);
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
                        try {
                            JAXBContext jaxbContext = JAXBContext.newInstance(TourAPIXMLErrorResponse.class);
                            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                            TourAPIXMLErrorResponse tourAPIXMLErrorResponse = (TourAPIXMLErrorResponse) unmarshaller.unmarshal(clientHttpResponse.getBody());
                            log.error("공공포털 오류 XML 반환 : {}", tourAPIXMLErrorResponse);

                            String errorMessage = tourAPIXMLErrorResponse.getErrorHeader().getReturnAuthMsg();
                            throw new WrongRequestException(errorMessage);
                        } catch (JAXBException e) {
                            throw new WrongXMLFormatException(e);
                        }
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