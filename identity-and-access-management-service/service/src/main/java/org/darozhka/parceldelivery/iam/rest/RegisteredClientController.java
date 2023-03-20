package org.darozhka.parceldelivery.iam.rest;

import org.darozhka.parceldelivery.iam.dto.RegisteredClientCreateDto;
import org.darozhka.parceldelivery.iam.dto.RegisteredClientDto;
import org.darozhka.parceldelivery.iam.dto.RegisteredClientUpdateDto;
import org.darozhka.parceldelivery.iam.service.RegisteredClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author S.Darozhka
 */
@RestController
@RequestMapping("/api/registered-client")
public class RegisteredClientController {

    @Autowired
    private RegisteredClientService registeredClientService;

    @GetMapping("/{clientId}")
    public RegisteredClientDto getByClientId(@PathVariable(name = "clientId") String clientId) {
        return RegisteredClientDto.from(registeredClientService.getByClientId(clientId));
    }

    @PostMapping
    public RegisteredClientDto create(@RequestBody RegisteredClientCreateDto request) {
        return RegisteredClientDto.from(
                registeredClientService.save(
                        request.toRegisteredClient()));
    }

}

