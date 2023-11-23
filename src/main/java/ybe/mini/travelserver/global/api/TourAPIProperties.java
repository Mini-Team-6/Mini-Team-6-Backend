package ybe.mini.travelserver.global.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("server.tour-api")
public class TourAPIProperties {
    public static final String KEY_DECODED = "0ON3kBtDxd0KYGW5spO8inNljADd/IqfnS/l3nMWq3EkARl20N3MmZVYlSvH3Y8V5fEAo7Seucd5pR7Ebm2Phg==";
    public static final String KEY_ENCODED = "0ON3kBtDxd0KYGW5spO8inNljADd%2FIqfnS%2Fl3nMWq3EkARl20N3MmZVYlSvH3Y8V5fEAo7Seucd5pR7Ebm2Phg%3D%3D";

    public static final String BASE_URL = "http://apis.data.go.kr/B551011/KorService1/";

    public static final String SEARCH_KEYWORD = "searchKeyword1";
    public static final String DETAIL_INTRO = "detailIntro1";
    public static final String DETAIL_INFO = "detailInfo1";
    public static final String SEARCH_STAY = "searchStay1";

    public static final String CONTENT_TYPE_ID = "32";
    public static final String MOBILE_OS = "ETC";
    public static final String MOBILE_APP = "TravelAPP";
    public static final String RENDER_TYPE = "json";
}
