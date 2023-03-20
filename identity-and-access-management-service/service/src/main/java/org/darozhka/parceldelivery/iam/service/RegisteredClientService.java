package org.darozhka.parceldelivery.iam.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author S.Darozhka
 */
@Transactional(readOnly = true)
public interface RegisteredClientService {

//    @PreAuthorize("hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).ADMIN)")
    RegisteredClient getId(String id);

    @PreAuthorize("hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).ADMIN)")
    RegisteredClient getByClientId(String clientId);

    @Transactional(readOnly = false)
//    @PreAuthorize("hasPermission(null, T(org.darozhka.parceldelivery.iam.domain.SecurityRole).ADMIN)")
    RegisteredClient save(RegisteredClient registeredClient);
}
