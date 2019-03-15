/**
 *
 */
package org.aguibert.testcontainers.framework;

import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategyTarget;

/**
 * @author aguibert
 */
public class MPHealthWaitStrategy extends HttpWaitStrategy {

    public MPHealthWaitStrategy() {
        super();
        forPath("/health");
        forResponsePredicate(response -> response.contains("\"outcome\":\"UP\""));
    }

    @Override
    public void waitUntilReady(WaitStrategyTarget waitStrategyTarget) {
        super.waitUntilReady(waitStrategyTarget);
        // TODO: heuristic to work around MP Health endpoint being available slightly before the app is
        // Starting with MP Health 2.0, health endpoints won't reply "UP" until the apps are available
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
}
