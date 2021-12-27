import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

public class GraalTest {
    public static void print(){
        System.out.println("test success");
    }

    public static void main(String[] args) {
        Context context = Context.newBuilder("js")
                .option("engine.WarnInterpreterOnly", "false")
                .allowHostClassLookup(s -> true)
                .allowHostAccess(HostAccess.ALL)
                .build();

        try {
            context.eval(Source.newBuilder("js",ClassLoader.getSystemResource("sample_script.js")).build());
            // 컨텍스트의 바인딩 객체에서 "accumulator" 함수를 가져온다.
            Value accumulatorFunc = context.getBindings("js").getMember("accumulator");
            int result = accumulatorFunc.execute(1, 2).asInt();
            System.out.println("result: " + result);
            context.eval("js", "print( Math.min(2, 3) )");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try (Context context = Context.create("js")) {
            context.eval(Source.newBuilder("js",
                    ClassLoader.getSystemResource("sample_script.js"))
                    .build());

            // 컨텍스트의 바인딩 객체에서 "makeContract" 함수를 가져온다.
            Value makeContractFunc = context.getBindings("js").getMember("makeContract");

            // 함수를 파라미터와 함께 실행시키고 결과를 `Value` 객체에 매핑한다.
            Value obj = makeContractFunc.execute("madplay", "010-1234-1234");

            // 반환값의 key-value 구조를 스트림을 이용해 모두 출력한다.
            obj.getMemberKeys().stream()
                    .forEach(key -> System.out.printf("%s: %s\n", key, obj.getMember(key)));
        } catch (IOException e) {
            System.err.println(e);
        }
         */
    }
}
