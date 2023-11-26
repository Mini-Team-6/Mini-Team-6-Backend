package ybe.mini.travelserver.global.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ybe.mini.travelserver.global.api.dto.DetailInfoResponse;
import ybe.mini.travelserver.global.api.dto.DetailIntroResponse;
import ybe.mini.travelserver.global.api.dto.SearchKeywordResponse;

import java.util.Objects;

import static ybe.mini.travelserver.global.api.TourAPIProperties.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TourAPIUtils {
    private static final RestTemplate restTemplate = new RestTemplate();

    private static StringBuilder buildCommonUrl(String endpoint) {
        StringBuilder url = new StringBuilder(BASE_URL + endpoint);
        url.append("?MobileOS=").append(MOBILE_OS);
        url.append("&MobileApp=").append(MOBILE_APP);
        url.append("&_type=").append(RENDER_TYPE);
        url.append("&contentTypeId=").append(CONTENT_TYPE_ID);
        url.append("&serviceKey=").append(KEY_DECODED);

        return url;
    }

    private static <T> T fetchDataFromAPI(
            String url,
            Class<T> responseType
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<T> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                responseType
        );

        return responseEntity.getBody();
    }

    public static DetailIntroResponse bringAccommodationDetail(long contentId) {
        StringBuilder url = buildCommonUrl(DETAIL_INTRO);
        url.append("&contentId=").append(contentId);

        return fetchDataFromAPI(
                url.toString(),
                DetailIntroResponse.class
        );
    }

    public static DetailInfoResponse bringRoom(long contentId) {
        StringBuilder url = buildCommonUrl(DETAIL_INFO);
        url.append("&contentId=").append(contentId);

        return fetchDataFromAPI(
                url.toString(),
                DetailInfoResponse.class
        );
    }


    public static SearchKeywordResponse bringAccommodations(
            int pageNo,
            int numOfRows,
            String keyword,
            String areaCode
    ) {
        StringBuilder url = buildCommonUrl(SEARCH_KEYWORD);
        url.append("&keyword=").append(keyword == null ? "_" : keyword);

        if (pageNo != 0 && numOfRows != 0) {
            url.append("&pageNo=").append(pageNo);
            url.append("&numOfRows=").append(numOfRows);
        }

        if (!Objects.isNull(areaCode)) {
            url.append("&areaCode=").append(areaCode);
        }

        url.append("&arrange=").append("R");

        return fetchDataFromAPI(
                url.toString(),
                SearchKeywordResponse.class
        );
    }

    public static SearchKeywordResponse bringAccommodations(String keyword) {
        return bringAccommodations(1, 1, keyword, null);
    }

    public static SearchKeywordResponse bringAccommodations(int pageNo, int numOfRows) {
        return bringAccommodations(pageNo, numOfRows, null, null);
    }
}