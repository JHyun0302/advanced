package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 템플릿 메서드 패턴 이전
 * 핵심기능(biz-logic) & 부가기능(시간 측정)이 한 메서드에 존재
 */
@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    /**
     * 템플릿 메서드 패턴 적용: 코드 중복 사라짐
     * 템플릿 메서드 패턴 단점: SubClassLogic1,2 상속받게 됨!
     * 만약 부모가 수정하게되면 수많은 자식 클래스 모두 수정해야함! -> 보완: 전략 패턴
     */
    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    /**
     * 익명 내부 클래스: SubClassLogic1, 2를 클래스로 안 만듬
     */
    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        log.info("클래스 이름1={}", template1.getClass());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        log.info("클래스 이름2={}", template2.getClass());
        template2.execute();
    }

}
