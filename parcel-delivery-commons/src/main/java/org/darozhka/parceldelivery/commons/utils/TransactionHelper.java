package org.darozhka.parceldelivery.commons.utils;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author S.Darozhka
 */
public interface TransactionHelper {

    public static TransactionHelper getInstance() {
        return new TransactionHelperImpl();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    <T> T doInTransaction(TransactionCallback<T> callback) throws Exception;

    @Transactional(propagation = Propagation.REQUIRED)
    default <T> T doInTransactionUnchecked(TransactionCallback<T> callback) {
        try {
            return doInTransaction(callback);
        } catch (RuntimeException e){
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    interface TransactionCallback<T> {
        T execute() throws Exception;
    }

    class TransactionHelperImpl implements TransactionHelper {

        @Override
        public <T> T doInTransaction(TransactionCallback<T> callback) throws Exception {
            return callback.execute();
        }
    }
}
