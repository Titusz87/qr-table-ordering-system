package org.restaurant.qrservice.api;

import com.google.zxing.WriterException;
import org.restaurant.qrservice.internal.service.QrService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Validated
@RequestMapping(path="/api/v1/qr")
public class QrController {
    private final QrService qrService;

    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    @GetMapping
    public ResponseEntity<byte[]> createQrCode(
            @RequestParam @Min(1) @Max(35) Integer tableNumber
    ) throws IOException, WriterException {
        byte[] qr = qrService.create(tableNumber);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qr);
    }
}