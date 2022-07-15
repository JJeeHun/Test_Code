import java.util.Optional;

public class Optional_test {
    public static void main(String[] args) {
        /* Optional값이 절대 null이 아닌경우 */
        Optional<String> optional = Optional.of("MyName");

        /* Optional값이 null일수도 있고 아닐 수도 있는 경우 */
        Optional<String> o_null = Optional.ofNullable("Null이면 empty 반환, 아니면 해당값 반환");
        
        /**
         * @Method=orElse(String)
         * @param String
         * @desc TODO Optional의 값이 null이 아닐 경우 해당값 반환, null일 경우 orElse의 셋팅값으로 반환
         * @desc TODO orElseThrow , orElseGet(Supplier<Functional interface 파라미터는 없고 return 만 있는>)
         * @desc TODO orElse 패밀리 -> null일 경우의 행동 처리
         */
        
        /**
         * @Method=isPresent
         * @desc TODO Optional의 값이 있는지 없는지 확인(true=값있음,false=null)
         */
        
        System.out.println(o_null.isPresent());

        /**
         * @Method=map
         * @desc TODO Optional에 존재하는 Method, 함축형 사용시 Optional의 제네릭으로 선언해줘야 객체안에 Method를 사용 할 수 있다.
         */
        VO vo = new VO();
        Optional<VO> op = Optional.ofNullable(vo);
        Optional<String> newOp = op.map(VO::getName);
        System.out.println(newOp.toString());

    }
}

class VO {
    String name = "name";
    int age = 20;
    String gender = "남";

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "VO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}



