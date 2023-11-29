package ybe.mini.travelserver.global.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TourAPIProperties {
    private static int keyIndex = 0;
    public static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/";

    public static final String SEARCH_KEYWORD = "searchKeyword1";
    public static final String DETAIL_INFO = "detailInfo1";
    public static final String CONTENT_TYPE_ID = "32";
    public static final String MOBILE_OS = "ETC";
    public static final String MOBILE_APP = "TravelAPP";
    public static final String RENDER_TYPE = "json";

    private static final List<String> keyList = List.of(
            System.getenv("TOUR_API_KEY_1"),
            System.getenv("TOUR_API_KEY_2"),
            System.getenv("TOUR_API_KEY_3"),
            System.getenv("TOUR_API_KEY_4"),
            System.getenv("TOUR_API_KEY_5")
    );

    public static String getEncodedKey() {
        return keyList.get(keyIndex);
    }

    public static void changeNextKey() {
        log.warn("API KEY를 변경합니다.");
        TourAPIProperties.keyIndex = ++keyIndex % keyList.size();
    }
}
