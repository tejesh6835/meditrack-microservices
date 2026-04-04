package org.meditrack.authserver.auth_server.exception;

import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        switch (response.status()) {
            case 400:
                return new UserAlreadyExistsException("User Already Exists");
            default:
                return feign.FeignException.errorStatus(methodKey, response);

        }
    }
}
