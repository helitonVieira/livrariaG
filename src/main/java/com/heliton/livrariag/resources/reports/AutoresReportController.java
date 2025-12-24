package com.heliton.livrariag.resources.reports;

import com.heliton.livrariag.services.AutorService;
import com.heliton.livrariag.services.reports.AutorLivroReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios/autores")
public class AutoresReportController  {

    private final AutorLivroReportsService autorService;

    public AutoresReportController(AutorLivroReportsService autorService) {
        this.autorService = autorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> gerarRelatorio(
            @RequestParam(required = false) Integer codau
    ) {
        byte[] pdf = autorService.gerarRelatorioAutores(codau);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=relatorio-autores.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
