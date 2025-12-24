package com.heliton.livrariag.services.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

@Service
@RequiredArgsConstructor
public class AutorLivroReportsService {

    private final DataSource dataSource;

    public byte[] gerarRelatorioAutores(Integer codau) {
        try (
                Connection connection = dataSource.getConnection()
        ) {
            Map<String, Object> params = new HashMap<>();
            params.put("codau", codau); // pode ser null

            InputStream jasperStream = getClass()
                    .getResourceAsStream("/reports/Autor_livro.jasper");

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperStream,
                    params,
                    connection
            );

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório de autores", e);
        }
    }
}
