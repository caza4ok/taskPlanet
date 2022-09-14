package com.company.spacetrans.service;

import com.company.spacetrans.entity.Discounts;
import com.company.spacetrans.entity.Waybill;
import com.company.spacetrans.entity.WaybillItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WaybillService {

    public Waybill calculateTotalWeight( Waybill waybill )
    {
        Double totalWeight = 0.0;
        for (WaybillItem item : waybill.getItems())
        {
            totalWeight += item.getWeight();
        }
        waybill.setTotalWeight(totalWeight);
        return waybill;
    }

    public Waybill calculateTotalCharge(Waybill waybill, Discounts discounts)
    {
        BigDecimal totalCharge = new BigDecimal(0);
        BigDecimal decimal;
        for (WaybillItem item : waybill.getItems())
        {
            decimal = totalCharge.add(item.getCharge());
            totalCharge = decimal;
        }
        decimal = totalCharge.multiply(discounts.getValue());
        waybill.setTotalCharge(decimal);
        return waybill;
    }
}
