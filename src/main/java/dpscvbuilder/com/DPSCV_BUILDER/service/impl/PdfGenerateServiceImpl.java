package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderException;
import dpscvbuilder.com.DPSCV_BUILDER.service.PdfGenerateService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
@Slf4j
public class PdfGenerateServiceImpl implements PdfGenerateService {

    public byte[] generate(String processedHtml) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String url = "";

        try {

            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);

            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);

            ConverterProperties converterProperties = new ConverterProperties();

            converterProperties.setFontProvider(defaultFont);

            HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);


//            BlobId blobId = BlobId.of("dps-cv-builder-01.appspot.com", "dps-cv-builder/resum2.pdf");
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/pdf").build();
//            Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/dps-cv-builder-01-firebase-adminsdk-l252i-eb190a6f75.json"));
//            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//            Blob blob = storage.create(blobInfo, byteArrayOutputStream.toByteArray());

            byteArrayOutputStream.close();

            byteArrayOutputStream.flush();


           // url = blob.getMediaLink();

            log.info(url);

        } catch(Exception ex) {
            log.info(ex.getMessage());
            throw new DpsCvBuilderException(ErrorEnum.ERROR_PDF_GENERATE_ERROR, ex.getMessage());
        }

         return byteArrayOutputStream.toByteArray();

    }
}
