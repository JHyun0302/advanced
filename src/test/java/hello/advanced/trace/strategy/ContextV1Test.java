package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.strategy.ContextV1;
import hello.advanced.trace.strategy.strategy.Strategy;
import hello.advanced.trace.strategy.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 전략 패턴 이전
 * 핵심기능(biz-logic) & 부가기능(시간 측정)이 한 메서드에 존재
 */
@Slf4j
public class ContextV1Test {

    @Test
    void StrategyV0() {
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
     * 전략 패턴 적용: 마치 스프링처럼 선 조립 후 실행 방식
     * new ContextV1()으로 조립 후 .execute()로 실행
     */
    @Test
    void strategyV1() {
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();


    }

    /**
     * 전략 패턴 - 익명 내부 클래스
     */
    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        ContextV1 context1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        ContextV1 context2 = new ContextV1(strategyLogic2);
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        context2.execute();
    }

    /**
     * 전략 패턴 - 익명 내부 클래스: 리팩토링(인라인)
     */
    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
        context2.execute();
    }

    /**
     * 전략 패턴 - 익명 내부 클래스: 리팩토링(람다)
     */
    @Test
    void strategyV4() {
        ContextV1 context1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        context2.execute();
    }


}
