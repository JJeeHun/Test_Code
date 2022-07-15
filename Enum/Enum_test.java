import java.lang.reflect.Constructor;

interface abstractCode {
    int getCode();
}
interface abstractName {
    String getName();
}
//Enum을 체이닝 할 수 있을 것 같다.

/**
 * Enum이란 하드코드성 데이터를 원활하게 관리하게 하기 위한 것인듯.
 * 예시) 테스트 및 운영의 상태 값을 담는 Enum을 생성 후 -> 단일 상태값 반환.
 * Enum의 값만 변경함으로써 전체적인 상태값 변경을 할 수 있을 것 같음.
 * ex)Api token값등 (카카오 key,네이버 key 등) 운영 상태에 따라서 return되는 key 반환 값을 변경 할 수 있을것 같다.
 */
public class Enum_test  {
    public String name = "test.class";

    public enum test_token {
        봄("봄테스트","봄운영"),여름("여름테스트","여름운영"),가을("가을테스트","가을운영"),겨울("겨울테스트","겨울운영");

        final private String test_token;
        final private String product_token;

        private test_token(String test_token ,String product_token) {
            this.test_token = test_token;
            this.product_token = product_token;
        }

        public String getToken() { return this.test_token;}
    }

    public enum Season implements abstractCode,abstractName{
        봄(10), 여름(11), 가을(12), 겨울(13);

        /* 로드시 한번만 돔. */
        {
            this.name = test_token.valueOf(name()).getToken();
        }
        final private String name;
        final private int code;

        @Override
        public int getCode() {
            return this.code;
        }
        @Override
        public String getName() {
            return this.name;
        }

        /* 한번만 돔. */
        private Season(int code){
            this.code = code;
        }

    }

    public static void main(String[] args) {
        Season s = Season.여름;
        System.out.println(Season.봄);
        System.out.println(s == Season.봄);
    }
}
