package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 메서드 패턴: 핵심기능, 부가기능 쪼개기
 * 핵심: call() - 상속
 * 부가: execute()
 */
@Slf4j
public abstract class AbstractTemplate {

    public void execute() { //변하지 않는 부분은 그대로 두고
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        call(); //상속
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    protected abstract void call(); //변하는 부분은 자식 클래스로 만듬
}
