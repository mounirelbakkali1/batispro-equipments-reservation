package ma.youcode.batispro.service.Impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.batispro.domain.entity.Bill;
import ma.youcode.batispro.service.IContractGenerator;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;

@Service
@Slf4j
public class ContractGenerator implements IContractGenerator{
    @Override
    public byte[] generateContract(@Valid Bill bill) throws Exception{
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Document document = new Document();

            document.setMargins(10, 10, 50, 40);
            document.setPageCount(1);
            String object = bill.getObject();
            document.addTitle(object);
            document.addSubject(object);
            document.addKeywords(object);
            document.addAuthor("BatisPro");
            document.addCreator("BatisPro");

            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph("Contract:" + bill.getObject(), FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLUE)));
            document.add(new Paragraph("Description: " + bill.getDescription(), FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("Billing Date: " + bill.getDateCreation().toString(), FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("Bill Number: " + bill.getBillNumber(), FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("Contract Status: " + bill.getStatus(), FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("Total Price: " + bill.getTotal(), FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL, BaseColor.BLACK)));


            PdfPTable table = new PdfPTable(6);
            table.setTotalWidth(new float[]{120, 100, 80, 90, 60, 120});
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(20);
            table.getDefaultCell().setBorder(Element.ALIGN_CENTER);
            table.setHeaderRows(1);
            table.addCell("Equipment");
            table.addCell("L. Start Date");
            table.addCell("L. End Date");
            table.addCell("Unit Price");
            table.addCell("Quantity");
            table.addCell("Total Price");
            for (int i = 0; i < bill.getBillDetails().size(); i++) {
                table.addCell(bill.getBillDetails().get(i).getEquipmentReference());
                table.addCell(bill.getBillDetails().get(i).getStartDate().toString());
                table.addCell(bill.getBillDetails().get(i).getEndDate().toString());
                table.addCell(bill.getBillDetails().get(i).getPriceUnit().toString());
                table.addCell(bill.getBillDetails().get(i).getQuantity().toString());
                table.addCell(bill.getBillDetails().get(i).getTotalPrice().toString());
            }
            table.setSpacingBefore(30f);
            table.setSpacingAfter(30f);
            document.addLanguage("en-US");
            document.add(table);
            document.close();
            return byteArrayOutputStream.toByteArray();
        } catch (DocumentException e) {
            log.error("Error while generating contract: {}", e.getMessage());
            throw new Exception("Error while generating contract");
        }
    }
}
