package hello.advanced.trace.strategy.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic2 implements Strategy {
    @Override
    public void call() {
        log.info("비지니스 로직2 실행");
    }
}