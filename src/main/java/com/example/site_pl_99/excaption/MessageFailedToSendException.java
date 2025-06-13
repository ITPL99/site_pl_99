package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class MessageFailedToSendException extends BaseException {
  public MessageFailedToSendException(String message) {
    super(message);
  }
}
