package liquibase.lockservice;

import liquibase.exception.DatabaseException;
import liquibase.exception.LockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * References
 * <pre>
 *   https://docs.liquibase.com/tools-integrations/extensions/extension-upgrade-guides/lb-4.0-upgrade-guide.html
 *   https://stackoverflow.com/questions/15528795/liquibase-lock-reasons
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 3/20/23 3:15 PM
 */
public final class ForceReleaseLockService extends StandardLockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForceReleaseLockService.class);

    /**
     * @return
     */
    @Override
    public int getPriority() {
        LOGGER.info("getPriority(), super.priority: {}", super.getPriority());
        return super.getPriority() + 1;
    }

    /**
     * @throws LockException
     */
    @Override
    public void waitForLock() throws LockException {
        LOGGER.info("+waitForLock()");
        try {
            super.forceReleaseLock();
        } catch (DatabaseException ex) {
            LOGGER.error("Unable to release lock forcefully!", ex);
            throw new LockException("Could not enforce getting the lock.", ex);
        }
        super.waitForLock();
        LOGGER.info("-waitForLock()");
    }
}
