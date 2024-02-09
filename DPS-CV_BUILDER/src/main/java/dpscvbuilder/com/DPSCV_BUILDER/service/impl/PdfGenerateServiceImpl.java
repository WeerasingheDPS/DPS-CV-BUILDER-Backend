package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DreamHireException;
import dpscvbuilder.com.DPSCV_BUILDER.service.PdfGenerateService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {
    public String generate(String resume) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            FileOutputStream fout = new FileOutputStream(new File("C:/repo/Resume.pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pdfWriter = PdfWriter.getInstance(document,byteArrayOutputStream);
            ConverterProperties converterProperties = new ConverterProperties();
            DefaultFontProvider defaultFontProvider = new DefaultFontProvider();
            converterProperties.setFontProvider(defaultFontProvider);

            HtmlConverter.convertToPdf(resume, pdfWriter.getOs(), converterProperties);

            byteArrayOutputStream.writeTo(fout);
            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();

            return "Success: PDF generated at " + "resume";
        } catch (Exception e) {
            // Log the exception for debugging purposes
            //e.printStackTrace();

            throw new DreamHireException(ErrorEnum.ERROR_NOT_FOUND, e.getMessage());
        }
    }
}
