import com.google.common.reflect.TypeToken
import groovy.transform.ToString

@ToString
class Person {
    static final TypeToken<List<Person>> LIST_TYPE = new TypeToken<List<Person>>() {}

    Long id
    String name
    String address
}
