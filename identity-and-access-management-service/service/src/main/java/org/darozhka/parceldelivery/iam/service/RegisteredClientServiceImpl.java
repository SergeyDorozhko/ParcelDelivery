package org.darozhka.parceldelivery.iam.service;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * @author S.Darozhka
 */
public class RegisteredClientServiceImpl implements RegisteredClientService {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisteredClient getId(String id) {
        return registeredClientRepository.findById(id);
    }

    @Override
    public RegisteredClient getByClientId(String clientId) {
        return registeredClientRepository.findByClientId(clientId);
    }

    @Override
    public RegisteredClient save(RegisteredClient registeredClient) {
        Validate.notNull(registeredClient, "RegisteredClient is null.");

        RegisteredClient toSave = RegisteredClient.from(registeredClient)
                .clientSecret(
                        Optional.ofNullable(registeredClient.getClientSecret())
                                .map(secret -> passwordEncoder.encode(secret))
                                .orElse(null))
                .build();

        registeredClientRepository.save(toSave);

        return getByClientId(toSave.getClientId());
    }
}
