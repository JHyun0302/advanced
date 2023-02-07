package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

/**
 * 부가기능: 시간 측정
 */
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    /**
     * OrderControllerV3와 비교하기
     */
    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            //로직 호출
            T result = call();

            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
