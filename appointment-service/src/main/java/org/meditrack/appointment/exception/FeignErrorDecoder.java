package org.meditrack.appointment.exception;

import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        return switch (response.status()) {
            case 400 -> new DoctorNotExistsException("Doctor Not Available");
            case 409 -> new DoctorNotAvailableAtThatTime("Doctor Not Available At That Time");
            default -> feign.FeignException.errorStatus(methodKey, response);
        };
    }
}
