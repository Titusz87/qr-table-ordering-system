package org.restaurant.qrservice.internal.service.impl;

import org.restaurant.qrservice.internal.service.QrService;
import org.springframework.stereotype.Service;

@Service
public class QrServiceImpl implements QrService {
    @Override
    public byte[] create(Integer tableNumber) {
        return new byte[0];
    }
}
