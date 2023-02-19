
import java.lang.reflect.ParameterizedType;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.*;
//import com.puppycrawl.tools.checkstyle.api.*;
//detectors 
 class SpaghettiCodeDetector {
    public static boolean isSpaghettiCode(String code) {
        // Check if the code contains too many nested loops and conditional statements
        int nestingLevel = 0;
        for (char c : code.toCharArray()) {
            if (c == '{') {
                nestingLevel++;
            } else if (c == '}') {
                nestingLevel--;
            }
            if (nestingLevel > 7) {
                return true;
            }
        }
        return false;
    }
}
class TypeCheckingDetector {
    public static boolean hasTypeChecking(Object object) {

        if (object instanceof Collection) {
            ParameterizedType type = (ParameterizedType) object.getClass().getGenericSuperclass();
            for (Type argType : type.getActualTypeArguments()) {
                if (argType instanceof TypeVariable) {
                    return true;
                }
            }
        }
        return false;
    }
}


public class DriverCode {
    public static void main(String[] args) {
        // Example code to test spaghetti code detector


        // False (having nesting level less than 7)
        String code = "public void doSomething() { for (int i = 0; i < 10; i++) { if (i % 2 == 0) { for (int j = 0; j < 5; j++) { if (j % 2 == 0) { for (int k = 0; k < 3; k++) { System.out.println(\"Hello world!\"); } } } } } }";
        boolean isSpaghettiCode = SpaghettiCodeDetector.isSpaghettiCode(code);
        System.out.println("Is spaghetti code? " + isSpaghettiCode);

        // True (having nesting level more than 7)
         code = "public void doSomething() { for (int i = 0; i < 10; i++) { if (i % 2 == 0) { for (int j = 0; j < 5; j++) { if (j % 2 == 0) { for (int k = 0; k < 3; k++) { for (int k = 0; k < 3; k++) { for (int k = 0; k < 3; k++) { System.out.println(\"Hello world!\"); } } } } } } } }";
         isSpaghettiCode = SpaghettiCodeDetector.isSpaghettiCode(code);
        System.out.println("Is spaghetti code? " + isSpaghettiCode);



//      Example of TypeCheck detector


// Case 2: an unchecked list should return true
        List<?> uncheckedList = new ArrayList<>();
        boolean hasTypeChecking2 = TypeCheckingDetector.hasTypeChecking(uncheckedList); // true
        System.out.println(hasTypeChecking2);
// Case 2: a map with checked type should return false
        Map<Integer, String> checkedMap = new HashMap<>();
        boolean hasTypeChecking3 = TypeCheckingDetector.hasTypeChecking(checkedMap); // false
        System.out.println(hasTypeChecking3);

    }
}