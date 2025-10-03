package com.btg.fondos.infrastructure.persistence;

import com.btg.fondos.domain.Fund;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "funds")
public class FundDocument {
    @Id
    private String id;
    private String name;
    private double minimumAmount;
    private String category;

    public static FundDocument fromDomain(Fund fund) {
        FundDocument doc = new FundDocument();
        doc.setId(fund.getId());
        doc.setName(fund.getName());
        doc.setMinimumAmount(fund.getMinimumAmount());
        doc.setCategory(fund.getCategory());
        return doc;
    }

    public Fund toDomain() {
        Fund fund = new Fund();
        fund.setId(id);
        fund.setName(name);
        fund.setMinimumAmount(minimumAmount);
        fund.setCategory(category);
        return fund;
    }
}
