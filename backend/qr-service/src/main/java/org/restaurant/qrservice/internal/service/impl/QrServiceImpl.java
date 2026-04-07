package org.restaurant.qrservice.internal.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.restaurant.qrservice.internal.service.QrService;
import org.springframework.stereotype.Service;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrServiceImpl implements QrService {

    private final String url = "http://localhost:3000/?tableId=";
    @Override
    public byte[] create(Integer tableNumber) throws WriterException, IOException {

        String qrContent = url + tableNumber.toString();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        return outputStream.toByteArray();
    }
}
