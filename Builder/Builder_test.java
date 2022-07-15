/**
 * 빌드패턴 사용이유
 * 1. set할 객체의 명확성
 * 2. 객체의 불변성 (데이터락)
 */
public class Builder_test {

    public static void main(String[] args) {
        /* 예시 빌드 패턴 */
        Person personinfo = new Person
                .Builder()    		// 필수값 입력 ( 빌더클래스 생성자로 빌더객체 생성)
                .setName("SeungJin")
                .setAge(25)  			// 값 set
                .setPhone(1234)
                .build();				// build() 가 객체를 생성해 돌려준다.

        /* lombok 사용시 빌드 패턴 input,output 형태 구현 */
        Person person = Person.builder().setName("testBuilder").setAge(20).setPhone(3333).build();
    }
}

 class Person {
    private String name;
    private int age;
    private int phone;

    static Builder builder() {
        return new Builder();
    }


    static class Builder {
        private String name;
        private int age;
        private int phone;

        Builder() {}

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setPhone(int phone) {
            this.phone = phone;
            return this;
        }

        // 빌더메소드
        public Person build() {

            Person personInfo = new Person();

            personInfo.name = name;

            personInfo.age = age;

            personInfo.phone = phone;

            return personInfo;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", phone=" + phone +
                    '}';
        }
    }

     @Override
     public String toString() {
         return "Person{" +
                 "name='" + name + '\'' +
                 ", age=" + age +
                 ", phone=" + phone +
                 '}';
     }
 }