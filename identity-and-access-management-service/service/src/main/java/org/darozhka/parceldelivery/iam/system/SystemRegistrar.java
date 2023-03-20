package org.darozhka.parceldelivery.iam.system;

import org.darozhka.parceldelivery.iam.domain.SystemUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author S.Darozhka
 */
@Transactional
public interface SystemRegistrar {

    void registerUser(SystemUser user);


}
