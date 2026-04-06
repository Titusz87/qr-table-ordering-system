package org.restaurant.qrservice.internal.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrService {
    byte[] create(Integer tableNumber) throws WriterException, IOException;
}
