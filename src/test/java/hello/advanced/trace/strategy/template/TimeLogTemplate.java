package hello.advanced.trace.strategy.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 콜백 패턴: 공통된 템플릿 속에서 콜백을 이용해 핵심기능 실행
 * 전략패턴: 문맥 속에서 전략만 바꾸기
 * Context -> Template
 * Strategy -> Callback
 */
@Slf4j
public class TimeLogTemplate {
    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        callback.call(); //위임
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
